package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;

public class VideoCategoriesList extends YouTubeResource {

    public final static String PART_SNIPPET = "snippet"; // cost: 2

    {
        this.dataPath = "videoCategories";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public VideoCategoriesList(YouTubeData3 data) {
        super(data);
    }

    public boolean hasItems() { return items != null; }

    public VideoCategoriesList getByIds(String part, String ids, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("id", ids);
        fields.put("pageToken", pageToken);
        return get();
    }

    public VideoCategoriesList getByRegionCode(String part, String regionCode, String pageToken) throws IOException {
        fields.put("part", part);
        fields.put("regionCode", regionCode);
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class Snippet {
            public String channelId;
            public String title;
            public String assignable;
        }
    }
}
