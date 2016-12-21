package mattw.youtube.datav3.list;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import mattw.youtube.datav3.YoutubeData;

public class SearchList extends ListResponse {
	
	public final static int MAX_RESULTS = 50;
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	
	public final static String TYPE_ALL = "";
	public final static String TYPE_CHANNEL = "channel";
	public final static String TYPE_PLAYLIST = "playlist";
	public final static String TYPE_VIDEO = "video";
	
	public final static String ORDER_DATE = "date";
	public final static String ORDER_RATING = "rating";
	public final static String ORDER_RELEVANCE = "relevance";
	public final static String ORDER_TITLE = "title";
	public final static String ORDER_VIDEO_COUNT = "videoCount";
	public final static String ORDER_VIEW_COUNT = "viewCount";
	
	//public final static String LISCENCE_ANY = "any";
	//public final static String LISCENCE_CREATIVE_COMMON = "creativeCommon";
	//public final static String LISCENCE_YOUTUBE = "youtube";
	
	public String nextPageToken;
	public String prevPageToken;
	public String regionCode;
	public Item[] items;
	
	public static String getSearchUrl(String apiKey, String part, String q, int maxResults, String pageToken, String order, String type) {
		return YoutubeData.BASE_API+"/search?key="+apiKey+"&part="+part+"&q="+q+"&maxResults="+maxResults+"&pageToken="+pageToken+"&order="+order+"&type="+type;
	}
	
	public static String getSearchUrl(String apiKey, String part, String q, int maxResults, String pageToken, String order, String type, String publishedBefore, String publishedAfter) {
		return getSearchUrl(apiKey, part, q, maxResults, pageToken, order, type)+"&publishedBefore="+publishedBefore+"&publishedAfter="+publishedAfter;
	}
	
	public static String getSearchAtLocationUrl(String apiKey, String part, String q, int maxResults, String pageToken, String order, String type, String location, String locationRadius) {
		return getSearchUrl(apiKey, part, q, maxResults, pageToken, order, type)+"&location="+location+"&locationRadius="+locationRadius;
	}
	
	public static String getSearchAtLocationUrl(String apiKey, String part, String q, int maxResults, String pageToken, String order, String type, String location, String locationRadius, String publishedBefore, String publishedAfter) {
		return getSearchAtLocationUrl(apiKey, part, q, maxResults, pageToken, order, type, location, locationRadius)+"&publishedBefore="+publishedBefore+"&publishedAfter="+publishedAfter;
	}
	
	public class Item {
		public String kind;
		public String etag;
		public ID id;
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class ID {
			public String kind;
			public String videoId;
			public String channelId;
			public String playlistId;
		}
		public class Snippet {
			public Date publishedDate;
			public String channelId;
			public String title;
			public String description;
			public Thumbnails thumbnails;
			public String channelTitle;
			public String liveBroadcastContent;
			
			public class Thumbnails {
				@SerializedName("default")
				public Thumbnail default_thumb;
				public Thumbnail medium;
				public Thumbnail high;
				public class Thumbnail {
					public String url;
					public int width;
					public int height;
				}
			}
		}
	}
	
}
