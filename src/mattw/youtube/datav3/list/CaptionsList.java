package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class CaptionsList extends ListResponse {
	
	public final static String PART_SNIPPET = "snippet"; // cost: 1
	
	public Item[] items;
	
	public static String getCaptionsUrl(String apiKey, String part, String videoId) {
		return YoutubeData.BASE_API+"/captions?key="+apiKey+"&part="+part+"&videoId="+videoId;
	}
	public static String getCaptionsUrl(String apiKey, String part, String videoId, String ids) {
		return getCaptionsUrl(apiKey, part, videoId)+"&id="+ids;
	}
	
	public class Item extends ListResponse.Item {
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public String videoId;
			public Date lastUpdated;
			public String trackKind;
			public String language;
			public String name;
			public String audioTrackType;
			public boolean isCC;
			public boolean isLarge;
			public boolean isEasyReader;
			public boolean isDraft;
			public boolean isAutoSynced;
			public String status;
		}
	}
	
}
