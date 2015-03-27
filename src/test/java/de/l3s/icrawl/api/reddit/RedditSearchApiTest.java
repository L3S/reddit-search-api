package de.l3s.icrawl.api.reddit;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import de.l3s.icrawl.api.reddit.Link;
import de.l3s.icrawl.api.reddit.Listing;
import de.l3s.icrawl.api.reddit.RedditSearchApi;
import de.l3s.icrawl.api.reddit.ResultWrapper;
import de.l3s.icrawl.api.reddit.RedditSearchApi.Sort;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RedditSearchApiTest {
    private final static String singleResult = "{\"kind\": \"t3\", \"data\": {\"domain\": \"self.MorbidReality\", \"banned_by\": null, \"media_embed\": {}, \"subreddit\": \"MorbidReality\", \"selftext_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Feel free to post any news, discussions, updates, etc. about the recent Ebola epidemic here.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"selftext\": \"Feel free to post any news, discussions, updates, etc. about the recent Ebola epidemic here.\", \"likes\": null, \"secure_media\": null, \"link_flair_text\": null, \"id\": \"2bzkr2\", \"gilded\": 0, \"secure_media_embed\": {}, \"clicked\": false, \"report_reasons\": null, \"author\": \"GoreFox\", \"media\": null, \"score\": 392, \"approved_by\": null, \"over_18\": true, \"hidden\": false, \"thumbnail\": \"nsfw\", \"subreddit_id\": \"t5_2tz1e\", \"edited\": false, \"link_flair_css_class\": null, \"author_flair_css_class\": null, \"downs\": 0, \"saved\": false, \"is_self\": true, \"name\": \"t3_2bzkr2\", \"permalink\": \"/r/MorbidReality/comments/2bzkr2/ebola_outbreak_megathread/\", \"stickied\": false, \"created\": 1406589851.0, \"url\": \"http://www.reddit.com/r/MorbidReality/comments/2bzkr2/ebola_outbreak_megathread/\", \"author_flair_text\": null, \"title\": \"Ebola outbreak megathread.\", \"created_utc\": 1406586251.0, \"ups\": 392, \"num_comments\": 376, \"visited\": false, \"num_reports\": null, \"distinguished\": \"moderator\"}}";

    private final RedditSearchApi reddit = new RedditSearchApi();

    @Test
    public void testDeserialization() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/reddit_search_result.json")) {
            ResultWrapper result = reddit.parse(is);
            assertThat(result.getData(), is(instanceOf(Listing.class)));
            assertThat(((Listing) result.getData()).getChildren(), is(not(empty())));
        }
    }

    @Test
    public void testResultDeserialization() throws Exception {
        ResultWrapper wrapper = reddit.parse(singleResult);
        assertThat(wrapper.getData(), is(instanceOf(Link.class)));

        Link link = (Link) wrapper.getData();

        assertThat(link.getId(), is("2bzkr2"));
        assertThat(link.getAuthor(), is("GoreFox"));
        assertThat(link.isOver18(), is(true));
        assertThat(link.getUps(), is(392));
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: " + RedditSearchApiTest.class.getName() + " query");
            return;
        }
        try (RedditSearchApi reddit = new RedditSearchApi()) {
            List<Link> links = reddit.search(args[0], 10, Sort.RELEVANCE);
            for (int i = 0; i < links.size(); i++) {
                System.out.format("- %2d: %s%n", i + 1, links.get(i));
            }
        }
    }
}
