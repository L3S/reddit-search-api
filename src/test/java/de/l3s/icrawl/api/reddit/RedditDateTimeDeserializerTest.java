package de.l3s.icrawl.api.reddit;

import org.joda.time.DateTime;
import org.junit.Test;

import de.l3s.icrawl.api.reddit.RedditDateTimeDeserializer;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

public class RedditDateTimeDeserializerTest {

    @Test
    public void testParseTimestamp() {
        // equal to 2014-08-01T04:46:24-07:00, is off by an hour
        float ts = 1406897184.0f;
        RedditDateTimeDeserializer deserializer = new RedditDateTimeDeserializer();

        DateTime parsed = deserializer.parseTimestamp(ts);
        System.out.println(parsed);
        assertThat(parsed, isA(DateTime.class));
        assertThat(parsed.getYear(), is(2014));
        assertThat(parsed.getMonthOfYear(), is(8));
        assertThat(parsed.getDayOfMonth(), is(1));
    }

}
