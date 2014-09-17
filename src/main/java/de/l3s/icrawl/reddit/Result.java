package de.l3s.icrawl.reddit;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonSubTypes({ @Type(Listing.class), @Type(Link.class) })
public class Result {
}
