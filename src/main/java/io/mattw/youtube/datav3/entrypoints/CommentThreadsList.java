package io.mattw.youtube.datav3.entrypoints;

import io.mattw.youtube.datav3.AcceptsParts;
import io.mattw.youtube.datav3.Parts;
import io.mattw.youtube.datav3.YouTubeData3;
import io.mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/commentThreads/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.REPLIES})
public class CommentThreadsList extends YouTubeResource {

    public final static int MAX_RESULTS = 100;

    public final static String ORDER_TIME = "time"; // default value
    public final static String ORDER_RELEVANCE = "relevance";

    public final static String FORMAT_HTML = "html"; // default value
    public final static String FORMAT_PLAIN = "plainText";

    String nextPageToken;
    Item[] items;

    public CommentThreadsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 100);
        setField("order", ORDER_TIME);
        setDataPath("commentThreads");
    }

    public CommentThreadsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public CommentThreadsList order(String order) {
        setField("order", order);
        return this;
    }

    public CommentThreadsList searchTerms(String searchTerms) {
        setField("searchTerms", searchTerms);
        return this;
    }

    public CommentThreadsList getThreadsRelatedToChannel(String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("allThreadsRelatedToChannelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public CommentThreadsList getThreadsByChannel(String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("channelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public CommentThreadsList getThreadsByVideo(String videoId, String pageToken) throws IOException, YouTubeErrorException {
        setField("videoId", videoId);
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
        Replies replies;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasReplies() {
            return replies != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public Replies getReplies() {
            return replies;
        }

        public static class Snippet implements Serializable {
            String channelId, videoId;
            boolean canReply, isPublic;
            CommentsList.Item topLevelComment;
            int totalReplyCount;

            public String getChannelId() {
                return channelId;
            }

            public String getVideoId() {
                return videoId;
            }

            public boolean isCanReply() {
                return canReply;
            }

            public boolean isPublic() {
                return isPublic;
            }

            public CommentsList.Item getTopLevelComment() {
                return topLevelComment;
            }

            public int getTotalReplyCount() {
                return totalReplyCount;
            }
        }
        public static class Replies implements Serializable {
            CommentsList.Item[] comments;

            public CommentsList.Item[] getComments() {
                return comments;
            }
        }
    }
}
