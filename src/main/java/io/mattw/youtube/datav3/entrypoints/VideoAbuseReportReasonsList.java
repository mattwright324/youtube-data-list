package io.mattw.youtube.datav3.entrypoints;

import io.mattw.youtube.datav3.AcceptsParts;
import io.mattw.youtube.datav3.Parts;
import io.mattw.youtube.datav3.YouTubeData3;
import io.mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/videoAbuseReportReasons/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.SNIPPET})
public class VideoAbuseReportReasonsList extends YouTubeResource implements Serializable {

    Item[] items;

    public VideoAbuseReportReasonsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("videoAbuseReportReasons");
    }

    public VideoAbuseReportReasonsList get() throws IOException, YouTubeErrorException {
        return super.get();
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
            String label;
            SecondaryReason[] secondaryReasons;

            public String getLabel() {
                return label;
            }

            public SecondaryReason[] getSecondaryReasons() {
                return secondaryReasons;
            }

            public static class SecondaryReason implements Serializable {
                String id, label;

                public String getId() {
                    return id;
                }

                public String getLabel() {
                    return label;
                }
            }
        }

    }
}
