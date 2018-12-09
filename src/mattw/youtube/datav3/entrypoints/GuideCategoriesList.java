package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @link https://developers.google.com/youtube/v3/docs/guideCategories/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class GuideCategoriesList extends YouTubeResource {

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public GuideCategoriesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("guideCategories");
    }

    public boolean hasItems() { return items != null; }

    public GuideCategoriesList getByChannelIds(String part, String ids) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("ids", ids);
        return get();
    }

    public GuideCategoriesList getByRegionCode(String part, String regionCode) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("regionCode", regionCode);
        return get();
    }

    public class Item extends BaseItem {

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
