package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/comments/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class CommentsList extends YouTubeResource {

    public final static int MAX_RESULTS = 100;

    public final static String FORMAT_HTML = "html";
    public final static String FORMAT_PLAIN = "plainText";

    String nextPageToken;
    Item[] items;

    public CommentsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 100);
        setDataPath("comments");
    }

    public CommentsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public CommentsList textFormat(String textFormat) {
        setField("textFormat", textFormat);
        return this;
    }

    public CommentsList get(String id, String pageToken) throws IOException, YouTubeErrorException {
        setField("id", id);
        setField("pageToken", pageToken);
        return get();
    }

    public CommentsList getByParentId(String parentId, String pageToken) throws IOException, YouTubeErrorException {
        setField("parentId", parentId);
        setField("pageToken", pageToken);
        return get();
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public Item[] getItems() {
        return items;
    }

    public static class Item extends BaseItem {

        Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public static class Snippet implements Serializable {
            String authorDisplayName, authorProfileImageUrl, authorChannelUrl, channelId, videoId, textDisplay,
                    textOriginal, parentId, moderationStatus, viewerRating;
            AuthorChannelId authorChannelId;
            boolean canRate;
            int likeCount;
            Date publishedAt, updatedAt;

            public String getAuthorDisplayName() {
                return authorDisplayName;
            }

            public String getAuthorProfileImageUrl() {
                return authorProfileImageUrl;
            }

            public String getAuthorChannelUrl() {
                return authorChannelUrl;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getVideoId() {
                return videoId;
            }

            public String getTextDisplay() {
                return textDisplay;
            }

            public String getTextOriginal() {
                return textOriginal;
            }

            public String getParentId() {
                return parentId;
            }

            public String getModerationStatus() {
                return moderationStatus;
            }

            public String getViewerRating() {
                return viewerRating;
            }

            public AuthorChannelId getAuthorChannelId() {
                return authorChannelId;
            }

            public boolean isCanRate() {
                return canRate;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public Date getPublishedAt() {
                return publishedAt;
            }

            public Date getUpdatedAt() {
                return updatedAt;
            }

            public static class AuthorChannelId implements Serializable {
                String value;

                public String getValue() {
                    return value;
                }
            }
        }
    }
}
