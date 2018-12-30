package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/activities/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS})
public class ActivitiesList extends YouTubeResource implements Serializable {

    public static final int MAX_RESULTS = 50;

    String nextPageToken, prevPageToken;
    Item[] items;

    public ActivitiesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("activities");
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

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public static class Snippet {
            Date publishedAt;
            String channelId;
            String title;
            String description;
            Thumbs thumbnails;
            String channelTitle;
            String type;
            String groupId;

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

            public Thumbs getThumbnails() {
                return thumbnails;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public String getType() {
                return type;
            }

            public String getGroupId() {
                return groupId;
            }
        }

        public static class ContentDetails implements Serializable {
            Upload upload;
            ContentEvent like;
            ContentEvent favorite;
            ContentEvent comment;
            ContentEvent subscription;
            ContentEvent playlistItem;
            Recommendation recommendation;
            ContentEvent bulletin;
            Social social;
            ContentEvent channelItem;

            public Upload getUpload() {
                return upload;
            }

            public ContentEvent getLike() {
                return like;
            }

            public ContentEvent getFavorite() {
                return favorite;
            }

            public ContentEvent getComment() {
                return comment;
            }

            public ContentEvent getSubscription() {
                return subscription;
            }

            public ContentEvent getPlaylistItem() {
                return playlistItem;
            }

            public Recommendation getRecommendation() {
                return recommendation;
            }

            public ContentEvent getBulletin() {
                return bulletin;
            }

            public Social getSocial() {
                return social;
            }

            public ContentEvent getChannelItem() {
                return channelItem;
            }

            public static class ResourceId implements Serializable {
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
            }
            public static class Upload implements Serializable {
                String videoId;

                public String getVideoId() {
                    return videoId;
                }
            }
            public static class ContentEvent implements Serializable {
                ResourceId resourceId;

                public ResourceId getResourceId() {
                    return resourceId;
                }
            }
            public static class Social implements Serializable {
                ResourceId resourceId;
                String type, author, referenceUrl, imageUrl;

                public ResourceId getResourceId() {
                    return resourceId;
                }

                public String getType() {
                    return type;
                }

                public String getAuthor() {
                    return author;
                }

                public String getReferenceUrl() {
                    return referenceUrl;
                }

                public String getImageUrl() {
                    return imageUrl;
                }
            }
            public static class Recommendation implements Serializable {
                ResourceId resourceId, seedResourceId;
                String reason;

                public ResourceId getResourceId() {
                    return resourceId;
                }

                public ResourceId getSeedResourceId() {
                    return seedResourceId;
                }

                public String getReason() {
                    return reason;
                }
            }
        }
    }
}
