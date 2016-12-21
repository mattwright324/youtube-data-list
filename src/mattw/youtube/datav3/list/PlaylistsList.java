package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class PlaylistsList extends ListResponse {
	
	public final static int MAX_RESULTS = 50;
	public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	public final static String PART_STATUS = "status"; // cost: 2
	public final static String PART_PLAYER = "player"; // cost: 0
	//public final static String PART_LOCALIZATIONS = "localizations"; // cost: 2
	
	public String nextPageToken;
	public String prevPageToken;
	public Item[] items;
	
	public static String getPlaylistsByChannelUrl(String apiKey, String part, String channelId, int maxResults) {
		return YoutubeData.BASE_API+"/playlists?key="+apiKey+"&part="+part+"&channelId="+channelId+"&maxResults="+maxResults;
	}
	
	public static String getPlaylistsByChannelUrl(String apiKey, String part, String channelId, int maxResults, String pageToken) {
		return getPlaylistsByChannelUrl(apiKey, part, channelId, maxResults, pageToken)+"&pageToken="+pageToken;
	}
	
	public static String getPlaylistsByPlaylistUrl(String apiKey, String part, String playlistId, int maxResults) {
		return YoutubeData.BASE_API+"/playlists?key="+apiKey+"&part="+part+"&id="+playlistId+"&maxResults="+maxResults;
	}
	
	public static String getPlaylistsByPlaylistUrl(String apiKey, String part, String playlistId, int maxResults, String pageToken) {
		return getPlaylistsByPlaylistUrl(apiKey, part, playlistId, maxResults)+"&pageToken="+pageToken;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		public Status status;
		public ContentDetails contentDetails;
		public Player player;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public boolean hasStatus() {
			return status != null;
		}
		
		public boolean hasContentDetails() {
			return contentDetails != null;
		}
		
		public boolean hasPlayer() {
			return player != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public Date publishedAt;
			public String channelId;
			public String title;
			public String description;
			public Thumbnails thumbnails;
			public String channelTitle;
			public String[] tags;
			public String defaultLanguage;
			public Localized localized;
			public class Localized {
				public String title;
				public String description;
			}
		}
		
		public class Status {
			public String privacyStatus;
		}
		
		public class ContentDetails {
			public int itemCount;
		}
		
		public class Player {
			public String embedHtml;
		}
		
		public class Localizations {
			// Unknown values?
			public class Localized {
				public String title;
				public String description;
			}
		}
	}
	
}
