package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class CaptionsList extends YouTubeResource {

    {
        this.dataPath = "captions";
    }

    public Item[] items;

    public CaptionsList(YouTubeData3 data) {
        super(data);
    }

    public boolean hasItems() { return items != null; }

    public CaptionsList get(String videoId) throws IOException, YouTubeErrorException {
        fields.put("videoId", videoId);
        return get();
    }

    public CaptionsList get(String part, String videoId) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        return get(videoId);
    }

    public CaptionsList get(String part, String videoId, String ids) throws IOException, YouTubeErrorException {
        fields.put("id", ids);
        return get(part, videoId);
    }

    public class Item extends YouTubeResource.Item {
        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class Snippet {
            public String videoId;
            public Date lastUpdated;
            public String trackKind;
            public String language;
            public String name;
            public String audioTrackType;
            public boolean isCC;
            public boolean isLarge;
            public boolean isEasyReader;
            public boolean isDraft;
            public boolean isAutoSynced;
            public String status;
        }
    }
}
