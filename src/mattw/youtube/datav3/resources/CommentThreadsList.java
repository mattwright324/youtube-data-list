package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeErrorException;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;

public class CommentThreadsList extends YouTubeResource {

    public final static int MAX_RESULTS = 100;
    public final static String PART_REPLIES = "replies"; // cost: 2
    public final static String PART_SNIPPET = "snippet"; // cost: 2

    public final static String ORDER_TIME = "time"; // default value
    public final static String ORDER_RELEVANCE = "relevance";

    public final static String FORMAT_HTML = "html"; // default value
    public final static String FORMAT_PLAIN = "plainText";

    {
        this.dataPath = "commentThreads";
    }

    public String nextPageToken;
    public Item[] items;

    public CommentThreadsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 100);
        fields.put("order", ORDER_RELEVANCE);
    }

    public boolean hasItems() { return items != null; }

    public CommentThreadsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public CommentThreadsList order(String order) {
        fields.put("order", order);
        return this;
    }

    public CommentThreadsList searchTerms(String searchTerms) {
        fields.put("searchTerms", searchTerms);
        return this;
    }

    public CommentThreadsList getThreadsRelatedToChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("allThreadsRelatedToChannelId", channelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public CommentThreadsList getThreadsByChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("channelId", channelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public CommentThreadsList getThreadsByVideo(String part, String videoId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("videoId", videoId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {

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
