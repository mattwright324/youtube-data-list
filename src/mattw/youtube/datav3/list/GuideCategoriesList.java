package mattw.youtube.datav3.list;

import mattw.youtube.datav3.YoutubeData;

public class GuideCategoriesList extends ListResponse {
	
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	
	public String nextPageToken;
	public String prevPageToken;
	
	public static String getGuideCategoriesByChannelIdsUrl(String apiKey, String part, String ids) {
		return YoutubeData.BASE_API+"/guideCategories?key="+apiKey+"&part="+part+"&id="+ids;
	}
	
	public static String getGuideCategoriesByRegionCodeUrl(String apiKey, String part, String regionCode) {
		return YoutubeData.BASE_API+"/guideCategories?key="+apiKey+"&part="+part+"&regionCode="+regionCode;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class Snippet {
			public String channelId;
			public String title;
		}
		
	}
	
}
