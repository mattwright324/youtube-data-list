package mattw.youtube.datav3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import mattw.youtube.datav3.list.*;

/**
 * 
 * This program does not include options and filters that require authorized requests.
 * Retrieve channels, videos, comments, playlists, and much more using the Youtube Data API.
 * <br>
 * The <a href="https://developers.google.com/youtube/v3/docs/">Youtube API docs</a> shows more information about each method and its parameters.
 * </br>
 * </br>
 * Dependent on <a href="https://github.com/google/gson">Google's Gson</a>.
 * 
 * @author mattw
 *
 */
public class YoutubeData {
	
	public String data_api_key, access_token;
	public static final String BASE_API = "https://www.googleapis.com/youtube/v3";
	public final Gson gson = new GsonBuilder()
			.excludeFieldsWithModifiers(Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC, Modifier.ABSTRACT)
			.create();
	
	/**
	 * This class requires a Youtube Data API key.
	 * Don't have a key? Follow the <a href="https://developers.google.com/youtube/v3/getting-started">guide to getting started</a>.
	 * 
	 * @param data_api_key
	 */
	public YoutubeData(String data_key) {
		data_api_key = data_key;
	}
	
	public YoutubeData(String data_key, String access_token) {
		data_api_key = data_key;
		this.access_token = access_token;
	}
	
	public void setAccessToken(String access_token) {
		this.access_token = access_token;
	}
	
	/**
	 * Retrieve a list of activities/events from a channel: uploads, likes, favorites, comments, subcriptions, playlists, recommendations, bullitens, social, and channel items.
	 * Only returns the first page of results.
	 * @param part          Parts include id, snippet, and contentDetails.
	 * @param channelId     The channelId for a channel.
	 * @param maxResults    A number between 0 and 50.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ActivitiesList getActivities(String part, String channelId, int maxResults) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ActivitiesList.getActivitiesUrl(data_api_key, part, channelId, maxResults)), ActivitiesList.class);
	}
	/**
	 * Retrieve a list of activities/events from a channel: uploads, likes, favorites, comments, subcriptions, playlists, recommendations, bullitens, social, and channel items.
	 * Use the pageToken to get more results.
	 * @param part          Parts include id, snippet, and contentDetails.
	 * @param channelId     The channelId for a channel.
	 * @param maxResults    A number between 0 and 50.
	 * @param pageToken     May be empty, use nextPageToken or prevPageToken
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ActivitiesList getActivities(String part, String channelId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ActivitiesList.getActivitiesUrl(data_api_key, part, channelId, maxResults, pageToken)), ActivitiesList.class);
	}
	/**
	 * Retrieve a list of activities/events from a channel: uploads, likes, favorites, comments, subcriptions, playlists, recommendations, bullitens, social, and channel items. 
	 * Restrict results by date.
	 * @param part               Parts include id, snippet, and contentDetails.
	 * @param channelId          The channelId for a channel.
	 * @param maxResults         A number between 0 and 50.
	 * @param pageToken          May be empty, use nextPageToken or prevPageToken
	 * @param publishedAfter     May be empty. Date-time using format: YYYY-MM-DDThh:mm:ss.sZ
	 * @param publishedBefore    May be empty. Date-time using format: YYYY-MM-DDThh:mm:ss.sZ
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ActivitiesList getActivities(String part, String channelId, int maxResults, String pageToken, Date publishedAfter, Date publishedBefore) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ActivitiesList.getActivitiesUrl(data_api_key, part, channelId, maxResults, pageToken, publishedAfter, publishedBefore)), ActivitiesList.class);
	}
	
	/**
	 * Get caption information for a specific video.
	 * @param videoId    Only one videoId. The id found at the end of a youtube link.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CaptionsList getCaptions(String videoId) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CaptionsList.getCaptionsUrl(data_api_key, CaptionsList.PART_SNIPPET, videoId)), CaptionsList.class);
	}
	/**
	 * Get caption information for a specific video.
	 * @param videoId    Only one videoId. The id found at the end of a youtube link.
	 * @param ids        May be empty. Comma-separated list of caption-ids for the specific video.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CaptionsList getCaptions(String videoId, String ids) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CaptionsList.getCaptionsUrl(data_api_key, CaptionsList.PART_SNIPPET, videoId, ids)), CaptionsList.class);
	}
	
	/**
	 * Retrieves the sections on a channel page for example the horizontal rows of uploads, playlists, and channels.
	 * @param part         Must be either id, contentDetails, snippet, or targeting.
	 * @param channelId    Only one videoId. The id found at the end of a youtube link.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ChannelSectionsList getChannelSectionsByChannelId(String part, String channelId) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ChannelSectionsList.getChannelSectionsByChannelIdUrl(data_api_key, part, channelId)), ChannelSectionsList.class);
	}
	/**
	 * Retrieves the sections on a channel page for example the horizontal rows of uploads, playlists, and channels.
	 * @param part         Must be either id, contentDetails, snippet, or targeting.
	 * @param sectionId    May be empty. The channelSection id to be retrieved.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ChannelSectionsList getChannelSectionsBySectionIdsUrl(String part, String sectionId) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ChannelSectionsList.getChannelSectionsBySectionIdsUrl(data_api_key, part, sectionId)), ChannelSectionsList.class);
	}
	
	
	/**
	 * Retrieve specific information about channels in a category. 
	 * @param part          Must be either id, auditDetails, brandingDetails, contentDetails, contentOwnerDetails, invideoPromotion, snippet, statistics, status, or topicDetails.
	 * @param categoryId    A categoryId provided by the GuideCategoriesList methods.
	 * @param maxResults    Must be between 0 and 50.
	 * @param pageToken     May be empty, use either nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ChannelsList getChannelsByCategory(String part, String categoryId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ChannelsList.getChannelsByCategoryUrl(data_api_key, part, categoryId, maxResults, pageToken)), ChannelsList.class);
	}
	
	/**
	 * Retrieve specific channel information with a channels username. The channel username is not the channelId.
	 * @param part          Must be either id, auditDetails, brandingDetails, contentDetails, contentOwnerDetails, invideoPromotion, snippet, statistics, status, or topicDetails.
	 * @param username      The channel's username. Found at the end of a <b>youtube.com/user/</b> link.
	 * @param maxResults    Must be between 0 and 50.
	 * @param pageToken     May be empty, user either nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ChannelsList getChannelsByUsername(String part, String username, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ChannelsList.getChannelsByUsernameUrl(data_api_key, part, username, maxResults, pageToken)), ChannelsList.class);
	}
	/**
	 * Retrieve specific information about a channel with its channelId.
	 * @param part        Must be either id, auditDetails, brandingDetails, contentDetails, contentOwnerDetails, invideoPromotion, snippet, statistics, status, or topicDetails.
	 * @param channelId   The channelId for a channel. Found at the end of a <b>youtube.com/channel/</b> link.
	 * @param maxResults  Must be between 0 and 50.
	 * @param pageToken   May be empty, use either nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ChannelsList getChannelsByChannelId(String part, String channelId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ChannelsList.getChannelsByChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken)), ChannelsList.class);
	}
	/**
	 * Retrieve information about your channel.
	 * @param part       Must be either id, auditDetails, brandingDetails, contentDetails, contentOwnerDetails, invideoPromotion, snippet, statistics, status, or topicDetails.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public ChannelsList getChannelsByMine(String part) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(ChannelsList.getChannelsByMineUrl(data_api_key, access_token, part)), ChannelsList.class);
	}
	
	/**
	 * Get comments by their ids.
	 * @param id           A comma-separated list of comment ids.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentsList getComments(String id) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentsList.getCommentsUrl(data_api_key, CommentsList.PART_SNIPPET, id)), CommentsList.class);
	}
	/**
	 * Get comments by their ids.
	 * @param id           A comma-separated list of comment ids.
	 * @param textFormat   May be empty. Choose either html or plain. 
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentsList getComments(String id, String textFormat) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentsList.getCommentsUrl(data_api_key, CommentsList.PART_SNIPPET, id, textFormat)), CommentsList.class);
	}
	
	/**
	 * Get comments related to a specific comment id.
	 * @param parentId     The id of the top level comment.
	 * @param maxResults   Must be between 1 and 100.
	 * @param pageToken    May be empty. Use nextPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentsList getCommentsByParentId(String parentId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentsList.getCommentsByParentIdUrl(data_api_key, CommentsList.PART_SNIPPET, parentId, maxResults, pageToken)), CommentsList.class);
	}
	
	/**
	 * Get comments related to a specific comment id.
	 * @param parentId     The id of the top level comment.
	 * @param maxResults   Must be between 1 and 100.
	 * @param pageToken    May be empty. Use nextPageToken.
	 * @param textFormat   May be empty. Use either plain or html. Formats comment text.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentsList getCommentsByParentId(String parentId, int maxResults, String pageToken, String textFormat) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentsList.getCommentsByParentIdUrl(data_api_key, CommentsList.PART_SNIPPET, parentId, maxResults, pageToken, textFormat)), CommentsList.class);
	}
	
	/**
	 * Retrieve all commentThreads related to a channel (discussion and videos). Default ordered by relevance.
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsRelatedToChannelId(String part, String channelId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsRelatedToChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken)), CommentThreadsList.class);
	}
	/**
	 * Retrieve all commentThreads related to a channel (discussion and videos). Results may be ordered.
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsRelatedToChannelId(String part, String channelId, int maxResults, String pageToken, String order) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsRelatedToChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken, order)), CommentThreadsList.class);
	}
	/**
	 * Retrieve all commentThreads related to a channel (discussion and videos). Results may be ordered. Restrict comments by search terms.
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsRelatedToChannelId(String part, String channelId, int maxResults, String pageToken, String order, String searchTerms) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsRelatedToChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken, order, searchTerms)), CommentThreadsList.class);
	}
	/**
	 * Retrieve all commentThreads related to a channel (discussion and videos). Results may be ordered. Restrict comments by search terms. Determine the format of the comment text.
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @param textFormat    May be empty. Choose either html (default) or plain.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsRelatedToChannelId(String part, String channelId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsRelatedToChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken, order, searchTerms, textFormat)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to the channel (discussion).
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByChannelId(String part, String channelId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to the channel (discussion).
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByChannelId(String part, String channelId, int maxResults, String pageToken, String order) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken, order)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to the channel (discussion).
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByChannelId(String part, String channelId, int maxResults, String pageToken, String order, String searchTerms) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken, order, searchTerms)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to the channel (discussion).
	 * @param part          Must be either snippet or replies.
	 * @param channelId     The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @param textFormat    May be empty. Choose either html (default) or plain.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByChannelId(String part, String channelId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByChannelIdUrl(data_api_key, part, channelId, maxResults, pageToken, order, searchTerms, textFormat)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a video. 
	 * @param part         Must be either snippet or replies.
	 * @param videoId      The id for the video.
	 * @param maxResults   Must be between 1 and 100.
	 * @param pageToken    May be empty. Use nextPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByVideoId(String part, String videoId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByVideoIdUrl(data_api_key, part, videoId, maxResults, pageToken)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a video. 
	 * @param part          Must be either snippet or replies.
	 * @param videoId       The id for the video.
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByVideoId(String part, String videoId, int maxResults, String pageToken, String order) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByVideoIdUrl(data_api_key, part, videoId, maxResults, pageToken, order)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a video. 
	 * @param part          Must be either snippet or replies.
	 * @param videoId       The id for the video.
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByVideoId(String part, String videoId, int maxResults, String pageToken, String order, String searchTerms) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByVideoIdUrl(data_api_key, part, videoId, maxResults, pageToken, order, searchTerms)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a video. 
	 * @param part          Must be either snippet or replies.
	 * @param videoId       The id for the video.
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @param textFormat    May be empty. Choose either html (default) or plain.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByVideoId(String part, String videoId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByVideoIdUrl(data_api_key, part, videoId, maxResults, pageToken, order, searchTerms, textFormat)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a comment. 
	 * @param part         Must be either snippet or replies.
	 * @param commentId    The id for the comment.
	 * @param maxResults   Must be between 1 and 100.
	 * @param pageToken    May be empty. Use nextPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByCommentId(String part, String commentId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByCommentIdUrl(data_api_key, part, commentId, maxResults, pageToken)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a comment. 
	 * @param part          Must be either snippet or replies.
	 * @param commentId     The id for the comment.
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByCommentId(String part, String commentId, int maxResults, String pageToken, String order) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByCommentIdUrl(data_api_key, part, commentId, maxResults, pageToken, order)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a comment. 
	 * @param part          Must be either snippet or replies.
	 * @param commentId     The id for the comment.
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByCommentId(String part, String commentId, int maxResults, String pageToken, String order, String searchTerms) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByCommentIdUrl(data_api_key, part, commentId, maxResults, pageToken, order, searchTerms)), CommentThreadsList.class);
	}
	/**
	 * Return only commentThreads related to a comment. 
	 * @param part          Must be either snippet or replies.
	 * @param commentId     The id for the comment.
	 * @param maxResults    Must be between 1 and 100.
	 * @param pageToken     May be empty. Use nextPageToken.
	 * @param order         May be empty. Order by relevance (default) or time.
	 * @param searchTerms   May be empty. Restrict comments by search terms.
	 * @param textFormat    May be empty. Choose either html (default) or plain.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public CommentThreadsList getCommentThreadsByCommentId(String part, String commentId, int maxResults, String pageToken, String order, String searchTerms, String textFormat) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(CommentThreadsList.getCommentThreadsByCommentIdUrl(data_api_key, part, commentId, maxResults, pageToken, order, searchTerms, textFormat)), CommentThreadsList.class);
	}
	
	/**
	 * Return a list of guide categories based on channel-category ids.
	 * @param ids      Comma-separated list of channel-category ids.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public GuideCategoriesList getGuideCategoriesByChannelIds(String ids) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(GuideCategoriesList.getGuideCategoriesByChannelIdsUrl(data_api_key, GuideCategoriesList.PART_SNIPPET, ids)), GuideCategoriesList.class);
	}
	
	/**
	 * Return a list of guide categories based on a region code. Region codes can be found with the getI18nRegions() method.
	 * @param regionCode
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public GuideCategoriesList getGuideCategoriesByRegionCode(String regionCode) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(GuideCategoriesList.getGuideCategoriesByRegionCodeUrl(data_api_key, GuideCategoriesList.PART_SNIPPET, regionCode)), GuideCategoriesList.class);
	}
	
	/**
	 * Returns a list of i18n languages.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public I18nLanguagesList getI18nLanguages() throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(I18nLanguagesList.getI18nLanguagesUrl(data_api_key, I18nLanguagesList.PART_SNIPPET)), I18nLanguagesList.class);
	}
	
	/**
	 * Returns a list of i18n regions.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public I18nRegionsList getI18nRegions() throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(I18nRegionsList.getI18nRegionsUrl(data_api_key, I18nLanguagesList.PART_SNIPPET)), I18nRegionsList.class);
	}
	
	/**
	 * Returns the items found in a playlist.
	 * @param part          May be either id, contentDetails, snippet, status, or player.
	 * @param playlistId    The id for the playlist.
	 * @param maxResults    Must be between 0 and 50.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public PlaylistItemsList getPlaylistItems(String part, String playlistId, int maxResults) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(PlaylistItemsList.getPlaylistItemsUrl(data_api_key, part, playlistId, maxResults)), PlaylistItemsList.class);
	}
	/**
	 * Returns the items found in a playlist.
	 * @param part         May be either id, contentDetails, snippet, status, or player.
	 * @param playlistId   The id for the playlist.
	 * @param maxResults   Must be betwen 0 and 50.
	 * @param pageToken    May be empty, use either nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public PlaylistItemsList getPlaylistItems(String part, String playlistId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(PlaylistItemsList.getPlaylistItemsUrl(data_api_key, part, playlistId, maxResults, pageToken)), PlaylistItemsList.class);
	}
	
	/**
	 * Returns a collection of playlists by a channel.
	 * @param part         May be either id, contentDetails, snippet, status, or player.
	 * @param channelId    The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults   Must be between 0 and 50.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public PlaylistsList getPlaylistsByChannel(String part, String channelId, int maxResults) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(PlaylistsList.getPlaylistsByChannelUrl(data_api_key, part, channelId, maxResults)), PlaylistsList.class);
	}
	/**
	 * Returns a collection of playlists by a channel.
	 * @param part         May be either id, contentDetails, snippet, status, or player.
	 * @param channelId    The id for the channel. Found after <b>youtube.com/channel/</b>
	 * @param maxResults   Must be between 0 and 50.
	 * @param pageToken    May be empty. Use nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public PlaylistsList getPlaylistsByChannel(String part, String channelId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(PlaylistsList.getPlaylistsByChannelUrl(data_api_key, part, channelId, maxResults, pageToken)), PlaylistsList.class);
	}
	
	/**
	 * Return a collection of playlists by playlist.
	 * @param part         May be either id, contentDetails, snippet, status, or player.
	 * @param playlistId   The id for the playlist.
	 * @param maxResults   Must be between 0 and 50.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public PlaylistsList getPlaylistsByPlaylist(String part, String playlistId, int maxResults) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(PlaylistsList.getPlaylistsByPlaylistUrl(data_api_key, part, playlistId, maxResults)), PlaylistsList.class);
	}
	
	/**
	 * Return a collection of playlists by playlist.
	 * @param part         May be either id, contentDetails, snippet, status, or player.
	 * @param playlistId   The id for the playlist.
	 * @param maxResults   Must be between 0 and 50.
	 * @param pageToken    May be empty. Use either nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public PlaylistsList getPlaylistsByPlaylist(String part, String playlistId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(PlaylistsList.getPlaylistsByPlaylistUrl(data_api_key, part, playlistId, maxResults, pageToken)), PlaylistsList.class);
	}
	
	/**
	 * Search for everything on Youtube with several search constraints. 
	 * @param query	           Automatically changes spaces to %20. May be empty.
	 * @param maxResults       Must be between 0 and 50.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken
	 * @param order            May be empty. Order by date, relevance, rating, title, video count, and view count.
	 * @param type             May be blank or TYPE_ALL. Can be set to find videos, channels, and playlists.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public SearchList getSearch(String query, int maxResults, String pageToken, String order, String type) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(SearchList.getSearchUrl(data_api_key, SearchList.PART_SNIPPET, query.replace(" ", "%20"), maxResults, pageToken, order, type)), SearchList.class);
	}
	/**
	 * Search for everything on Youtube with several search constraints. 
	 * @param query	           Automatically changes spaces to %20. May be empty.
	 * @param maxResults       Must be between 0 and 50.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken
	 * @param order            May be empty. Order by date, relevance, rating, title, video count, and view count.
	 * @param type             May be blank or TYPE_ALL. Can be set to find videos, channels, and playlists.
	 * @param publishedBefore  May be empty. A date-string following: 
	 * @param publishedAfter   May be empty. A date-string following: 
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public SearchList getSearch(String query, int maxResults, String pageToken, String order, String type, String publishedBefore, String publishedAfter) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(SearchList.getSearchUrl(data_api_key, SearchList.PART_SNIPPET, query.replace(" ", "%20"), maxResults, pageToken, order, type, publishedBefore, publishedAfter)), SearchList.class);
	}
	/**
	 * Return videos published with location information based on location and distance provided. 
	 * The location parameters for Youtube's search function only work with type set to 'video' or else it will return HTTP code 400.
	 * @param query	           Automatically changes spaces to %20. May be empty.
	 * @param maxResults       Must be between 0 and 50.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken
	 * @param order            May be empty. Order by date, relevance, rating, title, video count, and view count.
	 * @param location         Must be a string latitude,longitude e.g.: "86.75309,-12.34567"
	 * @param locationRadius   May be a string using m, km, ft, mi e.g.: "100ft", "0.75mi", "1500km"
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public SearchList getSearchVideosAtLocation(String query, int maxResults, String pageToken, String order, String location, String locationRadius) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(SearchList.getSearchAtLocationUrl(data_api_key, SearchList.PART_SNIPPET, query.replace(" ", "%20"), maxResults, pageToken, order, SearchList.TYPE_VIDEO, location, locationRadius)), SearchList.class);
	}
	/**
	 * Return videos published with location information based on location and distance provided. 
	 * The location parameters for Youtube's search function only work with type set to 'video' or else it will return HTTP code 400.
	 * @param query	           Automatically changes spaces to %20. May be empty.
	 * @param maxResults       Must be between 0 and 50.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken
	 * @param order            May be empty. Order by date, relevance, rating, title, video count, and view count.
	 * @param location         Must be a string latitude,longitude e.g.: "86.75309,-12.34567"
	 * @param locationRadius   May be a string using m, km, ft, mi e.g.: "100ft", "0.75mi", "1500km"
	 * @param publishedBefore  May be empty. A date-string following: 
	 * @param publishedAfter   May be empty. A date-string following: 
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public SearchList getSearchVideosAtLocation(String query, int maxResults, String pageToken, String order, String location, String locationRadius, String publishedBefore, String publishedAfter) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(SearchList.getSearchAtLocationUrl(data_api_key, SearchList.PART_SNIPPET, query.replace(" ", "%20"), maxResults, pageToken, order, SearchList.TYPE_VIDEO, location, locationRadius, publishedBefore, publishedAfter)), SearchList.class);
	}
	
	/**
	 * 
	 * @param part             Either snippet, contentDetails, or subscriberSnippet
	 * @param channelId        The channelId of the channel to get subscribtions.
	 * @param forChannelId     May be empty. Comma-separated list.
	 * @param maxResults       Must be between 0 and 50.
	 * @param order            May be empty. Order by alphabetical, relevance, and unread.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public SubscriptionsList getSubscriptionsByChannel(String part, String channelId, String forChannelId, int maxResults, String order, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(SubscriptionsList.getSubscriptionsByChannelUrl(data_api_key, part, channelId, forChannelId, maxResults, order, pageToken)), SubscriptionsList.class);
	}
	
	/**
	 * 
	 * @param part             Either snippet, contentDetails, or subscriberSnippet
	 * @param ids              Comma-separated list of subscription ids.
	 * @param forChannelId     May be empty. Comma-separated list.
	 * @param maxResults       Must be between 0 and 50.
	 * @param order            May be empty. Order by alphabetical, relevance, and unread.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public SubscriptionsList getSubscriptionsByIds(String part, String ids, String forChannelId, int maxResults, String order, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(SubscriptionsList.getSubscriptionsByIdsUrl(data_api_key, part, ids, forChannelId, maxResults, order, pageToken)), SubscriptionsList.class);
	}
	
	/**
	 * Retrieves a list of reasons that can be usd to report abusive videos.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public VideoAbuseReportReasonsList getVideoAbuseReportReasons() throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(VideoAbuseReportReasonsList.getVideoAbuseReportReasonsUrl(data_api_key, VideoAbuseReportReasonsList.PART_SNIPPET)), VideoAbuseReportReasonsList.class);
	}
	
	/**
	 * 
	 * @param ids         A comma-separated list of video category ids.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public VideoCategoriesList getVideoCategoriesByIds(String ids) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(VideoCategoriesList.getVideoCategoriesByIdsUrl(data_api_key, VideoCategoriesList.PART_SNIPPET, ids)), VideoCategoriesList.class);
	}
	
	/**
	 * 
	 * @param regionCode       A regionCode which can be found using the getI18nRegions() method.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public VideoCategoriesList getVideoCategoriesByRegionCode(String regionCode) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(VideoCategoriesList.getVideoCategoriesByRegionCodeUrl(data_api_key, VideoCategoriesList.PART_SNIPPET, regionCode)), VideoCategoriesList.class);
	}
	
	/**
	 * Retrieve the metadata for a list of videoIds.
	 * @param part             Choose any of the available part options.
	 * @param ids              A comma-separated list of videoIds;
	 * @param maxResults       Must be between 1 and 50.
	 * @param pageToken        May be empty, use nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public VideosList getVideosById(String part, String ids, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(VideosList.getVideosByIdsUrl(data_api_key, part, ids, maxResults, pageToken)), VideosList.class);
	}
	
	/**
	 * Retrieve a list of the most popular videos based on the regionCode or videoCategoryId
	 * @param part              Choose any of the available part options.
	 * @param regionCode        May be any of the regions found using the getI18nRegions() method.
	 * @param videoCategoryId   May be any of the categories found using the getVideoCategoriesByRegionCode(regionCode) method.
	 * @param maxResults        Must be between 1 and 50.
	 * @param pageToken         May be empty, use nextPageToken or prevPageToken.
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public VideosList getVideosByChart(String part, String regionCode, String videoCategoryId, int maxResults, String pageToken) throws JsonSyntaxException, IOException {
		return gson.fromJson(getJson(VideosList.getVideosByChartUrl(data_api_key, part, VideosList.CHART_MOST_POPULAR, regionCode, videoCategoryId, maxResults, pageToken)), VideosList.class);
	}
	
	private String getJson(String url) throws IOException {
		return getJson(new URL(url));
	}
	
	private String getJson(URL url) throws IOException {
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    String text = "";
        is = url.openStream();
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((line = br.readLine()) != null) {
            text += line;
        }
	    return text;
	}
	
}
