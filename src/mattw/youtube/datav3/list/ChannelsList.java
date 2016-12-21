package mattw.youtube.datav3.list;

import java.util.Date;

import mattw.youtube.datav3.YoutubeData;

public class ChannelsList extends ListResponse {
	
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
	
	public Item[] items;
	public String nextPageToken;
	public String prevPageToken;
	
	public static String getChannelsByCategoryUrl(String apiKey, String part, String categoryId, int maxResults) {
		return YoutubeData.BASE_API+"/channels?key="+apiKey+"&part="+part+"&categoryId="+categoryId+"&maxResults="+maxResults;
	}
	
	public static String getChannelsByCategoryUrl(String apiKey, String part, String categoryId, int maxResults, String pageToken) {
		return getChannelsByCategoryUrl(apiKey, part, categoryId, maxResults)+"&pageToken="+pageToken;
	}
	
	public static String getChannelsByUsernameUrl(String apiKey, String part, String forUsername, int maxResults) {
		return YoutubeData.BASE_API+"/channels?key="+apiKey+"&part="+part+"&forUsername="+forUsername+"&maxResults="+maxResults;
	}
	
	public static String getChannelsByUsernameUrl(String apiKey, String part, String forUsername, int maxResults, String pageToken) {
		return getChannelsByUsernameUrl(apiKey, part, forUsername, maxResults)+"&pageToken="+pageToken;
	}
	
	public static String getChannelsByChannelIdUrl(String apiKey, String part, String id, int maxResults) {
		return YoutubeData.BASE_API+"/channels?key="+apiKey+"&part="+part+"&id="+id+"&maxResults="+maxResults;
	}
	
	public static String getChannelsByChannelIdUrl(String apiKey, String part, String id, int maxResults, String pageToken) {
		return getChannelsByChannelIdUrl(apiKey, part, id, maxResults)+"&pageToken="+pageToken;
	}
	
	public class Item extends ListResponse.Item {
		public AuditDetails auditDetails;
		public BrandingSettings brandingSettings;
		public ContentDetails contentDetails;
		public ContentOwnerDetails contentOwnerDetails;
		public InvideoPromotion invideoPromotion;
		// public Localizations localizations;
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
		
		public class Snippet extends ListResponse.Part {
			public String title;
			public String description;
			public String customUrl;
			public Date publishedAt;
			public Thumbnails thumbnails;
			public Localized localized;
			public String country;
			public class Localized {
				public String title;
				public String description;
			}
		}
		public class ContentDetails extends ListResponse.Part {
			public RelatedPlaylists relatedPlaylists;
			public class RelatedPlaylists {
				public String likes;
				public String favorites;
				public String uploads;
				public String watchHistory;
				public String watchLater;
			}
		}
		public class Statistics extends ListResponse.Part {
			public long viewCount;
			public long commentCount;
			public long subsciberCount;
			public long hiddenSubscriberCount;
			public long videoCount;
		}
		public class TopicDetails extends ListResponse.Part {
			public String[] topicIds;
		}
		public class Status extends ListResponse.Part {
			public String privacyStatus;
			public boolean isLinked;
			public String longUploadsStatus;
		}
		public class BrandingSettings extends ListResponse.Part {
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
		public class InvideoPromotion extends ListResponse.Part {
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
		public class AuditDetails extends ListResponse.Part {
			public boolean overallGoodStanding;
			public boolean communityGuidelinesGoodStanding;
			public boolean copyrightStrikesGoodStanding;
			public boolean contentIdClaimsGoodStanding;
		}
		public class ContentOwnerDetails extends ListResponse.Part {
			public String contentOwner;
			public Date timeLinked;
		}
		public class Localizations extends ListResponse.Part {
			// TODO Unknown keys.
			public class Localize {
				public String title;
				public String description;
			}
		}
	}
}
