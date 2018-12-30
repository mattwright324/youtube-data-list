package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/guideCategories/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class GuideCategoriesList extends YouTubeResource {

    String nextPageToken, prevPageToken;
    Item[] items;

    public GuideCategoriesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("guideCategories");
    }

    public GuideCategoriesList getByChannelIds(String ids) throws IOException, YouTubeErrorException {
        setField("ids", ids);
        return get();
    }

    public GuideCategoriesList getByRegionCode(String regionCode) throws IOException, YouTubeErrorException {
        setField("regionCode", regionCode);
        return get();
    }

    public boolean hasItems() {
        return items != null;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public Item[] getItems() {
        return items;
    }

    public static class Item extends BaseItem {

        Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public static class Snippet implements Serializable {
            String channelId, title;

            public String getChannelId() {
                return channelId;
            }

            public String getTitle() {
                return title;
            }
        }

    }
}
