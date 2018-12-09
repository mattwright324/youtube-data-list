package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/search/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class SearchList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    public final static String TYPE_ALL = "";
    public final static String TYPE_CHANNEL = "channel";
    public final static String TYPE_PLAYLIST = "playlist";
    public final static String TYPE_VIDEO = "video";

    public final static String ORDER_DATE = "date";
    public final static String ORDER_RATING = "rating";
    public final static String ORDER_RELEVANCE = "relevance";
    public final static String ORDER_TITLE = "title";
    public final static String ORDER_VIDEO_COUNT = "videoCount";
    public final static String ORDER_VIEW_COUNT = "viewCount";

    public String nextPageToken;
    public String prevPageToken;
    public String regionCode;
    public Item[] items;

    public SearchList(YouTubeData3 data) {
        super(data);
        setCost(100);
        setField("maxResults", 50);
        setField("type", TYPE_ALL);
        setField("order", ORDER_RELEVANCE);
        setDataPath("search");
    }

    public boolean hasItems() { return items != null; }

    public SearchList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public SearchList publishedBefore(String publishedBefore) {
        setField("publishedBefore", publishedBefore);
        return this;
    }

    public SearchList publishedAfter(String publishedAfter) {
        setField("publishedAfter", publishedAfter);
        return this;
    }

    public SearchList order(String order) {
        setField("order", order);
        return this;
    }

    public SearchList get(String q, String type, String pageToken) throws IOException, YouTubeErrorException {
        setField("q", q);
        setField("type", type);
        setField("pageToken", pageToken);
        return get();
    }

    public SearchList getByLocation(String q, String pageToken, String location, String locationRadius) throws IOException, YouTubeErrorException {
        setField("q", q);
        setField("type", TYPE_VIDEO);
        setField("pageToken", pageToken);
        setField("location", location);
        setField("locationRadius", locationRadius);
        return get();
    }

    public class Item {
        public String kind;
        public String etag;
        public ID id;
        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class ID {
            public String kind;
            public String videoId;
            public String channelId;
            public String playlistId;

            public String getId() {
                if(videoId != null) return videoId;
                if(channelId != null) return channelId;
                if(playlistId != null) return playlistId;
                return null;
            }
        }
        public class Snippet {
            public Date publishedAt;
            public String channelId;
            public String title;
            public String description;
            public Thumbs thumbnails;
            public String channelTitle;
            public String liveBroadcastContent;
        }

        public String toString() {
            if(hasSnippet()) {
                return snippet.title;
            }
            return super.toString();
        }
    }
}
