package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/captions/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class CaptionsList extends YouTubeResource implements Serializable {

    Item[] items;

    public CaptionsList(YouTubeData3 data) {
        super(data);
        setCost(50);
        setDataPath("captions");
    }

    public CaptionsList get(String videoId) throws IOException, YouTubeErrorException {
        setField("videoId", videoId);
        return get();
    }

    public CaptionsList get(String videoId, String ids) throws IOException, YouTubeErrorException {
        setField("id", ids);
        return get(videoId);
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
            Date lastUpdated;
            String videoId, trackKind, language, name, audioTrackType, status;
            boolean isCC, isLarge, isEasyReader, isDraft, isAutoSynced;

            public Date getLastUpdated() {
                return lastUpdated;
            }

            public String getVideoId() {
                return videoId;
            }

            public String getTrackKind() {
                return trackKind;
            }

            public String getLanguage() {
                return language;
            }

            public String getName() {
                return name;
            }

            public String getAudioTrackType() {
                return audioTrackType;
            }

            public String getStatus() {
                return status;
            }

            public boolean isCC() {
                return isCC;
            }

            public boolean isLarge() {
                return isLarge;
            }

            public boolean isEasyReader() {
                return isEasyReader;
            }

            public boolean isDraft() {
                return isDraft;
            }

            public boolean isAutoSynced() {
                return isAutoSynced;
            }
        }
    }
}
