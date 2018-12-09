package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @link https://developers.google.com/youtube/v3/docs/videoAbuseReportReasons/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class VideoAbuseReportReasonsList extends YouTubeResource {

    public Item[] items;

    public VideoAbuseReportReasonsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("videoAbuseReportReasons");
    }

    public VideoAbuseReportReasonsList get(String part) throws IOException, YouTubeErrorException {
        setField("part", part);
        return get();
    }

    public boolean hasItems() { return items != null; }

    public class Item extends BaseItem {

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
