package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;

public class ChannelsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;
    public final static String PART_AUDIT_DETAILS = "auditDetails"; // cost: 4
    public final static String PART_BRANDING_SETTINGS = "brandingSettings"; // cost: 2
    public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
    public final static String PART_CONTENT_OWNER_DETAILS = "contentOwnerDetails"; // cost: 2
    public final static String PART_INVIDEO_PROMOTION = "invideoPromotion"; // cost: 2
    // public final static String PART_LOCALIZATIONS = "localizations"; // cost: 2
    public final static String PART_SNIPPET = "snippet"; // cost: 2
    public final static String PART_STATISTICS = "statistics"; // cost: 2
    public final static String PART_STATUS = "status"; // cost: 2
    public final static String PART_TOPIC_DETAILS = "topicDetails"; // cost: 2

    {
        this.dataPath = "channels";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public ChannelsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
    }

    public ChannelsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public ChannelsList getByCategory(String part, String categoryId, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("categoryId", categoryId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public ChannelsList getByUsername(String part, String forUsername, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("forUsername", forUsername);
        fields.put("pageToken", pageToken);
        return get();
    }

    public ChannelsList getByChannel(String part, String channelId, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("channelId", channelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public ChannelsList getMine(String part, String pageToken) throws IOException {
        fields.put("mine", "true");
        fields.put("pageToken", pageToken);
        return get();
    }

}
