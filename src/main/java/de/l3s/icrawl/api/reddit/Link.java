package de.l3s.icrawl.api.reddit;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("t3")
public class Link extends Result {
    private String approvedBy;
    private String author;
    private String authorFlairCssClass;
    private String authorFlairText;
    @Nullable
    private String bannedBy;
    private boolean clicked;
    private DateTime created;
    private DateTime createdUtc;
    private String distinguished;
    private String domain;
    private int downs;
    @Nullable
    private DateTime edited;
    private int gilded;
    private boolean hidden;
    private String id;
    private boolean isSelf;
    private String likes;
    private String linkFlairCssClass;
    private String linkFlairText;
    private Object media;
    private Map<String, Object> mediaEmbed;
    private String name;
    private int numComments;
    private Integer numReports;
    @JsonProperty("over_18")
    private boolean over18;
    /** Relative link to Reddit page */
    private String permalink;
    private String reportReasons;
    private boolean saved;
    private int score;
    private String secureMedia;
    private Map<String, Object> secureMediaEmbed;
    private String selftext;
    private String selftextHtml;
    private boolean stickied;
    private String subreddit;
    private String subredditId;
    private String thumbnail;
    private String title;
    private int ups;
    private String url;
    private boolean visited;

    public String getApprovedBy() {
        return approvedBy;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    public String getAuthorFlairText() {
        return authorFlairText;
    }

    public String getBannedBy() {
        return bannedBy;
    }

    public boolean isClicked() {
        return clicked;
    }

    public DateTime getCreated() {
        return created;
    }

    public DateTime getCreatedUtc() {
        return createdUtc;
    }

    public String getDistinguished() {
        return distinguished;
    }

    public String getDomain() {
        return domain;
    }

    public int getDowns() {
        return downs;
    }

    public DateTime getEdited() {
        return edited;
    }

    public int getGilded() {
        return gilded;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("is_self")
    public boolean isSelf() {
        return isSelf;
    }

    public String getLikes() {
        return likes;
    }

    public String getLinkFlairCssClass() {
        return linkFlairCssClass;
    }

    public String getLinkFlairText() {
        return linkFlairText;
    }

    public Object getMedia() {
        return media;
    }

    public Map<String, Object> getMediaEmbed() {
        return mediaEmbed;
    }

    public String getName() {
        return name;
    }

    public int getNumComments() {
        return numComments;
    }

    public Integer getNumReports() {
        return numReports;
    }

    public boolean isOver18() {
        return over18;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getReportReasons() {
        return reportReasons;
    }

    public boolean isSaved() {
        return saved;
    }

    public int getScore() {
        return score;
    }

    public String getSecureMedia() {
        return secureMedia;
    }

    public Map<String, Object> getSecureMediaEmbed() {
        return secureMediaEmbed;
    }

    public String getSelftext() {
        return selftext;
    }

    public String getSelftextHtml() {
        return selftextHtml;
    }

    public boolean isStickied() {
        return stickied;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public int getUps() {
        return ups;
    }

    public String getUrl() {
        return url;
    }

    public boolean isVisited() {
        return visited;
    }

    void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    void setAuthorFlairCssClass(String authorFlairCssClass) {
        this.authorFlairCssClass = authorFlairCssClass;
    }

    void setAuthorFlairText(String authorFlairText) {
        this.authorFlairText = authorFlairText;
    }

    void setBannedBy(String bannedBy) {
        this.bannedBy = bannedBy;
    }

    void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    void setCreated(DateTime created) {
        this.created = created;
    }

    void setCreatedUtc(DateTime createdUtc) {
        this.createdUtc = createdUtc;
    }

    void setDistinguished(String distinguished) {
        this.distinguished = distinguished;
    }

    void setDomain(String domain) {
        this.domain = domain;
    }

    void setDowns(int downs) {
        this.downs = downs;
    }

    void setEdited(DateTime edited) {
        this.edited = edited;
    }

    void setGilded(int gilded) {
        this.gilded = gilded;
    }

    void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    void setId(String id) {
        this.id = id;
    }

    void setSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

    void setLikes(String likes) {
        this.likes = likes;
    }

    void setLinkFlairCssClass(String linkFlairCssClass) {
        this.linkFlairCssClass = linkFlairCssClass;
    }

    void setLinkFlairText(String linkFlairText) {
        this.linkFlairText = linkFlairText;
    }

    void setMedia(Object media) {
        this.media = media;
    }

    void setMediaEmbed(Map<String, Object> mediaEmbed) {
        this.mediaEmbed = mediaEmbed;
    }

    void setName(String name) {
        this.name = name;
    }

    void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    void setNumReports(Integer numReports) {
        this.numReports = numReports;
    }

    void setOver18(boolean over18) {
        this.over18 = over18;
    }

    void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    void setReportReasons(String reportReasons) {
        this.reportReasons = reportReasons;
    }

    void setSaved(boolean saved) {
        this.saved = saved;
    }

    void setScore(int score) {
        this.score = score;
    }

    void setSecureMedia(String secureMedia) {
        this.secureMedia = secureMedia;
    }

    void setSecureMediaEmbed(Map<String, Object> secureMediaEmbed) {
        this.secureMediaEmbed = secureMediaEmbed;
    }

    void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    void setSelftextHtml(String selftextHtml) {
        this.selftextHtml = selftextHtml;
    }

    void setStickied(boolean stickied) {
        this.stickied = stickied;
    }

    void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

    void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setUps(int ups) {
        this.ups = ups;
    }

    void setUrl(String url) {
        this.url = url;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return String.format("Link [title=%s, url=%s]", title, url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(id, ((Link) obj).id);
    }
}
