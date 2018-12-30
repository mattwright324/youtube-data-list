package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/channels/list
 * @version 2018-12-30
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
public class ChannelsList extends YouTubeResource implements Serializable {

    public final static int MAX_RESULTS = 50;

    String nextPageToken, prevPageToken;
    Item[] items;

    public ChannelsList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("channels");
    }

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

    public boolean hasItems() {
        return items != null;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public Item[] getItems() {
        return items;
    }

    public static class Item extends BaseItem {
        AuditDetails auditDetails;
        BrandingSettings brandingSettings;
        ContentDetails contentDetails;
        ContentOwnerDetails contentOwnerDetails;
        InvideoPromotion invideoPromotion;
        Snippet snippet;
        Statistics statistics;
        Status status;
        TopicDetails topicDetails;

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

        public AuditDetails getAuditDetails() {
            return auditDetails;
        }

        public BrandingSettings getBrandingSettings() {
            return brandingSettings;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public ContentOwnerDetails getContentOwnerDetails() {
            return contentOwnerDetails;
        }

        public InvideoPromotion getInvideoPromotion() {
            return invideoPromotion;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public Status getStatus() {
            return status;
        }

        public TopicDetails getTopicDetails() {
            return topicDetails;
        }

        public static class Snippet implements Serializable {
            String title, description, customUrl, country;
            Date publishedAt;
            Thumbs thumbnails;
            Localized localized;

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getCustomUrl() {
                return customUrl;
            }

            public String getCountry() {
                return country;
            }

            public Date getPublishedAt() {
                return publishedAt;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }

            public Localized getLocalized() {
                return localized;
            }

            public static class Localized implements Serializable {
                String title, description;

                public String getTitle() {
                    return title;
                }

                public String getDescription() {
                    return description;
                }
            }
        }
        public static class ContentDetails implements Serializable {
            RelatedPlaylists relatedPlaylists;

            public RelatedPlaylists getRelatedPlaylists() {
                return relatedPlaylists;
            }

            public static class RelatedPlaylists implements Serializable {
                String likes, favorites, uploads, watchHistory, watchLater;

                public String getLikes() {
                    return likes;
                }

                public String getFavorites() {
                    return favorites;
                }

                public String getUploads() {
                    return uploads;
                }

                public String getWatchHistory() {
                    return watchHistory;
                }

                public String getWatchLater() {
                    return watchLater;
                }
            }
        }
        public static class Statistics implements Serializable {
            long viewCount, commentCount, subsciberCount, hiddenSubscriberCount, videoCount;

            public long getViewCount() {
                return viewCount;
            }

            public long getCommentCount() {
                return commentCount;
            }

            public long getSubsciberCount() {
                return subsciberCount;
            }

            public long getHiddenSubscriberCount() {
                return hiddenSubscriberCount;
            }

            public long getVideoCount() {
                return videoCount;
            }
        }
        public static class TopicDetails implements Serializable {
            String[] topicIds;

            public String[] getTopicIds() {
                return topicIds;
            }
        }
        public static class Status implements Serializable {
            String privacyStatus, longUploadsStatus;
            boolean isLinked;

            public String getPrivacyStatus() {
                return privacyStatus;
            }

            public String getLongUploadsStatus() {
                return longUploadsStatus;
            }

            public boolean isLinked() {
                return isLinked;
            }
        }
        public static class BrandingSettings implements Serializable {
            Channel channel;
            Watch watch;
            Image image;
            Hint[] hints;

            public Channel getChannel() {
                return channel;
            }

            public Watch getWatch() {
                return watch;
            }

            public Image getImage() {
                return image;
            }

            public Hint[] getHints() {
                return hints;
            }

            public static class Channel {
                String title, description, keywords, defaultTab, trackingAnalyticsAccountId, featuredChannelsTitle,
                        unsubscribedTrailer, profileColor, defaultLanguage, country;
                String[] featuredChannelsUrls;
                boolean moderateComments, showRelatedChannels, showBrowseView;

                public String getTitle() {
                    return title;
                }

                public String getDescription() {
                    return description;
                }

                public String getKeywords() {
                    return keywords;
                }

                public String getDefaultTab() {
                    return defaultTab;
                }

                public String getTrackingAnalyticsAccountId() {
                    return trackingAnalyticsAccountId;
                }

                public String getFeaturedChannelsTitle() {
                    return featuredChannelsTitle;
                }

                public String getUnsubscribedTrailer() {
                    return unsubscribedTrailer;
                }

                public String getProfileColor() {
                    return profileColor;
                }

                public String getDefaultLanguage() {
                    return defaultLanguage;
                }

                public String getCountry() {
                    return country;
                }

                public String[] getFeaturedChannelsUrls() {
                    return featuredChannelsUrls;
                }

                public boolean isModerateComments() {
                    return moderateComments;
                }

                public boolean isShowRelatedChannels() {
                    return showRelatedChannels;
                }

                public boolean isShowBrowseView() {
                    return showBrowseView;
                }
            }
            public static class Watch implements Serializable {
                String textColor, backgroundColor, featuredPlaylistId;

                public String getTextColor() {
                    return textColor;
                }

                public String getBackgroundColor() {
                    return backgroundColor;
                }

                public String getFeaturedPlaylistId() {
                    return featuredPlaylistId;
                }
            }
            public static class Image implements Serializable {
                String bannerImageUrl, bannedModelImageIrl, watchIconImageUrl, trackingIconImageUrl,
                        bannerTabletLowImageUrl, bannerTabletImageUrl, bannerTabletHdImageUrl, bannerTabletExtraHdImageUrl,
                        bannerMobileLowImageUrl, bannerMobileImageUrl, bannerMobileHdImageUrl, bannerMobileExtraHdImageUrl,
                        bannerTvImageUrl, bannerTvLowImageUrl, bannerTvMediumImageUrl, bannerTvHighImageUrl, bannerExternalUrl;

                public String getBannerImageUrl() {
                    return bannerImageUrl;
                }

                public String getBannedModelImageIrl() {
                    return bannedModelImageIrl;
                }

                public String getWatchIconImageUrl() {
                    return watchIconImageUrl;
                }

                public String getTrackingIconImageUrl() {
                    return trackingIconImageUrl;
                }

                public String getBannerTabletLowImageUrl() {
                    return bannerTabletLowImageUrl;
                }

                public String getBannerTabletImageUrl() {
                    return bannerTabletImageUrl;
                }

                public String getBannerTabletHdImageUrl() {
                    return bannerTabletHdImageUrl;
                }

                public String getBannerTabletExtraHdImageUrl() {
                    return bannerTabletExtraHdImageUrl;
                }

                public String getBannerMobileLowImageUrl() {
                    return bannerMobileLowImageUrl;
                }

                public String getBannerMobileImageUrl() {
                    return bannerMobileImageUrl;
                }

                public String getBannerMobileHdImageUrl() {
                    return bannerMobileHdImageUrl;
                }

                public String getBannerMobileExtraHdImageUrl() {
                    return bannerMobileExtraHdImageUrl;
                }

                public String getBannerTvImageUrl() {
                    return bannerTvImageUrl;
                }

                public String getBannerTvLowImageUrl() {
                    return bannerTvLowImageUrl;
                }

                public String getBannerTvMediumImageUrl() {
                    return bannerTvMediumImageUrl;
                }

                public String getBannerTvHighImageUrl() {
                    return bannerTvHighImageUrl;
                }

                public String getBannerExternalUrl() {
                    return bannerExternalUrl;
                }
            }
            public static class Hint implements Serializable {
                String property, value;

                public String getProperty() {
                    return property;
                }

                public String getValue() {
                    return value;
                }
            }
        }
        public static class InvideoPromotion implements Serializable {
            DefaultTiming defaultTiming;
            Position position;
            InvideoItem[] items;
            boolean useSmartTiming;

            public DefaultTiming getDefaultTiming() {
                return defaultTiming;
            }

            public Position getPosition() {
                return position;
            }

            public InvideoItem[] getItems() {
                return items;
            }

            public boolean isUseSmartTiming() {
                return useSmartTiming;
            }

            public static class DefaultTiming implements Serializable {
                String type;
                long offsetMs, durationMs;

                public String getType() {
                    return type;
                }

                public long getOffsetMs() {
                    return offsetMs;
                }

                public long getDurationMs() {
                    return durationMs;
                }
            }
            public static class Position implements Serializable {
                String type, cornerPosition;

                public String getType() {
                    return type;
                }

                public String getCornerPosition() {
                    return cornerPosition;
                }
            }
            public static class InvideoItem implements Serializable {
                Id id;
                DefaultTiming timing;
                String customMessage;
                boolean promotedByContentOwner;

                public Id getId() {
                    return id;
                }

                public DefaultTiming getTiming() {
                    return timing;
                }

                public String getCustomMessage() {
                    return customMessage;
                }

                public boolean isPromotedByContentOwner() {
                    return promotedByContentOwner;
                }

                public static class Id implements Serializable {
                    String type, videoId, websiteUrl, recentlyUploadedBy;

                    public String getType() {
                        return type;
                    }

                    public String getVideoId() {
                        return videoId;
                    }

                    public String getWebsiteUrl() {
                        return websiteUrl;
                    }

                    public String getRecentlyUploadedBy() {
                        return recentlyUploadedBy;
                    }
                }
            }
        }
        public static class AuditDetails implements Serializable {
            boolean overallGoodStanding, communityGuidelinesGoodStanding, copyrightStrikesGoodStanding, contentIdClaimsGoodStanding;

            public boolean isOverallGoodStanding() {
                return overallGoodStanding;
            }

            public boolean isCommunityGuidelinesGoodStanding() {
                return communityGuidelinesGoodStanding;
            }

            public boolean isCopyrightStrikesGoodStanding() {
                return copyrightStrikesGoodStanding;
            }

            public boolean isContentIdClaimsGoodStanding() {
                return contentIdClaimsGoodStanding;
            }
        }
        public static class ContentOwnerDetails implements Serializable {
            String contentOwner;
            Date timeLinked;

            public String getContentOwner() {
                return contentOwner;
            }

            public Date getTimeLinked() {
                return timeLinked;
            }
        }
    }
}
