package mattw.example.service;

import mattw.youtube.datav3.Parts;
import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.entrypoints.*;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class ExampleUsageTest {

    private YouTubeData3 data = new YouTubeData3("AIzaSyAa-o55aIMt4YC0mhPyp8WfGql5DVg_fp4");

    @Test
    public void testExample() throws IOException {
        try {
            SearchList searchList = ((SearchList) data.searchList().part(Parts.SNIPPET))
                    .get("test", SearchList.TYPE_VIDEO, "");

            assertNotNull(searchList);
            assertTrue(searchList.hasItems());

            assertThat("spentCost", data.getTotalSpentCost(), greaterThan(0L));
        } catch (YouTubeErrorException e) {
            fail("Call threw an exception: " + e.getError().getMessage());
        }
    }

    @Test
    public void readmeExamples() throws IOException {
        // Comments posted on a video:
        try {
            CommentThreadsList ctl;
            String pageToken = "";
            int pages = 0;
            do {
                ctl = ((CommentThreadsList) data.commentThreadsList().part(Parts.SNIPPET))
                        .getThreadsByVideo("dQw4w9WgXcQ", pageToken);
                pageToken = ctl.getNextPageToken();

                if(ctl.hasItems()) {
                    for(CommentThreadsList.Item item : ctl.getItems()) {
                        if(item.hasSnippet()) {
                            CommentsList.Item.Snippet topLevelComment = item.getSnippet().getTopLevelComment().getSnippet();

                            String author = topLevelComment.getAuthorDisplayName();
                            String comment = topLevelComment.getTextDisplay();

                            System.out.printf("%s: %s\r\n", author, comment);
                        }
                    }
                } else {
                    System.out.println("CommentThreadsList example didn't return any results.");
                }
                pages++;
            } while (ctl.getNextPageToken() != null && pages < 3);
        } catch (YouTubeErrorException e) {
            e.printStackTrace();

            fail(e.getError().getMessage());
        }

        // Find videos near you!
        try {
            SearchList sl;
            String pageToken = "";
            int pages = 0;
            do {
                // Location takes latitude,longitude and a radius using m, km, ft, or mi.
                sl = ((SearchList) data.searchList().part(Parts.SNIPPET))
                        .order(SearchList.ORDER_DATE)
                        .getByLocation("", pageToken, "40.2822047,-76.9154449", "1mi");
                pageToken = sl.getNextPageToken();

                if(sl.hasItems()) {
                    for(SearchList.Item item : sl.getItems()) {
                        if(item.hasSnippet()) {
                            SearchList.Item.Snippet snippet = item.getSnippet();

                            String date = snippet.getPublishedAt().toString();
                            String channelTitle = snippet.getChannelTitle();
                            String videoTitle = snippet.getTitle();
                            String videoId = item.getId().getVideoId();

                            System.out.printf("%s  https://youtu.be/%s (%s): %s\r\n",
                                    date, videoId, channelTitle, videoTitle);
                        }
                    }
                } else {
                    System.out.println("SearchList example didn't return any results.");
                }
                pages++;
            } while (sl.getNextPageToken() != null && pages < 3);
        } catch (YouTubeErrorException e) {
            e.printStackTrace();

            fail(e.getError().getMessage());
        }

        // See how much quota you used!
        System.out.printf("Total quota units spent this session: %,d\n\n", data.getTotalSpentCost());
    }

    @Test (expected = YouTubeErrorException.class)
    public void testBadApiKey() throws IOException, YouTubeErrorException {
        data.setDataApiKey("helloWorld");

        ((I18nLanguagesList) data.i18nLanguagesList().part(Parts.SNIPPET)).get();
    }

    @Test (expected = IOException.class)
    public void testUnsupportedParts() throws IOException, YouTubeErrorException {
        try {
            ((I18nLanguagesList) data.i18nLanguagesList().parts(Parts.ID, Parts.SUBSCRIBER_SNIPPET)).get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
