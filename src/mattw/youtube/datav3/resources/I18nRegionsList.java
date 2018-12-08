package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class I18nRegionsList extends YouTubeResource {

    {
        this.dataPath = "i18nRegions";
    }

    public Item[] items;

    public I18nRegionsList(YouTubeData3 data) {
        super(data);
    }

    public boolean hasItems() { return items != null; }

    public I18nRegionsList get(String part) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        return get();
    }

    public class Item extends YouTubeResource.Item {

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
