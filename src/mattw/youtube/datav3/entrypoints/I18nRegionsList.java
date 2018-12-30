package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/i18nRegions/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class I18nRegionsList extends YouTubeResource {

    Item[] items;

    public I18nRegionsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("i18nRegions");
    }

    public I18nRegionsList get() throws IOException, YouTubeErrorException {
        return super.get();
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
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
            String gl, name;

            public String getGl() {
                return gl;
            }

            public String getName() {
                return name;
            }
        }
    }
}
