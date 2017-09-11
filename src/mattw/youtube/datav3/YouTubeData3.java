package mattw.youtube.datav3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mattw.youtube.datav3.resources.ActivitiesList;

import java.lang.reflect.Modifier;

public class YouTubeData3 {

    protected final String BASE_API = "https://www.googleapis.com/youtube/v3/";
    private String dataApiKey;
    private String profileAccessToken;

    private Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.PROTECTED, Modifier.FINAL, Modifier.STATIC, Modifier.ABSTRACT)
            .create();

    public YouTubeData3() {}

    public YouTubeData3(String dataApiKey) {
        this.dataApiKey = dataApiKey;
    }

    public String getDataApiKey() { return dataApiKey; }
    public String getProfileAccessToken() { return profileAccessToken; }

    protected Gson gson() { return gson; }

    public void setDataApiKey(String dataApiKey) {
        this.dataApiKey = dataApiKey;
    }

    public void setProfileAccessToken(String accessToken) {
        this.profileAccessToken = accessToken;
    }

    public ActivitiesList activitiesList() { return new ActivitiesList(this); }

}
