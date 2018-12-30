package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/i18nLanguages/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class I18nLanguagesList extends YouTubeResource {

    Item[] items;

    public I18nLanguagesList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("i18nLanguages");
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public Item[] getItems() {
        return items;
    }

    public I18nLanguagesList get() throws IOException, YouTubeErrorException {
        return super.get();
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
            String hl, name;

            public String getHl() {
                return hl;
            }

            public String getName() {
                return name;
            }
        }
    }
}
