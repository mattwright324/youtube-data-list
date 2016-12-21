package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class PlaylistItemsList extends ListResponse {
	
	public final static int MAX_RESULTS = 50;
	public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	public final static String PART_STATUS = "status"; // cost: 2
	
	public String nextPageToken;
	public String prevPageToken;
	public Item[] items;
	
	public static String getPlaylistItemsUrl(String apiKey, String part, String playlistId, int maxResults) {
		return YoutubeData.BASE_API+"/playlistItems?key="+apiKey+"&part="+part+"&playlistId="+playlistId+"&maxResults="+maxResults;
	}
	
	public static String getPlaylistItemsUrl(String apiKey, String part, String playlistId, int maxResults, String pageToken) {
		return getPlaylistItemsUrl(apiKey, part, playlistId, maxResults)+"&pageToken="+pageToken;
	}
	
	public class Item extends ListResponse.Item {
		public Snippet snippet;
		public ContentDetails contentDetails;
		public Status status;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public boolean hasContentDetails() {
			return contentDetails != null;
		}
		
		public boolean hasStatus() {
			return status != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public Date publishedAt;
			public String channelId;
			public String title;
			public String description;
			public Thumbnails thumbnails;
			public String channelTitle;
			public String playlistId;
			public int position;
			public ResourceId resourceId;
			public class ResourceId {
				public String kind;
				public String videoId;
			}
		}
		public class ContentDetails extends ListResponse.Part {
			public String videoId;
			public String startAt;
			public String endAt;
			public String note;
		}
		public class Status extends ListResponse.Part {
			public String privacyStatus;
		}
	}
	
}
