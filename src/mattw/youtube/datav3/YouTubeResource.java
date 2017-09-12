package mattw.youtube.datav3;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class YouTubeResource {

    protected SimpleDateFormat SDF = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss:sZ");

    public static final String PART_ID = "id"; // No cost.

    protected String dataPath;
    protected YouTubeData3 data;
    public Map<String,Object> fields = new HashMap<>();
    public String kind;
    public String etag;
    public PageInfo pageInfo;

    public String getKind() { return kind; }
    public String getEtag() { return etag; }

    public boolean hasPageInfo() { return pageInfo != null; }
    public PageInfo getPageInfo() { return pageInfo; }

    protected YouTubeResource(YouTubeData3 data) {
        setYouTubeData(data);
    }

    protected void setYouTubeData(YouTubeData3 data) {
        this.data = data;
        fields.put("part", PART_ID);
    }

    protected String getRequestFields() {
        fields.put("key", data.getDataApiKey());
        fields.put("access_token", data.getProfileAccessToken());
        return fields.keySet().stream()
                .filter(key -> fields.get(key) != null)
                .map(key -> key+"="+fields.get(key))
                .collect(Collectors.joining("&"));
    }

    public <T extends YouTubeResource> T get() throws IOException, YouTubeErrorException {
        Response res = getJson(data.BASE_API+dataPath+"?"+getRequestFields());
        if(res.error) {
            throw data.gson().fromJson(res.jsonMessage, YouTubeErrorException.class);
        } else {
            return (T) data.gson().fromJson(res.jsonMessage, ((T) this).getClass());
        }
    }

    public class PageInfo {
        public int totalResults;
        public int resultsPerPage;
    }

    public class Item {
        private String kind;
        private String etag;
        private String id;

        public String getKind() { return kind; }
        public String getEtag() { return etag; }
        public String getId() { return id; }
    }


    public class Response {
        public boolean error = false;
        public String jsonMessage;
        public Response(boolean error, String json) {
            this.error = error;
            this.jsonMessage = json;
        }
    }

    private Response getJson(String url) throws IOException {
        return getJson(new URL(url));
    }

    private Response getJson(URL url) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
        con.setRequestProperty("Referer", "https://github.com/mattwright324/youtube-data-list");
        con.connect();
        InputStream is;
        boolean error = false;
        if (con.getResponseCode() < HttpsURLConnection.HTTP_BAD_REQUEST) {
            is = con.getInputStream();
        } else {
            error = true;
            is = con.getErrorStream();
        }
        return new Response(error, new String(toByteArray(error, is), "UTF-8"));
    }

    public static byte[] toByteArray(boolean error, InputStream is) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] b = new byte[4096];
            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            if (error) {
                System.err.println(new String(output.toByteArray(), "UTF-8"));
            }
            return output.toByteArray();
        }
    }
}
