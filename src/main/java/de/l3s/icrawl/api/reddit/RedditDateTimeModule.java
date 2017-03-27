package de.l3s.icrawl.api.reddit;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import static java.time.ZoneOffset.UTC;

public class RedditDateTimeModule extends SimpleModule {
    static class RedditOffsetDateTimeDeserializer extends StdScalarDeserializer<OffsetDateTime> {
        public RedditOffsetDateTimeDeserializer() {
            super(OffsetDateTime.class);
        }

        private static final long serialVersionUID = 1L;

        @Override
        public OffsetDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
                double floatValue = jp.getDoubleValue();
                return RedditInstantDeserializer.parseTimestamp(floatValue).atOffset(UTC);
            } else if (jp.getCurrentToken() == JsonToken.VALUE_FALSE) {
                return null;
            } else {
                throw ctxt.mappingException(OffsetDateTime.class);
            }
        }
    }

    static class RedditInstantDeserializer extends StdScalarDeserializer<Instant> {
        public RedditInstantDeserializer() {
            super(Instant.class);
        }

        private static final long serialVersionUID = 1L;

        @Override
        public Instant deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
                double floatValue = jp.getDoubleValue();
                return parseTimestamp(floatValue);
            } else if (jp.getCurrentToken() == JsonToken.VALUE_FALSE) {
                return null;
            } else {
                throw ctxt.mappingException(OffsetDateTime.class);
            }
        }

        static Instant parseTimestamp(double floatTs) {
            long seconds = (long) floatTs;
            int millis = (int) ((floatTs - seconds) * 1000);
            return Instant.ofEpochSecond(seconds, millis);
        }

    }

    private static final long serialVersionUID = 1L;

    public RedditDateTimeModule() {
        super(PackageVersion.VERSION);
        addDeserializer(OffsetDateTime.class, new RedditOffsetDateTimeDeserializer());
        addDeserializer(Instant.class, new RedditInstantDeserializer());
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
