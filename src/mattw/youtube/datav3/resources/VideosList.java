package mattw.youtube.datav3.resources;

import mattw.youtube.datav3.Thumbs;
import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.YouTubeErrorException;
import mattw.youtube.datav3.YouTubeResource;

import java.io.IOException;
import java.util.Date;

public class VideosList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;
    public final static String PART_CONTENT_DETAILS = "contentDetails"; // cost: 2
    public final static String PART_FILE_DETAILS = "fileDetails"; // cost: 1
    public final static String PART_LIVE_STREAMING_DETAILS = "liveStreamingDetails"; // cost: 2
    public final static String PART_PLAYER = "player"; // cost: 0
    public final static String PART_PROCESSING_DETAILS = "processingDetails"; // cost: 1
    public final static String PART_RECORDING_DETAILS = "recordingDetails"; // cost: 2
    public final static String PART_SNIPPET = "snippet"; // cost: 2
    public final static String PART_STATISTICS = "statistics"; // cost: 2
    public final static String PART_STATUS = "status"; // cost: 2
    public final static String PART_SUGGESTIONS = "suggestions"; // cost: 1
    public final static String PART_TOPIC_DETAILS = "topicDetails"; // cost 2

    public final static String CHART_MOST_POPULAR = "mostPopular";

    {
        this.dataPath = "videos";
    }

    public String nextPageToken;
    public String prevPageToken;
    public Item[] items;

    public VideosList(YouTubeData3 data) {
        super(data);
        fields.put("maxResults", 50);
    }

    public VideosList maxResults(int maxResults) {
        fields.put("maxResults", maxResults);
        return this;
    }

    public VideosList getByIds(String part, String ids, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("id", ids);
        fields.put("pageToken", pageToken);
        return get();
    }

    public VideosList getByChart(String part, String chart, String regionCode, String videoCategoryId, String pageToken) throws IOException, YouTubeErrorException {
        fields.put("part", part);
        fields.put("chart", chart);
        fields.put("regionCode", regionCode);
        fields.put("videoCategoryId", videoCategoryId);
        fields.put("pageToken", pageToken);
        return get();
    }

    public class Item extends YouTubeResource.Item {

        public Snippet snippet;
        public ContentDetails contentDetails;
        public Status status;
        public Statistics statistics;
        public Player player;
        public TopicDetails topicDetails;
        public RecordingDetails recordingDetails;
        public FileDetails fileDetails;
        public ProcessingDetails processingDetails;
        public Suggestions suggestions;
        public LiveStreamingDetails liveStreamingDetails;

        public boolean hasSnippet() {
            return snippet != null;
        }

        public boolean hasContentDetails() {
            return contentDetails != null;
        }

        public boolean hasStatus() {
            return status != null;
        }

        public boolean hasStatistics() {
            return statistics != null;
        }

        public boolean hasPlayer() {
            return player != null;
        }

        public boolean hasTopicDetails() {
            return topicDetails != null;
        }

        public boolean hasRecordingDetails() {
            return recordingDetails != null;
        }

        public boolean hasFileDetails() {
            return fileDetails != null;
        }

        public boolean hasProcessingDetails() {
            return processingDetails != null;
        }

        public boolean hasSuggestions() {
            return suggestions != null;
        }

        public boolean hasLiveStreamingDetails() {
            return liveStreamingDetails != null;
        }

        public class Snippet {
            public Date publishedAt;
            public String channelId;
            public String title;
            public String description;
            public Thumbs thumbnails;
            public String channelTitle;
            public String[] tags;
            public String categoryId;
            public String liveBroadcastContent;
            public String defaultLanguage;
            public Localized localized;
            public String defaultAudioLanguage;
            public class Localized {
                public String title;
                public String description;
            }
        }
        public class ContentDetails {
            public String duration;
            public String dimension;
            public String definition;
            public String caption;
            public boolean liscensedContent;
            public Restriction regionRestriction;
            public ContentRating contentRating;
            public String projection;
            public boolean hasCustomThumbnail;
            public class Restriction {
                public String[] allowed;
                public String[] blocked;
            }
            public class ContentRating {
                public String acbRating, agcomRating, anatelRating, bbfcRating, bfvcRating, bmukkRating, catvRating, catvfrRating, cbfcRating, cccRating, cceRating, chfilmRating, chvrsRating, cicfRating, cnaRating, cncRating, csaRating, cscfRating, czfilmRating, djctqRating, ecbmctRating, eefilmRating, egfilmRating, eirinRating, fcbmRating, fcoRating, fmocRating, fpbRating, fskRating, grfilmRating, icaaRating, ifcoRating, ilfilmRating, incaaRating, kfcbRating, kijkwijzerRating, kmrbRating, lsfRating, mccaaRating, mccypRating, mcstRating, mdaRating, medietilsynetRating, mekuRating, mibacRating, mocRating, moctwRating, mpaaRating, mtrcbRating, nbcRating, nbcplRating, nfrcRating, nfvcbRating, nkclvRating, oflcRating, pefilmRating, rcnofRating, resorteviolenciaRating, rtcRating, rteRating, russiaRating, skfilmRating, smaisRating, smsaRating, tvpgRating, ytRating;
                public String[] djctqRatingReasons, fpbRatingReasons;
            }
        }
        public class Status {
            public String uploadStatus;
            public String failureReason;
            public String rejectionReason;
            public String privacyStatus;
            public Date publishAt;
            public String license;
            public boolean embeddable;
            public boolean publicStatsViewable;
        }
        public class Statistics {
            public long viewCount;
            public long likeCount;
            public long dislikeCount;
            public long favoriteCount;
            public long commentCount;
        }
        public class Player {
            public String embedHtml;
            public long embedHeight;
            public long embedWidth;
        }
        public class TopicDetails {
            public String[] topicIds;
            public String[] relevantTopicIds;
        }
        public class RecordingDetails {
            public String locationDescription;
            public Location location;
            public Date recordingDate;
            public class Location {
                public double latitude;
                public double longitude;
                public double altitude;
            }
        }
        public class FileDetails {
            public String fileName;
            public long fileSize;
            public String fileType;
            public String container;
            public VideoStream[] videoStreams;
            public AudioStream[] audioStreams;
            public long durationMs;
            public long bitrateBps;
            public String creationTime;
            public class VideoStream {
                public int widthPixels;
                public int heightPixels;
                public double frameRateFps;
                public double aspectRatio;
                public String codec;
                public long bitrateBps;
                public String rotation;
                public String vendor;
            }
            public class AudioStream {
                public int channelCount;
                public String codec;
                public long bitrateBps;
                public String vendor;
            }
        }
        public class ProcessingDetails {
            public String processingStatus;
            public ProcessingProgress processingProgress;
            public String processingFailureReason;
            public String fileDetailsAvailability;
            public String tagSuggestionsAvailability;
            public String editorSuggestionsAvailability;
            public String thumbnailsAvailability;
            public class ProcessingProgress {
                public long partsTotal, partsProcessed, timeLeftMs;
            }
        }
        public class Suggestions {
            public String[] processingErrors, processingWarnings, processingHints, editorSuggestions;
            public TagSuggestion[] tagSuggestions;
            public class TagSuggestion {
                public String tag;
                public String[] categoryRestricts;
            }
        }
        public class LiveStreamingDetails {
            public Date actualStartTime, actualEndTime, schedulesStartTime, scheduledEndTime;
            public long concurrentViewers;
            public String activeLiveChatId;
        }
    }
}
