package de.l3s.icrawl.reddit;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RedditDateTimeDeserializer extends StdScalarDeserializer<DateTime> {
    public static class RedditJodaModule extends SimpleModule {
        private static final long serialVersionUID = 1L;

        public RedditJodaModule() {
            super(PackageVersion.VERSION);
            addDeserializer(DateTime.class, new RedditDateTimeDeserializer());
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }
    }

    public RedditDateTimeDeserializer() {
        super(DateTime.class);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        if (jp.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
            float floatValue = jp.getFloatValue();
            return parseTimestamp(floatValue);
        } else if (jp.getCurrentToken() == JsonToken.VALUE_FALSE) {
            return null;
        } else {
            throw ctxt.mappingException(DateTime.class);
        }
    }

    DateTime parseTimestamp(float floatTs) {
        long seconds = (long) floatTs;
        int millis = (int) ((floatTs - seconds) * 1000);
        return new DateTime(seconds * 1000, DateTimeZone.UTC).withMillisOfSecond(millis);
    }

}
