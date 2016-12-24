package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class CommentsList extends ListResponse {
	
	/*
	 * id filter is the only usable one.
	 * parentId is listed but noted that it may be supported in the future.
	 */
	
	public final static int MAX_RESULTS = 100;
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	
	public final static String FORMAT_HTML = "html";
	public final static String FORMAT_PLAIN = "plainText";
	
	public String nextPageToken;
	public Item[] items;
	
	public static String getCommentsUrl(String apiKey, String part, String id, int maxResults) {
		return YoutubeData.BASE_API+"/comments?key="+apiKey+"&part="+part+"&id="+id+"&maxResults="+maxResults;
	}
	
	public static String getCommentsUrl(String apiKey, String part, String id, int maxResults, String pageToken) {
		return getCommentsUrl(apiKey, part, id, maxResults)+"&pageToken="+pageToken;
	}
	
	public static String getCommentsUrl(String apiKey, String part, String id, int maxResults, String pageToken, String textFormat) {
		return getCommentsUrl(apiKey, part, id, maxResults)+"&pageToken="+pageToken+"&textFormat="+textFormat;
	}
	
	public static String getCommentsByParentIdUrl(String apiKey, String part, String parentId, int maxResults, String pageToken) {
		return YoutubeData.BASE_API+"/comments?key="+apiKey+"&part="+part+"&parentId="+parentId+"&maxResults="+maxResults+"&pageToken"+pageToken;
	}
	
	public static String getCommentsByParentIdUrl(String apiKey, String part, String parentId, int maxResults, String pageToken, String textFormat) {
		return getCommentsByParentIdUrl(apiKey, part, parentId, maxResults, pageToken)+"&textFormat="+textFormat;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class Snippet extends ListResponse.Part {
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
