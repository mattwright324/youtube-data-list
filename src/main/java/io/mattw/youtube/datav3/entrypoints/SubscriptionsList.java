package io.mattw.youtube.datav3.entrypoints;

import io.mattw.youtube.datav3.AcceptsParts;
import io.mattw.youtube.datav3.Parts;
import io.mattw.youtube.datav3.Thumbs;
import io.mattw.youtube.datav3.YouTubeData3;
import io.mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/subscriptions/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.CONTENT_DETAILS, Parts.SUBSCRIBER_SNIPPET})
public class SubscriptionsList extends YouTubeResource {

    public static final int MAX_RESULTS = 50;

    public static final String ORDER_ALPHABETICAL = "alphabetical";
    public static final String ORDER_RELEVANCE = "relevance";
    public static final String ORDER_UNREAD = "unread";

    String nextPageToken, prevPageToken;
    Item[] items;

    public SubscriptionsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("subscriptions");
    }

    public SubscriptionsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public SubscriptionsList order(String order) {
        setField("order", order);
        return this;
    }

    public SubscriptionsList getByChannel(String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("channelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public SubscriptionsList getByIds(String ids, String forChannelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("id", ids);
        setField("forChannelId", forChannelId);
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
        SubscriberSnippet subscriberSnippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasSubscriberSnippet() {
            return subscriberSnippet != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public SubscriberSnippet getSubscriberSnippet() {
            return subscriberSnippet;
        }

        public static class Snippet implements Serializable {
            Date publishedAt;
            String channelTitle, title, description, channelId;
            ResourceId resourceId;
            Thumbs thumbnails;

            public Date getPublishedAt() {
                return publishedAt;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getChannelId() {
                return channelId;
            }

            public ResourceId getResourceId() {
                return resourceId;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }

            public static class ResourceId implements Serializable {
                String kind, channelId;

                public String getKind() {
                    return kind;
                }

                public String getChannelId() {
                    return channelId;
                }
            }
        }
        public static class ContentDetails implements Serializable {
            int totalItemCount, newItemCount;
            String activityType;

            public int getTotalItemCount() {
                return totalItemCount;
            }

            public int getNewItemCount() {
                return newItemCount;
            }

            public String getActivityType() {
                return activityType;
            }
        }
        public static class SubscriberSnippet implements Serializable {
            String title, description, channelId;
            Thumbs thumbnails;

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getChannelId() {
                return channelId;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }
        }
    }
}
