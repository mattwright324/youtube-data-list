package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @link https://developers.google.com/youtube/v3/docs/i18nLanguages/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class I18nLanguagesList extends YouTubeResource {

    public Item[] items;

    public I18nLanguagesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("i18nLanguages");
    }

    public boolean hasItems() { return items != null; }

    public I18nLanguagesList get() throws IOException, YouTubeErrorException {
        return super.get();
    }

    public class Item extends BaseItem {

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
