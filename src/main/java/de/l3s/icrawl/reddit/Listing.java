package de.l3s.icrawl.reddit;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Listing")
public class Listing extends Result {
    private String modhash;
    private List<ResultWrapper> children;
    private String before;
    private String after;

    public String getModhash() {
        return modhash;
    }

    public List<ResultWrapper> getChildren() {
        return children;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    void setModhash(String modhash) {
        this.modhash = modhash;
    }

    void setChildren(List<ResultWrapper> children) {
        this.children = children;
    }

    void setBefore(String before) {
        this.before = before;
    }

    void setAfter(String after) {
        this.after = after;
    }

    @Override
    public String toString() {
        return String.format("Listing [children=%s]", children);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Listing)) {
            return false;
        }
        return Objects.equals(children, ((Listing) obj).children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }
}
