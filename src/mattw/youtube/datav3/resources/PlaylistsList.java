package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.Thumbs;
import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;
import java.util.Date;

public class PlaylistsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;
    public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
    public final static String PART_SNIPPET = "snippet"; // cost: 2
    public final static String PART_STATUS = "status"; // cost: 2
    public final static String PART_PLAYER = "player"; // cost: 0

    {
        this.dataPath = "playlists";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public PlaylistsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
    }

    public boolean hasItems() { return items != null; }

    public PlaylistsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public PlaylistsList getByChannel(String part, String channelId, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("channelId", channelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public PlaylistsList getByPlaylist(String part, String playlistId, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("playlistId", playlistId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;
        public Status status;
        public ContentDetails contentDetails;
        public Player player;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasStatus() {
            return status != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasPlayer() {
            return player != null;
        }

        public class Snippet {
            public Date publishedAt;
            public String channelId;
            public String title;
            public String description;
            public Thumbs thumbnails;
            public String channelTitle;
            public String[] tags;
            public String defaultLanguage;
            public Localized localized;
            public class Localized {
                public String title;
                public String description;
            }
        }

        public class Status {
            public String privacyStatus;
        }

        public class ContentDetails {
            public int itemCount;
        }

        public class Player {
            public String embedHtml;
        }
    }
}
