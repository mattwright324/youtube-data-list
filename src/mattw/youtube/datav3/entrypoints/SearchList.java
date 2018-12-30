package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/search/list
 * @version 2018-12-30
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

    String nextPageToken, prevPageToken, regionCode;
    Item[] items;

    public SearchList(YouTubeData3 data) {
        super(data);
        setCost(100);
        setField("maxResults", 50);
        setField("type", TYPE_ALL);
        setField("order", ORDER_RELEVANCE);
        setDataPath("search");
    }

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

    public boolean hasItems() {
        return items != null;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public Item[] getItems() {
        return items;
    }

    public static class Item implements Serializable {
        String kind, etag;
        ID id;
        Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public String getKind() {
            return kind;
        }

        public String getEtag() {
            return etag;
        }

        public ID getId() {
            return id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public static class ID implements Serializable {
            String kind, videoId, channelId, playlistId;

            public String getKind() {
                return kind;
            }

            public String getVideoId() {
                return videoId;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getPlaylistId() {
                return playlistId;
            }

            public String getId() {
                if(videoId != null) return videoId;
                if(channelId != null) return channelId;
                if(playlistId != null) return playlistId;
                return null;
            }
        }
        public static class Snippet implements Serializable {
            Date publishedAt;
            String channelId, title, description, liveBroadcastContent, channelTitle;
            Thumbs thumbnails;

            public Date getPublishedAt() {
                return publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getLiveBroadcastContent() {
                return liveBroadcastContent;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }
        }

        public String toString() {
            if(hasSnippet()) {
                return snippet.title;
            }
            return super.toString();
        }
    }
}
