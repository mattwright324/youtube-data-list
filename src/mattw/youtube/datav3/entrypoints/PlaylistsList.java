package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/playlists/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.STATUS, Parts.PLAYER})
public class PlaylistsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    String nextPageToken, prevPageToken;
    Item[] items;

    public PlaylistsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("playlists");
    }

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

    public boolean hasItems() {
        return items != null;
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
        Status status;
        ContentDetails contentDetails;
        Player player;

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

        public Snippet getSnippet() {
            return snippet;
        }

        public Status getStatus() {
            return status;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public Player getPlayer() {
            return player;
        }

        public static class Snippet implements Serializable {
            Date publishedAt;
            String channelId, title, description, channelTitle, defaultLanguage;
            String[] tags;
            Thumbs thumbnails;
            Localized localized;

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

            public String getDefaultLanguage() {
                return defaultLanguage;
            }

            public String[] getTags() {
                return tags;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }

            public Localized getLocalized() {
                return localized;
            }

            public static class Localized implements Serializable {
                String title, description;

                public String getTitle() {
                    return title;
                }

                public String getDescription() {
                    return description;
                }
            }
        }

        public static class Status implements Serializable {
            String privacyStatus;

            public String getPrivacyStatus() {
                return privacyStatus;
            }
        }

        public static class ContentDetails implements Serializable {
            int itemCount;

            public int getItemCount() {
                return itemCount;
            }
        }

        public static class Player implements Serializable {
            String embedHtml;

            public String getEmbedHtml() {
                return embedHtml;
            }
        }
    }
}
