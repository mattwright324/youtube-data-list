package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.*;

import java.io.IOException;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.CONTENT_DETAILS, Parts.SNIPPET, Parts.TARGETING})
public class ChannelSectionsList extends YouTubeResource {

    {
        this.dataPath = "channelSections";
    }

    public Item[] items;

    public ChannelSectionsList(YouTubeData3 data) {
        super(data);
    }

    public boolean hasItems() { return items != null; }

    public ChannelSectionsList getByChannel(String part, String channelId) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("channelId", channelId);
        return get();
    }

    public ChannelSectionsList getBySection(String part, String sectionId) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("sectionId", sectionId);
        return get();
    }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;
        public ContentDetails contentDetails;
        public Targeting targeting;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasTargeting() {
            return targeting != null;
        }

        public class Snippet {
            public String type;
            public String style;
            public String channelId;
            public String title;
            public int position;
            public String defaultLanguage;
            public Localized localized;
            public class Localized {
                public String title;
            }
        }

        public class ContentDetails {
            public String[] playlists;
            public String[] channels;

        }

        public class Targeting {
            public String[] languages;
            public String[] regions;
            public String[] countries;
        }
    }
}
