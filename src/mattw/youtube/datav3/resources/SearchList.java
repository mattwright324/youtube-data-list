package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.Thumbs;
import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeErrorException;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;
import java.util.Date;

public class SearchList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;
    public final static String PART_SNIPPET = "snippet"; // cost: 2

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

    {
        this.dataPath = "search";
    }

    public String nextPageToken;
    public String prevPageToken;
    public String regionCode;
    public Item[] items;

    public SearchList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
        fields.put("type", TYPE_ALL);
        fields.put("order", ORDER_RELEVANCE);
    }

    public boolean hasItems() { return items != null; }

    public SearchList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public SearchList publishedBefore(String publishedBefore) {
        fields.put("publishedBefore", publishedBefore);
        return this;
    }

    public SearchList publishedAfter(String publishedAfter) {
        fields.put("publishedAfter", publishedAfter);
        return this;
    }

    public SearchList order(String order) {
        fields.put("order", order);
        return this;
    }

    public SearchList get(String part, String q, String type, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("q", q);
        fields.put("type", type);
        fields.put("pageToken", pageToken);
        return get();
    }

    public SearchList getByLocation(String part, String q, String pageToken, String location, String locationRadius) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("q", q);
        fields.put("type", TYPE_VIDEO);
        fields.put("pageToken", pageToken);
        fields.put("location", location);
        fields.put("locationRadius", locationRadius);
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
