package mattw.youtube.datav3;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
public abstract class YouTubeResource implements Serializable {

    protected transient String dataPath;
    protected transient YouTubeData3 data;
    protected transient Map<String,Object> fields = new HashMap<>();

    String kind, etag;
    PageInfo pageInfo;

    public String getKind() { return kind; }
    public String getEtag() { return etag; }

    public boolean hasPageInfo() { return pageInfo != null; }
    public PageInfo getPageInfo() { return pageInfo; }

    protected YouTubeResource(YouTubeData3 data) {
        setYouTubeData(data);
    }

    protected void setYouTubeData(YouTubeData3 data) {
        this.data = data;
        fields.put("part", Parts.ID.getId());
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
        Response res = getJsonResponse(data.BASE_API+dataPath+"?"+getRequestFields());
        if(res.isError()) {
            YouTubeErrorException yee= data.gson().fromJson(res.getJsonMessage(), YouTubeErrorException.class);
            yee.setRequestUrl(res.getRequestUrl());
            throw yee;
        } else {
            return (T) data.gson().fromJson(res.getJsonMessage(), this.getClass());
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

    private Response getJsonResponse(String url) throws IOException {
        return getJsonResponse(new URL(url));
    }

    private Response getJsonResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = data.getUseHttps() ?
                (HttpsURLConnection) url.openConnection() :
                (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setRequestProperty("Accept", "application/json");
            for(String key : data.getRequestHeaders().keySet()) {
                urlConnection.setRequestProperty(key, data.getRequestHeaders().get(key));
            }
            urlConnection.connect();

            int code = urlConnection.getResponseCode();
            boolean errorStream = (code >= 200 && code < 300);
            try(InputStream inputStream =  errorStream ? urlConnection.getInputStream() : urlConnection.getErrorStream()) {
                String response = new String(toByteArray(inputStream), StandardCharsets.UTF_8);

                return new Response.Builder()
                        .error(errorStream)
                        .jsonMessage(response)
                        .requestUrl(url.toString())
                        .build();
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    private static byte[] toByteArray(InputStream is) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] b = new byte[4096];
            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        }
    }
}
