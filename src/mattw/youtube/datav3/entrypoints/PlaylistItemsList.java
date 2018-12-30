package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/playlistItems/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.STATUS})
public class PlaylistItemsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    String nextPageToken, prevPageToken;
    Item[] items;

    public PlaylistItemsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("playlistItems");
    }

    public PlaylistItemsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public PlaylistItemsList get(String playlistId, String pageToken) throws IOException, YouTubeErrorException {
        setField("playlistId", playlistId);
        setField("pageToken", pageToken);
        return get();
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public Item[] getItems() {
        return items;
    }

    public static class Item extends BaseItem {
        Snippet snippet;
        ContentDetails contentDetails;
        Status status;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasStatus() {
            return status != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public Status getStatus() {
            return status;
        }

        public static class Snippet implements Serializable {
            Date publishedAt;
            String channelId, title, description, channelTitle, playlistId;
            Thumbs thumbnails;
            int position;
            ResourceId resourceId;

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

            public String getChannelTitle() {
                return channelTitle;
            }

            public String getPlaylistId() {
                return playlistId;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }

            public int getPosition() {
                return position;
            }

            public ResourceId getResourceId() {
                return resourceId;
            }

            public static class ResourceId implements Serializable {
                String kind, videoId;

                public String getKind() {
                    return kind;
                }

                public String getVideoId() {
                    return videoId;
                }
            }
        }
        public static class ContentDetails implements Serializable {
            String videoId, startAt, endAt, note;

            public String getVideoId() {
                return videoId;
            }

            public String getStartAt() {
                return startAt;
            }

            public String getEndAt() {
                return endAt;
            }

            public String getNote() {
                return note;
            }
        }
        public static class Status implements Serializable {
            String privacyStatus;

            public String getPrivacyStatus() {
                return privacyStatus;
            }
        }
    }

}
