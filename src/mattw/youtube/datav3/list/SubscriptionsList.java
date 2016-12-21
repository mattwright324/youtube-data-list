package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class SubscriptionsList extends ListResponse {
	
	public static final int MAX_RESULTS = 50;
	public static final String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
	public static final String PART_SNIPPET = "snippet"; // cost: 2
	public static final String PART_SUBSCRIBER_SNIPPET = "subscriberSnippet"; // cost: 2
	
	public static final String ORDER_ALPHABETICAL = "alphabetical";
	public static final String ORDER_RELEVANCE = "relevance";
	public static final String ORDER_UNREAD = "unread";
	
	public String nextPageToken;
	public String prevPageToken;
	public Item[] items;
	
	public static String getSubscriptionsByChannelUrl(String apiKey, String part, String channelId, String forChannelId, int maxResults, String order, String pageToken) {
		return YoutubeData.BASE_API+"/subscriptions?key="+apiKey+"&part="+part+"&channelId="+channelId+validateString("&forChannelId=", forChannelId)+"&maxResults="+maxResults+validateString("&order=", order)+"&pageToken="+pageToken;
	}
	
	public static String getSubscriptionsByIdsUrl(String apiKey, String part, String ids, String forChannelId, int maxResults, String order, String pageToken) {
		return YoutubeData.BASE_API+"/subscriptions?key="+apiKey+"&part="+part+"&id="+ids+validateString("&forChannelId=", forChannelId)+"&maxResults="+maxResults+validateString("&order=", order)+"&pageToken="+pageToken;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		public ContentDetails contentDetails;
		public SubscriberSnippet subscriberSnippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public boolean hasContentDetails() {
			return contentDetails != null;
		}
		
		public boolean hasSubscriberSnippet() {
			return subscriberSnippet != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public Date publishedAt;
			public String channelTitle;
			public String title;
			public String description;
			public ResourceId resourceId;
			public String channelId;
			public Thumbnails thumbnails;
			public class ResourceId {
				public String kind;
				public String channelId;
			}
		}
		public class ContentDetails extends ListResponse.Part {
			public int totalItemCount;
			public int newItemCount;
			public String activityType;
		}
		public class SubscriberSnippet extends ListResponse.Part {
			public String title;
			public String description;
			public String channelId;
			public Thumbnails thumbnails;
		}
	}
	
}
