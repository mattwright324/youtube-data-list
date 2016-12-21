package mattw.youtube.datav3.list;

import mattw.youtube.datav3.YoutubeData;

public class VideoAbuseReportReasonsList extends ListResponse {
	
	public static final String PART_SNIPPET = "snippet"; // cost: 2
	
	public Item[] items;
	
	public static String getVideoAbuseReportReasonsUrl(String apiKey, String part) {
		return YoutubeData.BASE_API+"/videoAbuseReportReasons?key="+apiKey+"&part="+part;
	}
	
	public class Item extends ListResponse.Item {
		
		public Snippet snippet;
		
		public boolean hasSnippet() {
			return snippet != null;
		}
		
		public class Snippet {
			public String label;
			public SecondaryReason[] secondaryReasons;
			public class SecondaryReason {
				public String id, label;
			}
		}
		
	}
	
}
