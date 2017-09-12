package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;
import java.util.Date;

public class CommentsList extends YouTubeResource {

    public final static int MAX_RESULTS = 100;
    public final static String PART_SNIPPET = "snippet"; // cost: 2

    public final static String FORMAT_HTML = "html";
    public final static String FORMAT_PLAIN = "plainText";

    {
        this.dataPath = "comments";
    }

    public String nextPageToken;
    public Item[] items;

    public CommentsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 100);
    }

    public boolean hasItems() { return items != null; }

    public CommentsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public CommentsList get(String part, String id, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("id", id);
        fields.put("pageToken", pageToken);
        return get();
    }

    public CommentsList get(String part, String id, String pageToken, String textFormat) throws IOException {
        fields.put("textFormat", textFormat);
        return get(part, id, pageToken);
    }

    public CommentsList getByParentId(String part, String parentId, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("parentId", parentId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public CommentsList getByParentId(String part, String parentId, String pageToken, String textFormat) throws IOException {
        fields.put("textFormat", textFormat);
        return get(part, parentId, pageToken);
    }

    public class Item extends YouTubeResource.Item {

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
