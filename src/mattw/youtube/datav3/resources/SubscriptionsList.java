package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.SUBSCRIBER_SNIPPET})
public class SubscriptionsList extends YouTubeResource {

    public static final int MAX_RESULTS = 50;

    public static final String ORDER_ALPHABETICAL = "alphabetical";
    public static final String ORDER_RELEVANCE = "relevance";
    public static final String ORDER_UNREAD = "unread";

    {
        this.dataPath = "subscriptions";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public SubscriptionsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
    }

    public boolean hasItems() { return items != null; }

    public SubscriptionsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public SubscriptionsList order(String order) {
        fields.put("order", order);
        return this;
    }

    public SubscriptionsList getByChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("channelId", channelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public SubscriptionsList getByIds(String part, String ids, String forChannelId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("id", ids);
        fields.put("forChannelId", forChannelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;
        public ContentDetails contentDetails;
        public SubscriberSnippet subscriberSnippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasSubscriberSnippet() {
            return subscriberSnippet != null;
        }

        public class Snippet {
            public Date publishedAt;
            public String channelTitle;
            public String title;
            public String description;
            public ResourceId resourceId;
            public String channelId;
            public Thumbs thumbnails;
            public class ResourceId {
                public String kind;
                public String channelId;
            }
        }
        public class ContentDetails {
            public int totalItemCount;
            public int newItemCount;
            public String activityType;
        }
        public class SubscriberSnippet {
            public String title;
            public String description;
            public String channelId;
            public Thumbs thumbnails;
        }
    }
}
