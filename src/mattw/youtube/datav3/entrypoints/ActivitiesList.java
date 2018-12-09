package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/activities/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS})
public class ActivitiesList extends YouTubeResource {

    public static final int MAX_RESULTS = 50;

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public ActivitiesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("activities");
    }

    public boolean hasItems() {
        return items != null;
    }

    public ActivitiesList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public ActivitiesList get(String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("channelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public class Item extends BaseItem {

        public Snippet snippet;
        public ContentDetails contentDetails;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public class Snippet {
            public Date publishedAt;
            public String channelId;
            public String title;
            public String description;
            public Thumbs thumbnails;
            public String channelTitle;
            public String type;
            public String groupId;
        }

        public class ContentDetails {
            public Upload upload;
            public ContentEvent like;
            public ContentEvent favorite;
            public ContentEvent comment;
            public ContentEvent subscription;
            public ContentEvent playlistItem;
            public Recommendation recommendation;
            public ContentEvent bulletin;
            public Social social;
            public ContentEvent channelItem;

            public class ResourceId {
                public String kind;
                public String videoId;
                public String channelId;
                public String playlistId;
            }
            public class Upload {
                public String videoId;
            }
            public class ContentEvent {
                public ResourceId resourceId;
            }
            public class Social {
                public String type;
                public ResourceId resourceId;
                public String author;
                public String referenceUrl;
                public String imageUrl;
            }
            public class Recommendation {
                public ResourceId resourceId;
                public String reason;
                public ResourceId seedResourceId;
            }
        }
    }
}
