package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.Thumbs;
import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeErrorException;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;
import java.util.Date;

public class ChannelsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;
    public final static String PART_AUDIT_DETAILS = "auditDetails"; // cost: 4
    public final static String PART_BRANDING_SETTINGS = "brandingSettings"; // cost: 2
    public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
    public final static String PART_CONTENT_OWNER_DETAILS = "contentOwnerDetails"; // cost: 2
    public final static String PART_INVIDEO_PROMOTION = "invideoPromotion"; // cost: 2
    // public final static String PART_LOCALIZATIONS = "localizations"; // cost: 2
    public final static String PART_SNIPPET = "snippet"; // cost: 2
    public final static String PART_STATISTICS = "statistics"; // cost: 2
    public final static String PART_STATUS = "status"; // cost: 2
    public final static String PART_TOPIC_DETAILS = "topicDetails"; // cost: 2

    {
        this.dataPath = "channels";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public ChannelsList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
    }

    public boolean hasItems() { return items != null; }

    public ChannelsList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public ChannelsList getByCategory(String part, String categoryId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("categoryId", categoryId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public ChannelsList getByUsername(String part, String forUsername, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("forUsername", forUsername);
        fields.put("pageToken", pageToken);
        return get();
    }

    public ChannelsList getByChannel(String part, String channelId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("id", channelId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public ChannelsList getMine(String part, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("mine", "true");
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {
        public AuditDetails auditDetails;
        public BrandingSettings brandingSettings;
        public ContentDetails contentDetails;
        public ContentOwnerDetails contentOwnerDetails;
        public InvideoPromotion invideoPromotion;
        public Snippet snippet;
        public Statistics statistics;
        public Status status;
        public TopicDetails topicDetails;

        public boolean hasAuditDetails() {
            return auditDetails != null;
        }

        public boolean hasBrandingSettings() {
            return brandingSettings != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasContentOwnerDetails() {
            return contentOwnerDetails != null;
        }

        public boolean hasInvideoPromotion() {
            return invideoPromotion != null;
        }

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasStatistics() {
            return statistics != null;
        }

        public boolean hasStatus() {
            return status != null;
        }

        public boolean hasTopicDetails() {
            return topicDetails != null;
        }

        public class Snippet {
            public String title;
            public String description;
            public String customUrl;
            public Date publishedAt;
            public Thumbs thumbnails;
            public Localized localized;
            public String country;
            public class Localized {
                public String title;
                public String description;
            }
        }
        public class ContentDetails {
            public RelatedPlaylists relatedPlaylists;
            public class RelatedPlaylists {
                public String likes;
                public String favorites;
                public String uploads;
                public String watchHistory;
                public String watchLater;
            }
        }
        public class Statistics {
            public long viewCount;
            public long commentCount;
            public long subsciberCount;
            public long hiddenSubscriberCount;
            public long videoCount;
        }
        public class TopicDetails {
            public String[] topicIds;
        }
        public class Status {
            public String privacyStatus;
            public boolean isLinked;
            public String longUploadsStatus;
        }
        public class BrandingSettings {
            public Channel channel;
            public Watch watch;
            public Image image;
            public Hint[] hints;
            public class Channel {
                public String title;
                public String description;
                public String keywords;
                public String defaultTab;
                public String trackingAnalyticsAccountId;
                public boolean moderateComments;
                public boolean showRelatedChannels;
                public boolean showBrowseView;
                public String featuredChannelsTitle;
                public String[] featuredChannelsUrls;
                public String unsubscribedTrailer;
                public String profileColor;
                public String defaultLanguage;
                public String country;
            }
            public class Watch {
                public String textColor;
                public String backgroundColor;
                public String featuredPlaylistId;
            }
            public class Image {
                public String bannerImageUrl, bannedModelImageIrl, watchIconImageUrl, trackingIconImageUrl,
                        bannerTabletLowImageUrl, bannerTabletImageUrl, bannerTabletHdImageUrl, bannerTabletExtraHdImageUrl,
                        bannerMobileLowImageUrl, bannerMobileImageUrl, bannerMobileHdImageUrl, bannerMobileExtraHdImageUrl,
                        bannerTvImageUrl, bannerTvLowImageUrl, bannerTvMediumImageUrl, bannerTvHighImageUrl, bannerExternalUrl;
            }
            public class Hint {
                public String property;
                public String value;
            }
        }
        public class InvideoPromotion {
            public DefaultTiming defaultTiming;
            public Position position;
            public InvideoItem[] items;
            public boolean useSmartTiming;
            public class DefaultTiming {
                public String type;
                public long offsetMs;
                public long durationMs;
            }
            public class Position {
                public String type;
                public String cornerPosition;
            }
            public class InvideoItem {
                public Id id;
                public DefaultTiming timing;
                public String customMessage;
                public boolean promotedByContentOwner;
                public class Id {
                    public String type;
                    public String videoId;
                    public String websiteUrl;
                    public String recentlyUploadedBy;
                }
            }
        }
        public class AuditDetails {
            public boolean overallGoodStanding;
            public boolean communityGuidelinesGoodStanding;
            public boolean copyrightStrikesGoodStanding;
            public boolean contentIdClaimsGoodStanding;
        }
        public class ContentOwnerDetails {
            public String contentOwner;
            public Date timeLinked;
        }
    }
}
