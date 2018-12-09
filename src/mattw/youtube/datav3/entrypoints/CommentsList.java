package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/comments/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class CommentsList extends YouTubeResource {

    public final static int MAX_RESULTS = 100;

    public final static String FORMAT_HTML = "html";
    public final static String FORMAT_PLAIN = "plainText";

    public String nextPageToken;
    public Item[] items;

    public CommentsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 100);
        setDataPath("comments");
    }

    public boolean hasItems() { return items != null; }

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

    public class Item extends BaseItem {

        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class Snippet {
            public String authorDisplayName;
            public String authorProfileImageUrl;
            public String authorChannelUrl;
            public AuthorChannelId authorChannelId;
            public String channelId;
            public String videoId;
            public String textDisplay;
            public String textOriginal;
            public String parentId;
            public boolean canRate;
            public String viewerRating;
            public int likeCount;
            public String moderationStatus;
            public Date publishedAt;
            public Date updatedAt;
            public class AuthorChannelId {
                public String value;
            }
        }
    }
}
