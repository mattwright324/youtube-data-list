package mattw.youtube.datav3.list;

import mattw.youtube.datav3.YoutubeData;

public class ChannelSectionsList extends ListResponse {
	
	public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
	// public final static String PART_LOCALIZATIONS = "localizations";
	public final static String PART_SNIPPET = "snippet"; // cost: 2
	public final static String PART_TARGETING = "targeting"; // cost: 2
	
	public Item[] items;
	
	public static String getChannelSectionsByChannelIdUrl(String apiKey, String part, String channelId) {
		return YoutubeData.BASE_API+"/channelSections?key="+apiKey+"&part="+part+"&channelId="+channelId;
	}
	
	public static String getChannelSectionsBySectionIdsUrl(String apiKey, String part, String sectionId) {
		return YoutubeData.BASE_API+"/channelSections?key="+apiKey+"&part="+part+"&id="+sectionId;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		public ContentDetails contentDetails;
		// public Localizations localizations;
		public Targeting targeting;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public boolean hasContentDetails() {
			return contentDetails != null;
		}
		
		public boolean hasTargeting() {
			return targeting != null;
		}
		
		public class Snippet extends ListResponse.Part {
			public String type;
			public String style;
			public String channelId;
			public String title;
			public int position;
			public String defaultLanguage;
			public Localized localized;
			public class Localized {
				public String title;
			}
		}
		public class ContentDetails extends ListResponse.Part {
			public String[] playlists;
			public String[] channels;
		
		}
		public class Localizations extends ListResponse.Part {
			// TODO Unknown keys.
			public class Localize {
				public String title;
			}
		}
		public class Targeting extends ListResponse.Part {
			public String[] languages;
			public String[] regions;
			public String[] countries;
		}
	}
	
}
