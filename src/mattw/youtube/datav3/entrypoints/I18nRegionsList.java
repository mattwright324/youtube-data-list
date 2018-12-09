package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @link https://developers.google.com/youtube/v3/docs/i18nRegions/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class I18nRegionsList extends YouTubeResource {

    public Item[] items;

    public I18nRegionsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("i18nRegions");
    }

    public boolean hasItems() { return items != null; }

    public I18nRegionsList get(String part) throws IOException, YouTubeErrorException {
        setField("part", part);
        return get();
    }

    public class Item extends BaseItem {

        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class Snippet {
            public String gl;
            public String name;
        }
    }
}
