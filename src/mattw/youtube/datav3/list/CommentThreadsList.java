package mattw.youtube.datav3.list;

import mattw.youtube.datav3.YoutubeData;

public class CommentThreadsList extends ListResponse {
	
	public final static int MAX_RESULTS = 100;
	public final static String PART_REPLIES = "replies"; // cost: 2
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	
	public final static String ORDER_TIME = "time"; // default value
	public final static String ORDER_RELEVANCE = "relevance";
	
	public final static String FORMAT_HTML = "html"; // default value
	public final static String FORMAT_PLAIN = "plainText";
	
	
	public String nextPageToken;
	public Item[] items;
	
	private static String getCommentThreadsUrl(String apiKey, String part) {
		return YoutubeData.BASE_API+"/commentThreads?key="+apiKey+"&part="+part;
	}
	
	public static String getCommentThreadsRelatedToChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken) {
		return getCommentThreadsUrl(apiKey, part)+"&allThreadsRelatedToChannelId="+channelId+"&maxResults="+maxResults+"&pageToken="+pageToken;
	}
	public static String getCommentThreadsRelatedToChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order) {
		return getCommentThreadsRelatedToChannelIdUrl(apiKey, part, channelId, maxResults, pageToken)+"&order="+order;
	}
	public static String getCommentThreadsRelatedToChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order, String searchTerms) {
		return getCommentThreadsRelatedToChannelIdUrl(apiKey, part, channelId, maxResults, pageToken, order)+"&searchTerms="+searchTerms;
	}
	public static String getCommentThreadsRelatedToChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) {
		return getCommentThreadsRelatedToChannelIdUrl(apiKey, part, channelId, maxResults, pageToken, order, searchTerms)+"&textFormat="+textFormat;
	}
	
	public static String getCommentThreadsByChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken) {
		return getCommentThreadsUrl(apiKey, part)+"&channelId="+channelId+"&maxResults="+maxResults+"&pageToken="+pageToken;
	}
	public static String getCommentThreadsByChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order) {
		return getCommentThreadsByChannelIdUrl(apiKey, part, channelId, maxResults, pageToken)+"&order="+order;
	}
	public static String getCommentThreadsByChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order, String searchTerms) {
		return getCommentThreadsByChannelIdUrl(apiKey, part, channelId, maxResults, pageToken, order)+"&searchTerms="+searchTerms;
	}
	public static String getCommentThreadsByChannelIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) {
		return getCommentThreadsByChannelIdUrl(apiKey, part, channelId, maxResults, pageToken, order, searchTerms)+"&textFormat="+textFormat;
	}
	
	public static String getCommentThreadsByVideoIdUrl(String apiKey, String part, String videoId, int maxResults, String pageToken) {
		return getCommentThreadsUrl(apiKey, part)+"&videoId="+videoId+"&maxResults="+maxResults+"&pageToken="+pageToken;
	}
	public static String getCommentThreadsByVideoIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order) {
		return getCommentThreadsByVideoIdUrl(apiKey, part, channelId, maxResults, pageToken)+"&order="+order;
	}
	public static String getCommentThreadsByVideoIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order, String searchTerms) {
		return getCommentThreadsByVideoIdUrl(apiKey, part, channelId, maxResults, pageToken, order)+"&searchTerms="+searchTerms;
	}
	public static String getCommentThreadsByVideoIdUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) {
		return getCommentThreadsByVideoIdUrl(apiKey, part, channelId, maxResults, pageToken, order, searchTerms)+"&textFormat="+textFormat;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		public Replies replies;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public boolean hasReplies() {
			return replies != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public String channelId;
			public String videoId;
			public boolean canReply;
			public CommentsList.Item topLevelComment;
			public int totalReplyCount;
			public boolean isPublic;
		}
		public class Replies extends ListResponse.Part {
			public CommentsList.Item[] comments;
		}
	}
	
}
