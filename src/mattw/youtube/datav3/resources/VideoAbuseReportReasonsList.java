package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;

public class VideoAbuseReportReasonsList extends YouTubeResource {

    public static final String PART_SNIPPET = "snippet"; // cost: 2

    {
        this.dataPath = "videoAbuseReportReasons";
    }

    public Item[] items;

    public VideoAbuseReportReasonsList(YouTubeData3 data) {
        super(data);
    }

    public VideoAbuseReportReasonsList get(String part) throws IOException {
        fields.put("part", part);
        return get();
    }

    public boolean hasItems() { return items != null; }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public class Snippet {
            public String label;
            public SecondaryReason[] secondaryReasons;
            public class SecondaryReason {
                public String id, label;
            }
        }

    }
}
