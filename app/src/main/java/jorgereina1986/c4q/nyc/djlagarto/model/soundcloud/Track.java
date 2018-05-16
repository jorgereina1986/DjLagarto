package jorgereina1986.c4q.nyc.djlagarto.model.soundcloud;

import com.google.gson.annotations.SerializedName;

/**
 * Created by c4q-jorgereina on 12/15/15.
 */
public class Track {

    @SerializedName("kind")
    private String kind;

    @SerializedName("id")
    private Integer id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("duration")
    private Integer duration;

    @SerializedName("commentable")
    private Boolean commentable;

    @SerializedName("state")
    private String state;

    @SerializedName("original_content_size")
    private Integer originalContentSize;

    @SerializedName("last_modified")
    private String lastModified;

    @SerializedName("sharing")
    private String sharing;

    @SerializedName("tag_list")
    private String tagList;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("streamable")
    private Boolean streamable;

    @SerializedName("embeddable_by")
    private String embeddableBy;

    @SerializedName("downloadable")
    private Boolean downloadable;

    @SerializedName("purchase_url")
    private Object purchaseUrl;

    @SerializedName("label_id")
    private Object labelId;

    @SerializedName("purchase_title")
    private Object purchaseTitle;

    @SerializedName("genre")
    private String genre;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("label_name")
    private String labelName;

    @SerializedName("release")
    private String release;

    @SerializedName("track_type")
    private String trackType;

    @SerializedName("key_signature")
    private String keySignature;

    @SerializedName("isrc")
    private String isrc;

    @SerializedName("video_url")
    private Object videoUrl;

    @SerializedName("bpm")
    private Object bpm;

    @SerializedName("release_year")
    private Object releaseYear;

    @SerializedName("release_month")
    private Object releaseMonth;

    @SerializedName("release_day")
    private Object releaseDay;

    @SerializedName("original_format")
    private String originalFormat;

    @SerializedName("license")
    private String license;

    @SerializedName("uri")
    private String uri;

    @SerializedName("permalink_url")
    private String permalinkUrl;

    @SerializedName("artwork_url")
    private String artworkUrl;

    @SerializedName("waveform_url")
    private String waveformUrl;

    @SerializedName("stream_url")
    private String streamUrl;

    @SerializedName("playback_count")
    private long playbackCount;

    @SerializedName("download_count")
    private Integer downloadCount;

    @SerializedName("favoritings_count")
    private Integer favoritingsCount;

    @SerializedName("comment_count")
    private Integer commentCount;

    @SerializedName("attachments_uri")
    private String attachmentsUri;

    @SerializedName("user")
    private User user;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getCommentable() {
        return commentable;
    }

    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOriginalContentSize() {
        return originalContentSize;
    }

    public void setOriginalContentSize(Integer originalContentSize) {
        this.originalContentSize = originalContentSize;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Boolean getStreamable() {
        return streamable;
    }

    public void setStreamable(Boolean streamable) {
        this.streamable = streamable;
    }

    public String getEmbeddableBy() {
        return embeddableBy;
    }

    public void setEmbeddableBy(String embeddableBy) {
        this.embeddableBy = embeddableBy;
    }

    public Boolean getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
    }

    public Object getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(Object purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public Object getLabelId() {
        return labelId;
    }

    public void setLabelId(Object labelId) {
        this.labelId = labelId;
    }

    public Object getPurchaseTitle() {
        return purchaseTitle;
    }

    public void setPurchaseTitle(Object purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getTrackType() {
        return trackType;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    public String getKeySignature() {
        return keySignature;
    }

    public void setKeySignature(String keySignature) {
        this.keySignature = keySignature;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public Object getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Object getBpm() {
        return bpm;
    }

    public void setBpm(Object bpm) {
        this.bpm = bpm;
    }

    public Object getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Object releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Object getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(Object releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    public Object getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(Object releaseDay) {
        this.releaseDay = releaseDay;
    }

    public String getOriginalFormat() {
        return originalFormat;
    }

    public void setOriginalFormat(String originalFormat) {
        this.originalFormat = originalFormat;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public void setWaveformUrl(String waveformUrl) {
        this.waveformUrl = waveformUrl;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public long getPlaybackCount() {
        return playbackCount;
    }

    public void setPlaybackCount(long playbackCount) {
        this.playbackCount = playbackCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getFavoritingsCount() {
        return favoritingsCount;
    }

    public void setFavoritingsCount(Integer favoritingsCount) {
        this.favoritingsCount = favoritingsCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getAttachmentsUri() {
        return attachmentsUri;
    }

    public void setAttachmentsUri(String attachmentsUri) {
        this.attachmentsUri = attachmentsUri;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}