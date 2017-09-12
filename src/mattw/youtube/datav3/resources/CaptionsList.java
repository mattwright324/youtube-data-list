package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;

public class CaptionsList extends YouTubeResource {

    public final static String PART_SNIPPET = "snippet"; // cost: 1

    {
        this.dataPath = "captions";
    }

    public CaptionsList(YouTubeData3 data) {
        super(data);
    }

    public Item[] items;

    public CaptionsList get(String videoId) throws IOException {
        fields.put("videoId", videoId);
        return get();
    }

    public CaptionsList get(String part, String videoId) throws IOException {
        fields.put("part", part);
        return get(videoId);
    }

    public CaptionsList get(String part, String videoId, String ids) throws IOException {
        fields.put("id", ids);
        return get(part, videoId, ids);
    }
}
