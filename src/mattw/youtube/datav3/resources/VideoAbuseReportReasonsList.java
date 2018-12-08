package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class VideoAbuseReportReasonsList extends YouTubeResource {

    {
        this.dataPath = "videoAbuseReportReasons";
    }

    public Item[] items;

    public VideoAbuseReportReasonsList(YouTubeData3 data) {
        super(data);
    }

    public VideoAbuseReportReasonsList get(String part) throws IOException, YouTubeErrorException {
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
