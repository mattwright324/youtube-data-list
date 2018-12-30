package mattw.youtube.datav3;

import mattw.youtube.datav3.entrypoints.*;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class TestYouTubeData3 {

    private String apiKey = "apiKey";
    private String accessToken = "accessToken";
    private boolean useHttps = true;

    private String requestProp = "requestProp";
    private String requestValue = "requestValue";

    YouTubeData3 empty;
    YouTubeData3 full;

    @Before
    public void setup() {
        empty = new YouTubeData3();
        full = new YouTubeData3(apiKey);
    }

    @Test
    public void testConstructors() {
        assertNull(empty.getDataApiKey());
        assertNull(empty.getProfileAccessToken());

        assertEquals(apiKey, full.getDataApiKey());
    }

    @Test
    public void testGetterSetter() {
        empty.setDataApiKey(apiKey);
        empty.setProfileAccessToken(accessToken);
        empty.setUseHttps(useHttps);
        empty.setRequestHeader(requestProp, requestValue);

        Map<String,String> header = empty.getRequestHeaders();

        assertEquals(apiKey, empty.getDataApiKey());
        assertEquals(accessToken, empty.getProfileAccessToken());
        assertEquals(useHttps, empty.getUseHttps());
        assertTrue(header.containsKey(requestProp));
        assertEquals(requestValue, header.get(requestProp));
    }

    @Test
    public void testSpendingCost() {
        assertEquals(0, full.getTotalSpentCost());

        int perSpent = 100;
        int expected = 0;
        for(int i=0; i<10; i++) {
            expected += perSpent;

            full.spendCost(perSpent);

            assertEquals(expected, full.getTotalSpentCost());
        }

    }

    private void testEntrypoint(Class clazz, Callable method) throws Exception {
        Object returned = method.call();

        assertNotNull(returned);
        assertThat(returned, CoreMatchers.instanceOf(clazz));
    }

    @Test
    public void testGetEntrypoints() throws Exception {
        testEntrypoint(ActivitiesList.class, full::activitiesList);
        testEntrypoint(CaptionsList.class, full::captionsList);
        testEntrypoint(ChannelSectionsList.class, full::channelSectionsList);
        testEntrypoint(ChannelsList.class, full::channelsList);
        testEntrypoint(CommentsList.class, full::commentsList);
        testEntrypoint(CommentThreadsList.class, full::commentThreadsList);
        testEntrypoint(GuideCategoriesList.class, full::guideCategoriesList);
        testEntrypoint(I18nLanguagesList.class, full::i18nLanguagesList);
        testEntrypoint(I18nRegionsList.class, full::i18nRegionsList);
        testEntrypoint(PlaylistItemsList.class, full::playlistItemsList);
        testEntrypoint(PlaylistsList.class, full::playlistsList);
        testEntrypoint(SearchList.class, full::searchList);
        testEntrypoint(SubscriptionsList.class, full::subscriptionsList);
        testEntrypoint(VideoAbuseReportReasonsList.class, full::videoAbuseReportReasonsList);
        testEntrypoint(VideoCategoriesList.class, full::videoCategoriesList);
        testEntrypoint(VideosList.class, full::videosList);
    }

}
