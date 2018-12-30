package mattw.youtube.datav3.entrypoints;

import mattw.youtube.datav3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @link https://developers.google.com/youtube/v3/docs/videos/list
 * @version 2018-12-30
 * @author mattwright324
 */
@AcceptsParts(values = {
        Parts.ID,
        Parts.CONTENT_DETAILS,
        Parts.FILE_DETAILS,
        Parts.LIVE_STREAMING_DETAILS,
        Parts.PLAYER,
        Parts.PROCESSING_DETAILS,
        Parts.RECORDING_DETAILS,
        Parts.SNIPPET,
        Parts.STATISTICS,
        Parts.STATUS,
        Parts.SUGGESTIONS,
        Parts.TOPIC_DETAILS
})
public class VideosList extends YouTubeResource {

    public final static int MAX_RESULTS = 50;

    public final static String CHART_MOST_POPULAR = "mostPopular";

    String nextPageToken, prevPageToken;
    Item[] items;

    public VideosList(YouTubeData3 data) {
        super(data);
        setCost(1);
        setField("maxResults", 50);
        setDataPath("videos");
    }

    public VideosList maxResults(int maxResults) {
        setField("maxResults", maxResults);
        return this;
    }

    public VideosList getByIds(String ids, String pageToken) throws IOException, YouTubeErrorException {
        setField("id", ids);
        setField("pageToken", pageToken);
        return get();
    }

    public VideosList getByChart(String chart, String regionCode, String videoCategoryId, String pageToken) throws IOException, YouTubeErrorException {
        setField("chart", chart);
        setField("regionCode", regionCode);
        setField("videoCategoryId", videoCategoryId);
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
        Snippet snippet;
        ContentDetails contentDetails;
        Status status;
        Statistics statistics;
        Player player;
        TopicDetails topicDetails;
        RecordingDetails recordingDetails;
        FileDetails fileDetails;
        ProcessingDetails processingDetails;
        Suggestions suggestions;
        LiveStreamingDetails liveStreamingDetails;

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

        public Snippet getSnippet() {
            return snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public Status getStatus() {
            return status;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public Player getPlayer() {
            return player;
        }

        public TopicDetails getTopicDetails() {
            return topicDetails;
        }

        public RecordingDetails getRecordingDetails() {
            return recordingDetails;
        }

        public FileDetails getFileDetails() {
            return fileDetails;
        }

        public ProcessingDetails getProcessingDetails() {
            return processingDetails;
        }

        public Suggestions getSuggestions() {
            return suggestions;
        }

        public LiveStreamingDetails getLiveStreamingDetails() {
            return liveStreamingDetails;
        }

        public static class Snippet implements Serializable {
            Date publishedAt;
            String channelId, title, description, channelTitle, defaultAudioLanguage, categoryId,
                    liveBroadcastContent, defaultLanguage;
            Thumbs thumbnails;
            String[] tags;
            Localized localized;

            public Date getPublishedAt() {
                return publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public String getDefaultAudioLanguage() {
                return defaultAudioLanguage;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public String getLiveBroadcastContent() {
                return liveBroadcastContent;
            }

            public String getDefaultLanguage() {
                return defaultLanguage;
            }

            public Thumbs getThumbnails() {
                return thumbnails;
            }

            public String[] getTags() {
                return tags;
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
            String duration, dimension, definition, caption, projection;
            boolean liscensedContent, hasCustomThumbnail;
            Restriction regionRestriction;
            ContentRating contentRating;

            public String getDuration() {
                return duration;
            }

            public String getDimension() {
                return dimension;
            }

            public String getDefinition() {
                return definition;
            }

            public String getCaption() {
                return caption;
            }

            public String getProjection() {
                return projection;
            }

            public boolean isLiscensedContent() {
                return liscensedContent;
            }

            public boolean isHasCustomThumbnail() {
                return hasCustomThumbnail;
            }

            public Restriction getRegionRestriction() {
                return regionRestriction;
            }

            public ContentRating getContentRating() {
                return contentRating;
            }

            public static class Restriction implements Serializable {
                String[] allowed, blocked;

                public String[] getAllowed() {
                    return allowed;
                }

                public String[] getBlocked() {
                    return blocked;
                }
            }
            public static class ContentRating implements Serializable {
                String acbRating, agcomRating, anatelRating, bbfcRating, bfvcRating, bmukkRating, catvRating, catvfrRating, cbfcRating, cccRating, cceRating, chfilmRating, chvrsRating, cicfRating, cnaRating, cncRating, csaRating, cscfRating, czfilmRating, djctqRating, ecbmctRating, eefilmRating, egfilmRating, eirinRating, fcbmRating, fcoRating, fmocRating, fpbRating, fskRating, grfilmRating, icaaRating, ifcoRating, ilfilmRating, incaaRating, kfcbRating, kijkwijzerRating, kmrbRating, lsfRating, mccaaRating, mccypRating, mcstRating, mdaRating, medietilsynetRating, mekuRating, mibacRating, mocRating, moctwRating, mpaaRating, mtrcbRating, nbcRating, nbcplRating, nfrcRating, nfvcbRating, nkclvRating, oflcRating, pefilmRating, rcnofRating, resorteviolenciaRating, rtcRating, rteRating, russiaRating, skfilmRating, smaisRating, smsaRating, tvpgRating, ytRating;
                String[] djctqRatingReasons, fpbRatingReasons;

                public String getAcbRating() {
                    return acbRating;
                }

                public String getAgcomRating() {
                    return agcomRating;
                }

                public String getAnatelRating() {
                    return anatelRating;
                }

                public String getBbfcRating() {
                    return bbfcRating;
                }

                public String getBfvcRating() {
                    return bfvcRating;
                }

                public String getBmukkRating() {
                    return bmukkRating;
                }

                public String getCatvRating() {
                    return catvRating;
                }

                public String getCatvfrRating() {
                    return catvfrRating;
                }

                public String getCbfcRating() {
                    return cbfcRating;
                }

                public String getCccRating() {
                    return cccRating;
                }

                public String getCceRating() {
                    return cceRating;
                }

                public String getChfilmRating() {
                    return chfilmRating;
                }

                public String getChvrsRating() {
                    return chvrsRating;
                }

                public String getCicfRating() {
                    return cicfRating;
                }

                public String getCnaRating() {
                    return cnaRating;
                }

                public String getCncRating() {
                    return cncRating;
                }

                public String getCsaRating() {
                    return csaRating;
                }

                public String getCscfRating() {
                    return cscfRating;
                }

                public String getCzfilmRating() {
                    return czfilmRating;
                }

                public String getDjctqRating() {
                    return djctqRating;
                }

                public String getEcbmctRating() {
                    return ecbmctRating;
                }

                public String getEefilmRating() {
                    return eefilmRating;
                }

                public String getEgfilmRating() {
                    return egfilmRating;
                }

                public String getEirinRating() {
                    return eirinRating;
                }

                public String getFcbmRating() {
                    return fcbmRating;
                }

                public String getFcoRating() {
                    return fcoRating;
                }

                public String getFmocRating() {
                    return fmocRating;
                }

                public String getFpbRating() {
                    return fpbRating;
                }

                public String getFskRating() {
                    return fskRating;
                }

                public String getGrfilmRating() {
                    return grfilmRating;
                }

                public String getIcaaRating() {
                    return icaaRating;
                }

                public String getIfcoRating() {
                    return ifcoRating;
                }

                public String getIlfilmRating() {
                    return ilfilmRating;
                }

                public String getIncaaRating() {
                    return incaaRating;
                }

                public String getKfcbRating() {
                    return kfcbRating;
                }

                public String getKijkwijzerRating() {
                    return kijkwijzerRating;
                }

                public String getKmrbRating() {
                    return kmrbRating;
                }

                public String getLsfRating() {
                    return lsfRating;
                }

                public String getMccaaRating() {
                    return mccaaRating;
                }

                public String getMccypRating() {
                    return mccypRating;
                }

                public String getMcstRating() {
                    return mcstRating;
                }

                public String getMdaRating() {
                    return mdaRating;
                }

                public String getMedietilsynetRating() {
                    return medietilsynetRating;
                }

                public String getMekuRating() {
                    return mekuRating;
                }

                public String getMibacRating() {
                    return mibacRating;
                }

                public String getMocRating() {
                    return mocRating;
                }

                public String getMoctwRating() {
                    return moctwRating;
                }

                public String getMpaaRating() {
                    return mpaaRating;
                }

                public String getMtrcbRating() {
                    return mtrcbRating;
                }

                public String getNbcRating() {
                    return nbcRating;
                }

                public String getNbcplRating() {
                    return nbcplRating;
                }

                public String getNfrcRating() {
                    return nfrcRating;
                }

                public String getNfvcbRating() {
                    return nfvcbRating;
                }

                public String getNkclvRating() {
                    return nkclvRating;
                }

                public String getOflcRating() {
                    return oflcRating;
                }

                public String getPefilmRating() {
                    return pefilmRating;
                }

                public String getRcnofRating() {
                    return rcnofRating;
                }

                public String getResorteviolenciaRating() {
                    return resorteviolenciaRating;
                }

                public String getRtcRating() {
                    return rtcRating;
                }

                public String getRteRating() {
                    return rteRating;
                }

                public String getRussiaRating() {
                    return russiaRating;
                }

                public String getSkfilmRating() {
                    return skfilmRating;
                }

                public String getSmaisRating() {
                    return smaisRating;
                }

                public String getSmsaRating() {
                    return smsaRating;
                }

                public String getTvpgRating() {
                    return tvpgRating;
                }

                public String getYtRating() {
                    return ytRating;
                }

                public String[] getDjctqRatingReasons() {
                    return djctqRatingReasons;
                }

                public String[] getFpbRatingReasons() {
                    return fpbRatingReasons;
                }
            }
        }
        public static class Status implements Serializable {
            String uploadStatus, failureReason, rejectionReason, privacyStatus, license;
            Date publishAt;
            boolean embeddable, publicStatsViewable;

            public String getUploadStatus() {
                return uploadStatus;
            }

            public String getFailureReason() {
                return failureReason;
            }

            public String getRejectionReason() {
                return rejectionReason;
            }

            public String getPrivacyStatus() {
                return privacyStatus;
            }

            public String getLicense() {
                return license;
            }

            public Date getPublishAt() {
                return publishAt;
            }

            public boolean isEmbeddable() {
                return embeddable;
            }

            public boolean isPublicStatsViewable() {
                return publicStatsViewable;
            }
        }
        public static class Statistics implements Serializable {
            long viewCount, likeCount, dislikeCount, favoriteCount, commentCount;

            public long getViewCount() {
                return viewCount;
            }

            public long getLikeCount() {
                return likeCount;
            }

            public long getDislikeCount() {
                return dislikeCount;
            }

            public long getFavoriteCount() {
                return favoriteCount;
            }

            public long getCommentCount() {
                return commentCount;
            }
        }
        public static class Player implements Serializable {
            String embedHtml;
            long embedHeight, embedWidth;

            public String getEmbedHtml() {
                return embedHtml;
            }

            public long getEmbedHeight() {
                return embedHeight;
            }

            public long getEmbedWidth() {
                return embedWidth;
            }
        }
        public static class TopicDetails implements Serializable {
            String[] topicIds, relevantTopicIds;

            public String[] getTopicIds() {
                return topicIds;
            }

            public String[] getRelevantTopicIds() {
                return relevantTopicIds;
            }
        }
        public static class RecordingDetails implements Serializable {
            String locationDescription;
            Location location;
            Date recordingDate;

            public String getLocationDescription() {
                return locationDescription;
            }

            public Location getLocation() {
                return location;
            }

            public Date getRecordingDate() {
                return recordingDate;
            }

            public static class Location implements Serializable {
                double latitude, longitude, altitude;

                public double getLatitude() {
                    return latitude;
                }

                public double getLongitude() {
                    return longitude;
                }

                public double getAltitude() {
                    return altitude;
                }
            }
        }
        public static class FileDetails implements Serializable {
            String fileName, fileType, container, creationTime;
            VideoStream[] videoStreams;
            AudioStream[] audioStreams;
            long fileSize, durationMs, bitrateBps;

            public String getFileName() {
                return fileName;
            }

            public String getFileType() {
                return fileType;
            }

            public String getContainer() {
                return container;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public VideoStream[] getVideoStreams() {
                return videoStreams;
            }

            public AudioStream[] getAudioStreams() {
                return audioStreams;
            }

            public long getFileSize() {
                return fileSize;
            }

            public long getDurationMs() {
                return durationMs;
            }

            public long getBitrateBps() {
                return bitrateBps;
            }

            public static class VideoStream implements Serializable {
                int widthPixels, heightPixels;
                double frameRateFps, aspectRatio;
                long bitrateBps;
                String codec, rotation, vendor;

                public int getWidthPixels() {
                    return widthPixels;
                }

                public int getHeightPixels() {
                    return heightPixels;
                }

                public double getFrameRateFps() {
                    return frameRateFps;
                }

                public double getAspectRatio() {
                    return aspectRatio;
                }

                public long getBitrateBps() {
                    return bitrateBps;
                }

                public String getCodec() {
                    return codec;
                }

                public String getRotation() {
                    return rotation;
                }

                public String getVendor() {
                    return vendor;
                }
            }
            public static class AudioStream implements Serializable {
                String codec, vendor;
                int channelCount;
                long bitrateBps;

                public String getCodec() {
                    return codec;
                }

                public String getVendor() {
                    return vendor;
                }

                public int getChannelCount() {
                    return channelCount;
                }

                public long getBitrateBps() {
                    return bitrateBps;
                }
            }
        }
        public static class ProcessingDetails implements Serializable {
            ProcessingProgress processingProgress;
            String processingStatus, processingFailureReason, fileDetailsAvailability, tagSuggestionsAvailability,
                    editorSuggestionsAvailability, thumbnailsAvailability;

            public ProcessingProgress getProcessingProgress() {
                return processingProgress;
            }

            public String getProcessingStatus() {
                return processingStatus;
            }

            public String getProcessingFailureReason() {
                return processingFailureReason;
            }

            public String getFileDetailsAvailability() {
                return fileDetailsAvailability;
            }

            public String getTagSuggestionsAvailability() {
                return tagSuggestionsAvailability;
            }

            public String getEditorSuggestionsAvailability() {
                return editorSuggestionsAvailability;
            }

            public String getThumbnailsAvailability() {
                return thumbnailsAvailability;
            }

            public static class ProcessingProgress implements Serializable {
                long partsTotal, partsProcessed, timeLeftMs;

                public long getPartsTotal() {
                    return partsTotal;
                }

                public long getPartsProcessed() {
                    return partsProcessed;
                }

                public long getTimeLeftMs() {
                    return timeLeftMs;
                }
            }
        }
        public static class Suggestions implements Serializable {
            String[] processingErrors, processingWarnings, processingHints, editorSuggestions;
            TagSuggestion[] tagSuggestions;

            public String[] getProcessingErrors() {
                return processingErrors;
            }

            public String[] getProcessingWarnings() {
                return processingWarnings;
            }

            public String[] getProcessingHints() {
                return processingHints;
            }

            public String[] getEditorSuggestions() {
                return editorSuggestions;
            }

            public TagSuggestion[] getTagSuggestions() {
                return tagSuggestions;
            }

            public static class TagSuggestion implements Serializable {
                String tag;
                String[] categoryRestricts;

                public String getTag() {
                    return tag;
                }

                public String[] getCategoryRestricts() {
                    return categoryRestricts;
                }
            }
        }
        public static class LiveStreamingDetails implements Serializable {
            Date actualStartTime, actualEndTime, schedulesStartTime, scheduledEndTime;
            long concurrentViewers;
            String activeLiveChatId;

            public Date getActualStartTime() {
                return actualStartTime;
            }

            public Date getActualEndTime() {
                return actualEndTime;
            }

            public Date getSchedulesStartTime() {
                return schedulesStartTime;
            }

            public Date getScheduledEndTime() {
                return scheduledEndTime;
            }

            public long getConcurrentViewers() {
                return concurrentViewers;
            }

            public String getActiveLiveChatId() {
                return activeLiveChatId;
            }
        }
    }
}
