package de.l3s.icrawl.api.reddit;

public class RedditApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RedditApiException(String message) {
        super(message);
    }

    public RedditApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
