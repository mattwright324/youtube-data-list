package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.STATUS})
public class PlaylistItemsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    {
        this.dataPath = "playlistItems";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public PlaylistItemsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
    }

    public boolean hasItems() { return items != null; }

    public PlaylistItemsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public PlaylistItemsList get(String part, String playlistId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("playlistId", playlistId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {
        public Snippet snippet;
        public ContentDetails contentDetails;
        public Status status;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasStatus() {
            return status != null;
        }

        public class Snippet {
            public Date publishedAt;
            public String channelId;
            public String title;
            public String description;
            public Thumbs thumbnails;
            public String channelTitle;
            public String playlistId;
            public int position;
            public ResourceId resourceId;
            public class ResourceId {
                public String kind;
                public String videoId;
            }
        }
        public class ContentDetails {
            public String videoId;
            public String startAt;
            public String endAt;
            public String note;
        }
        public class Status {
            public String privacyStatus;
        }
    }

}
