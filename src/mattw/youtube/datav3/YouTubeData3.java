package mattw.youtube.datav3;

import mattw.youtube.datav3.entrypoints.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @link https://developers.google.com/youtube/v3/docs/
 * @version 2018-12-08
 * @author mattwright324
 */
public class YouTubeData3 {

    private String dataApiKey;
    private String profileAccessToken;
    private Map<String,String> requestHeaders = new HashMap<>();
    private boolean useHttps = true;
    private AtomicLong spentCost = new AtomicLong(0);

    public YouTubeData3() {}

    public YouTubeData3(String dataApiKey) {
        this.dataApiKey = dataApiKey;
    }

    public String getDataApiKey() {
        return dataApiKey;
    }

    public String getProfileAccessToken() {
        return profileAccessToken;
    }

    public boolean getUseHttps() {
        return useHttps;
    }

    public void setRequestHeader(String name, String value) {
        requestHeaders.put(name, value);
    }

    public void setUseHttps(boolean https) {
        this.useHttps = https;
    }

    public Map<String,String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setDataApiKey(String dataApiKey) {
        this.dataApiKey = dataApiKey;
    }

    public void setProfileAccessToken(String accessToken) {
        this.profileAccessToken = accessToken;
    }

    public synchronized void spendCost(long cost) {
        this.spentCost.addAndGet(cost);
    }

    public long getTotalSpentCost() {
        return this.spentCost.get();
    }

    public ActivitiesList activitiesList() { return new ActivitiesList(this); }
    public CaptionsList captionsList() { return new CaptionsList(this); }
    public ChannelSectionsList channelSectionsList() { return new ChannelSectionsList(this); }
    public ChannelsList channelsList() { return new ChannelsList(this); }
    public CommentsList commentsList() { return new CommentsList(this); }
    public CommentThreadsList commentThreadsList() { return new CommentThreadsList(this); }
    public GuideCategoriesList guideCategoriesList() { return new GuideCategoriesList(this); }
    public I18nLanguagesList i18nLanguagesList() { return new I18nLanguagesList(this); }
    public I18nRegionsList i18nRegionsList() { return new I18nRegionsList(this); }
    public PlaylistItemsList playlistItemsList() { return new PlaylistItemsList(this); }
    public PlaylistsList playlistsList() { return new PlaylistsList(this); }
    public SearchList searchList() { return new SearchList(this); }
    public SubscriptionsList subscriptionsList() { return new SubscriptionsList(this); }
    public VideoAbuseReportReasonsList videoAbuseReportReasonsList() { return new VideoAbuseReportReasonsList(this); }
    public VideoCategoriesList videoCategoriesList() { return new VideoCategoriesList(this); }
    public VideosList videosList() { return new VideosList(this); }
}
