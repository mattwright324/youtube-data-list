package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @link https://developers.google.com/youtube/v3/docs/videoCategories/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class VideoCategoriesList extends YouTubeResource {

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public VideoCategoriesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("videoCategories");
    }

    public boolean hasItems() { return items != null; }

    public VideoCategoriesList getByIds(String part, String ids, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("id", ids);
        setField("pageToken", pageToken);
        return get();
    }

    public VideoCategoriesList getByRegionCode(String part, String regionCode, String pageToken) throws IOException, YouTubeErrorException {
        setField("part", part);
        setField("regionCode", regionCode);
        setField("pageToken", pageToken);
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
            public String assignable;
        }
    }
}
