package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class ActivitiesList extends ListResponse {
	
	public static final int MAX_RESULTS = 50;
	public static final String PART_SNIPPET = "snippet"; // cost: 2
	public static final String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
	
	public String nextPageToken;
	public String prevPageToken;
	public Item[] items;
	
	public static String getActivitiesUrl(String apiKey, String part, String channelId, int maxResults) {
		return YoutubeData.BASE_API+"/activities?key="+apiKey+"&part="+part+"&channelId="+channelId+"&maxResults="+maxResults;
	}
	
	public static String getActivitiesUrl(String apiKey, String part, String channelId, int maxResults, String pageToken) {
		return getActivitiesUrl(apiKey, part, channelId, maxResults)+"&pageToken="+pageToken;
	}
	
	public static String getActivitiesUrl(String apiKey, String part, String channelId, int maxResults, String pageToken, Date publishedAfter, Date publishedBefore) {
		return getActivitiesUrl(apiKey, part, channelId, maxResults, pageToken)+"&publishedAfter="+(publishedAfter != null ? dateFormat.format(publishedAfter):"")+"&publshedBefore"+(publishedBefore != null ? dateFormat.format(publishedBefore):"");
	}
	
	public class Item extends ListResponse.Item {

		public Snippet snippet;
		public ContentDetails contentDetails;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public boolean hasContentDetails() {
			return contentDetails != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public Date publishedAt;
			public String channelId;
			public String title;
			public String description;
			public Thumbnails thumbnails;
			public String channelTitle;
			public String type;
			public String groupId;
		}
		
		public class ContentDetails extends ListResponse.Part {
			public Upload upload;
			public ContentEvent like;
			public ContentEvent favorite;
			public ContentEvent comment;
			public ContentEvent subscription;
			public ContentEvent playlistItem;
			public Recommendation recommendation;
			public ContentEvent bulletin;
			public Social social;
			public ContentEvent channelItem;
			
			public class ResourceId {
				public String kind;
				public String videoId;
				public String channelId;
				public String playlistId;
			}
			public class Upload {
				public String videoId;
			}
			public class ContentEvent {
				public ResourceId resourceId;
			}
			public class Social {
				public String type;
				public ResourceId resourceId;
				public String author;
				public String referenceUrl;
				public String imageUrl;
			}
			public class Recommendation {
				public ResourceId resourceId;
				public String reason;
				public ResourceId seedResourceId;
			}
		}
	}
}
