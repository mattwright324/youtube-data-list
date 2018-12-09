package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/channels/list
 * @version 2018-12-08
 * @author mattwright324
 */
@AcceptsParts(values = {
        Parts.ID,
        Parts.AUDIT_DETAILS,
        Parts.BRANDING_SETTINGS,
        Parts.CONTENT_DETAILS,
        Parts.CONTENT_OWNER_DETAILS,
        Parts.INVIDEO_PROMOTION,
        Parts.LOCALIZATIONS,
        Parts.SNIPPET,
        Parts.STATISTICS,
        Parts.STATUS,
        Parts.TOPIC_DETAILS
})
public class ChannelsList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public ChannelsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("channels");
    }

    public boolean hasItems() { return items != null; }

    public ChannelsList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public ChannelsList getByCategory(String categoryId, String pageToken) throws IOException, YouTubeErrorException {
        setField("categoryId", categoryId);
        setField("pageToken", pageToken);
        return get();
    }

    public ChannelsList getByUsername(String forUsername, String pageToken) throws IOException, YouTubeErrorException {
        setField("forUsername", forUsername);
        setField("pageToken", pageToken);
        return get();
    }

    public ChannelsList getByChannel(String channelId, String pageToken) throws IOException, YouTubeErrorException {
        setField("id", channelId);
        setField("pageToken", pageToken);
        return get();
    }

    public ChannelsList getMine(String pageToken) throws IOException, YouTubeErrorException {
        setField("mine", "true");
        setField("pageToken", pageToken);
        return get();
    }

    public class Item extends BaseItem {
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
