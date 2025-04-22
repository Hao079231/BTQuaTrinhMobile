package com.example.firebase;

public class Video1Model {
    private String title;
    private String desc;
    private String url;
    private String uploaderEmail;
    private int likeCount;
    private int dislikeCount;
    private boolean isLiked; // Thêm trường này
    private boolean isDisliked; // Thêm trường này

    public Video1Model(String title, String desc, String url, String uploaderEmail, int likeCount, int dislikeCount) {
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.uploaderEmail = uploaderEmail;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.isLiked = false;
        this.isDisliked = false;
    }

    public Video1Model(String title, String desc, String url, String uploaderEmail) {
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.uploaderEmail = uploaderEmail;
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.isLiked = false;
        this.isDisliked = false;
    }

    // Getters và setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getUploaderEmail() { return uploaderEmail; }
    public void setUploaderEmail(String uploaderEmail) { this.uploaderEmail = uploaderEmail; }
    public int getLikeCount() { return likeCount; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
    public int getDislikeCount() { return dislikeCount; }
    public void setDislikeCount(int dislikeCount) { this.dislikeCount = dislikeCount; }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
    public boolean isDisliked() { return isDisliked; }
    public void setDisliked(boolean disliked) { isDisliked = disliked; }
}