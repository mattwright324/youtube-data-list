package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * @link https://developers.google.com/youtube/v3/docs/channelSections/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {Parts.ID, Parts.CONTENT_DETAILS, Parts.SNIPPET, Parts.TARGETING})
public class ChannelSectionsList extends YouTubeResource implements Serializable {

    Item[] items;

    public ChannelSectionsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setDataPath("channelSections");
    }

    public ChannelSectionsList getByChannel(String channelId) throws IOException, YouTubeErrorException {
        setField("channelId", channelId);
        return get();
    }

    public ChannelSectionsList getBySection(String sectionId) throws IOException, YouTubeErrorException {
        setField("sectionId", sectionId);
        return get();
    }

    public boolean hasItems() {
        return items != null;
    }

    public Item[] getItems() {
        return items;
    }

    public static class Item extends BaseItem {
        Snippet snippet;
        ContentDetails contentDetails;
        Targeting targeting;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasTargeting() {
            return targeting != null;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public Targeting getTargeting() {
            return targeting;
        }

        public static class Snippet implements Serializable {
            int position;
            Localized localized;
            String type, style, channelId, title, defaultLanguage;

            public int getPosition() {
                return position;
            }

            public Localized getLocalized() {
                return localized;
            }

            public String getType() {
                return type;
            }

            public String getStyle() {
                return style;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getTitle() {
                return title;
            }

            public String getDefaultLanguage() {
                return defaultLanguage;
            }

            public static class Localized implements Serializable {
                String title;

                public String getTitle() {
                    return title;
                }
            }
        }

        public static class ContentDetails implements Serializable {
            String[] playlists, channels;

            public String[] getPlaylists() {
                return playlists;
            }

            public String[] getChannels() {
                return channels;
            }
        }

        public static class Targeting implements Serializable {
            String[] languages, regions, countries;

            public String[] getLanguages() {
                return languages;
            }

            public String[] getRegions() {
                return regions;
            }

            public String[] getCountries() {
                return countries;
            }
        }
    }
}
