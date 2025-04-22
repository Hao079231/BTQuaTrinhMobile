package com.example.firebase;

import java.io.Serializable;

public class Video1Model implements Serializable {

    private String title;
    private String desc;
    private String url;
    private String uploaderEmail;
    private Integer likeCount;
    private Integer dislikeCount;

    public Video1Model(String title, String url, String desc, Integer likeCount, String uploaderEmail, Integer dislikeCount) {
        this.title = title;
        this.url = url;
        this.desc = desc;
        this.likeCount = likeCount;
        this.uploaderEmail = uploaderEmail;
        this.dislikeCount = dislikeCount;
    }

    public Video1Model(String title, String desc, String url, String uploaderEmail) {
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.uploaderEmail = uploaderEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploaderEmail() {
        return uploaderEmail;
    }

    public void setUploaderEmail(String uploaderEmail) {
        this.uploaderEmail = uploaderEmail;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
}
