package mattw.example.service;

import mattw.youtube.datav3.Parts;
import mattw.youtube.datav3.YouTubeData3;
import mattw.youtube.datav3.entrypoints.I18nLanguagesList;
import mattw.youtube.datav3.entrypoints.SearchList;
import mattw.youtube.datav3.entrypoints.YouTubeErrorException;
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
