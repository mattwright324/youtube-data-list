package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

public class I18nLanguagesList extends YouTubeResource {

    public final static String PART_SNIPPET = "snippet"; // cost: 2

    {
        this.dataPath = "i18nLanguages";
    }

    public Item[] items;

    public I18nLanguagesList(YouTubeData3 data) {
        super(data);
    }

    public boolean hasItems() { return items != null; }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class Snippet {
            public String hl;
            public String name;
        }
    }
}
