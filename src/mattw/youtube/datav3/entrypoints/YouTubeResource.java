package mattw.youtube.datav3.entrypoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mattw.youtube.datav3.AcceptsParts;
import mattw.youtube.datav3.Parts;
import mattw.youtube.datav3.YouTubeData3;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Serves as the base for each YouTube API entry point.
 *
 * @version 2018-12-08
 * @author mattwright324
 */
abstract class YouTubeResource implements Serializable {

    private transient Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.PROTECTED, Modifier.FINAL, Modifier.STATIC, Modifier.ABSTRACT)
            .create();
    private transient String dataPath;
    private transient YouTubeData3 data;
    private transient Map<String,Object> fields = new HashMap<>();
    private transient Set<Parts> parts = new HashSet<>();
    private transient int cost = 0;

    String kind, etag;
    PageInfo pageInfo;

    YouTubeResource(YouTubeData3 data) {
        setYouTubeData(data);
    }

    public <T extends YouTubeResource> T part(Parts part) {
        parts.add(part);
        return (T) this;
    }

    public <T extends YouTubeResource> T parts(Parts... parts) {
        Collections.addAll(this.parts, parts);
        return (T) this;
    }

    void setField(String key, Object value) {
        fields.put(key, value);
    }

    void setCost(int cost) {
        this.cost = cost;
    }

    void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public int getCost() {
        return cost;
    }

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public boolean hasPageInfo() {
        return pageInfo != null;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    private void setYouTubeData(YouTubeData3 data) {
        this.data = data;
        setField("part", Parts.ID.getId());
    }

    public int estimateRequestCost() {
        return getCost() + parts.stream()
                .mapToInt(Parts::getCost)
                .sum();
    }

    private String buildRequestUrl() throws IOException {
        setField("part", parts.stream().map(Parts::getId).collect(Collectors.joining(",")));
        setField("key", data.getDataApiKey());
        setField("access_token", data.getProfileAccessToken());

        String queryParams = fields.keySet().stream()
                .map(key -> String.format("%s=%s", key, fields.get(key)))
                .collect(Collectors.joining("&"));

        Annotation[] as = this.getClass().getAnnotations();

        for(Annotation a : as) {
            if(a instanceof AcceptsParts) {
                AcceptsParts parts = (AcceptsParts) a;
                List<Parts> supportedParts = Arrays.asList(parts.values());
                List<Parts> unsupported = new ArrayList<>();

                for(Parts part : this.parts) {
                    if(!supportedParts.contains(part)) {
                        unsupported.add(part);
                    }
                }

                if(!unsupported.isEmpty()) {
                    throw new IOException(String.format(
                            "The %s API call does not support given parts %s. Use one that is supported: %s.",
                            this.dataPath, unsupported, supportedParts));
                }
                break;
            }
        }

        return String.format("https://www.googleapis.com/youtube/v3/%s?%s",
                dataPath,
                queryParams);
    }

    public <T extends YouTubeResource> T get() throws IOException, YouTubeErrorException {
        String requestUrl = buildRequestUrl();

        data.spendCost(this.estimateRequestCost());

        Response res = getJsonResponse(requestUrl);
        if(res.isError()) {
            YouTubeErrorException yee = gson.fromJson(res.getJsonMessage(), YouTubeErrorException.class);
            yee.setRequestUrl(res.getRequestUrl());
            throw yee;
        } else {
            return (T) gson.fromJson(res.getJsonMessage(), this.getClass());
        }
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
            boolean errorStream = (code < 200 || code >= 300);
            try(InputStream inputStream =  errorStream ?  urlConnection.getErrorStream() : urlConnection.getInputStream()) {
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
            byte[] b = new byte[65535];
            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        }
    }
}
