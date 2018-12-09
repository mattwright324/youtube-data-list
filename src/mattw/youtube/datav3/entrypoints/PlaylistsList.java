package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/playlists/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.STATUS, Parts.PLAYER})
public class PlaylistsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public PlaylistsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("playlists");
    }

    public boolean hasItems() { return items != null; }

    public PlaylistsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public PlaylistsList getByChannel(String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("channelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public PlaylistsList getByPlaylist(String playlistId, String pageToken) throws IOException, YouTubeErrorException {
        setField("playlistId", playlistId);
        setField("pageToken", pageToken);
        return get();
    }

    public class Item extends BaseItem {

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
