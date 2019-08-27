package com.norman.reptile.domain;

import java.io.Serializable;

public class PlayAddress implements Serializable {
    private int id;
    /** 影片ID */
    private int movie_id;
    /** 播放类型：ckm3u8 */
    private String media_type;
    /** 资源地址 */
    private String url;
    /** 第01集 */
    private String title;
    /** 失效 */
    private int invalid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    @Override
    public String toString() {
        return "PlayAddress{" +
                "id=" + id +
                ", movie_id=" + movie_id +
                ", media_type='" + media_type + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", invalid=" + invalid +
                '}';
    }
}
