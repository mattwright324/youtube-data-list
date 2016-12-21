package mattw.youtube.datav3.list;

import mattw.youtube.datav3.YoutubeData;

public class I18nRegionsList extends ListResponse {
	
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	
	public static String getI18nRegionsUrl(String apiKey, String part) {
		return YoutubeData.BASE_API+"/i18nRegions?key="+apiKey+"&part="+part;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public String gl;
			public String name;
		}
	}
}
