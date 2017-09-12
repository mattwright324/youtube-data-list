package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;

public class GuideCategoriesList extends YouTubeResource {

    public final static String PART_SNIPPET = "snippet"; // cost: 2

    {
        this.dataPath = "guideCategories";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public GuideCategoriesList(YouTubeData3 data) {
        super(data);
    }

    public boolean hasItems() { return items != null; }

    public GuideCategoriesList getByChannelIds(String part, String ids) throws IOException {
        fields.put("part", part);
        fields.put("ids", ids);
        return get();
    }

    public GuideCategoriesList getByRegionCode(String part, String regionCode) throws IOException {
        fields.put("part", part);
        fields.put("regionCode", regionCode);
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
        }

    }
}
