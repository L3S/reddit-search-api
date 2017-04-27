package de.l3s.icrawl.api.reddit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Interface to the Reddit search API.
 *
 * Accesses the Reddit search API through the provided JSON interface and
 * extracts the returned results.
 */
public class RedditSearchApi {

    public enum Sort {
        RELEVANCE("relevance"), NEW("new"), HOT("hot"), TOP("top"), COMMENTS("comments");
        private final String param;

        Sort(String param) {
            this.param = param;
        }

        String getParam() {
            return param;
        }
    }

    private static final String SEARCH_URI = "http://www.reddit.com/search.json";
    private static final Header ACCEPT_HEADER = new BasicHeader(HttpHeaders.ACCEPT,
        ContentType.APPLICATION_JSON.withCharset(UTF_8).getMimeType());
    private final ObjectMapper mapper;
    private final HttpClient client;

    public RedditSearchApi(HttpClient client) {
        this.mapper = new ObjectMapper()
            .registerModule(new RedditDateTimeModule())
            .setPropertyNamingStrategy(CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.client = client;
    }

    /* Unhandled parameters: after, before, count, restrict_sr, syntax, t   */
    /**
     * Searches Reddit with the provided query.
     *
     * See <a href="http://www.reddit.com/dev/api#GET_search">the Reddit API
     * documentation</a> for more information about the parameters.
     *
     * @param query
     *            query terms, in total less than 512 characters
     * @param numResults
     *            the number of results to return, must be in (0, 100]
     * @param sort
     *            the results sort order to request
     * @throws IllegalArgumentException
     *             if the arguments have the wrong form
     *
     * @throws RedditApiException
     *             if any exception occurs during the processing of the query
     * @return a (possibly) empty list of results
     */
    public List<Link> search(String query, int numResults, Sort sort) {
        if (numResults <= 0 || numResults > 100) {
            throw new IllegalArgumentException("Number of requested results must be in (0,100], got " + numResults);
        }
        if (query.trim().isEmpty()) {
            throw new IllegalArgumentException("Got empty query");
        }
        if (query.length() > 512) {
            throw new IllegalArgumentException("Query to long, must be less than 512 characters, got " + query.length());
        }
        try {
            String uri = new URIBuilder(SEARCH_URI)
                .addParameter("q", query)
                .addParameter("limit", String.valueOf(numResults))
                .addParameter("sort", sort.getParam())
                .toString();
            HttpGet get = new HttpGet(uri);
            get.addHeader(ACCEPT_HEADER);

            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RedditApiException(
                    "Failed to query for '" + query + "', server returned: " + response.getStatusLine().getStatusCode());
            }
            HttpEntity entity = response.getEntity();
            Result result = parse(entity.getContent()).getData();
            if (result instanceof Listing) {
                Listing listing = (Listing) result;
                List<Link> links = new ArrayList<>(listing.getChildren().size());
                for (ResultWrapper wrapper : listing.getChildren()) {
                    if (wrapper.getData() instanceof Link) {
                        links.add((Link) wrapper.getData());
                    }
                }
                return links;
            } else {
                throw new RedditApiException("Unexpected result type: " + result);
            }
        } catch (URISyntaxException | IOException e) {
            throw new RedditApiException("Exception while searching for '" + query + "'", e);
        }
    }

    protected ResultWrapper parse(String json) throws IOException {
        return mapper.readValue(json, ResultWrapper.class);
    }

    protected ResultWrapper parse(InputStream is) throws IOException {
        return mapper.readValue(is, ResultWrapper.class);
    }
}
