package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @link https://developers.google.com/youtube/v3/docs/commentThreads/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET, Parts.REPLIES})
public class CommentThreadsList extends YouTubeResource {

    public final static int MAX_RESULTS = 100;

    public final static String ORDER_TIME = "time"; // default value
    public final static String ORDER_RELEVANCE = "relevance";

    public final static String FORMAT_HTML = "html"; // default value
    public final static String FORMAT_PLAIN = "plainText";

    public String nextPageToken;
    public Item[] items;

    public CommentThreadsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 100);
        setField("order", ORDER_TIME);
        setDataPath("commentThreads");
    }

    public boolean hasItems() { return items != null; }

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

    public CommentThreadsList getThreadsRelatedToChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("allThreadsRelatedToChannelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public CommentThreadsList getThreadsByChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("channelId", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public CommentThreadsList getThreadsByVideo(String part, String videoId, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("videoId", videoId);
        setField("pageToken", pageToken);
        return get();
    }

    public class Item extends BaseItem {

        public Snippet snippet;
        public Replies replies;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasReplies() {
            return replies != null;
        }

        public class Snippet{
            public String channelId;
            public String videoId;
            public boolean canReply;
            public CommentsList.Item topLevelComment;
            public int totalReplyCount;
            public boolean isPublic;
        }
        public class Replies {
            public CommentsList.Item[] comments;
        }
    }
}
