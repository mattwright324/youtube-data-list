package mattw.youtube.datav3.list;

import java.text.SimpleDateFormat;

public abstract class ListResponse {
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss:sZ");
	public static final String PART_ID = "id"; // cost: 0
	
	public String kind;
	public String etag;
	public PageInfo pageInfo;
	//public Item[] items;
	
	public boolean hasPageInfo() {
		return pageInfo != null;
	}
	
	/**
	 * Determines if the value is not null and not empty, returning nothing is no value specified.
	 * @param post
	 * @param value
	 * @return
	 */
	protected static String validateString(String post, String value) {
		return value != null && !value.equals("") ? post+value : "";
	}
	
	//public boolean hasItems() {
	//	return items != null && items.length > 0;
	//}
	
	public class PageInfo {
		public int totalResults;
		public int resultsPerPage;
	}
	
	public abstract class Part {}
	
	public abstract class Item {
		public String kind;
		public String etag;
		public String id;
	}
}
