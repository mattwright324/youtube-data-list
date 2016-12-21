package mattw.youtube.datav3.list;

import mattw.youtube.datav3.YoutubeData;

public class I18nLanguagesList extends ListResponse {
	
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	
	public static String getI18nLanguagesUrl(String apiKey, String part) {
		return YoutubeData.BASE_API+"/i18nLanguages?key="+apiKey+"&part="+part;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public String hl;
			public String name;
		}
	}
}
