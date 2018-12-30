package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/videoCategories/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class VideoCategoriesList extends YouTubeResource implements Serializable {

    String nextPageToken, prevPageToken;
    Item[] items;

    public VideoCategoriesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("videoCategories");
    }

    public VideoCategoriesList getByIds(String ids, String pageToken) throws IOException, YouTubeErrorException {
        setField("id", ids);
        setField("pageToken", pageToken);
        return get();
    }

    public VideoCategoriesList getByRegionCode(String regionCode, String pageToken) throws IOException, YouTubeErrorException {
        setField("regionCode", regionCode);
        setField("pageToken", pageToken);
        return get();
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
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
            String channelId, title, assignable;

            public String getChannelId() {
                return channelId;
            }

            public String getTitle() {
                return title;
            }

            public String getAssignable() {
                return assignable;
            }
        }
    }
}
