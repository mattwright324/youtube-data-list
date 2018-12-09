package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/subscriptions/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.SUBSCRIBER_SNIPPET})
public class SubscriptionsList extends YouTubeResource {

    public static final int MAX_RESULTS = 50;

    public static final String ORDER_ALPHABETICAL = "alphabetical";
    public static final String ORDER_RELEVANCE = "relevance";
    public static final String ORDER_UNREAD = "unread";

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public SubscriptionsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("subscriptions");
    }

    public boolean hasItems() { return items != null; }

    public SubscriptionsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public SubscriptionsList order(String order) {
        setField("order", order);
        return this;
    }

    public SubscriptionsList getByChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("channelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public SubscriptionsList getByIds(String part, String ids, String forChannelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("id", ids);
        setField("forChannelId", forChannelId);
        setField("pageToken", pageToken);
        return get();
    }

    public class Item extends BaseItem {

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
