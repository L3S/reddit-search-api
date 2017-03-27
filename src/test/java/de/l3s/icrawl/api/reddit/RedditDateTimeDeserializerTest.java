package de.l3s.icrawl.api.reddit;

import java.time.Instant;
import java.time.OffsetDateTime;

import org.junit.Test;

import de.l3s.icrawl.api.reddit.RedditDateTimeModule.RedditInstantDeserializer;

import static java.time.ZoneOffset.UTC;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

public class RedditDateTimeDeserializerTest {

    @Test
    public void testParseTimestamp() {
        // equal to 2014-08-01T04:46:24-07:00, is off by an hour
        double ts = 1406897184.0;

        Instant parsed = RedditInstantDeserializer.parseTimestamp(ts);
        System.out.println(parsed);
        assertThat(parsed, isA(Instant.class));
        assertThat(parsed, is(OffsetDateTime.of(2014, 8, 1, 12, 46, 24, 0, UTC).toInstant()));
    }

}
