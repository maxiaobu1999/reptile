package com.norman.reptile.domain;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    /** '影片名称' */
    private String name;
    /** '更新至201908220' */
    private String title_tip;
    /** '类型：国产剧' */
    private String type;
    /** 标签：搞笑$少女$推理$竞技' */
    private String tag;
    /** 封面 */
    private String cover_url;
    /** '评分' */
    private float score;
    /** '总评分数' */
    private String score_total;
    /** '评分次数' */
    private int score_num;
    /** 别名 */
    private String alias;
    /** 导演 */
    private String director;
    /** 主演 */
    private String actors;
    /** 地区 */
    private String region;
    /** 语言 */
    private String language;
    /** 上映时间 */
    private String release_time;
    /** 片长 */
    private String length;
    /** 更新时间  */
    private Long update_time;
    /** 总播放量  */
    private int views_total;
    /** 今日播放量  */
    private int views_today;
    /** 剧情介绍  */
    private String summary;

    public List<PlayAddress> getPlayAddresses() {
        return playAddresses;
    }

    public void setPlayAddresses(List<PlayAddress> playAddresses) {
        this.playAddresses = playAddresses;
    }

    private List<PlayAddress> playAddresses;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle_tip() {
        return title_tip;
    }

    public void setTitle_tip(String title_tip) {
        this.title_tip = title_tip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getScore_total() {
        return score_total;
    }

    public void setScore_total(String score_total) {
        this.score_total = score_total;
    }

    public int getScore_num() {
        return score_num;
    }

    public void setScore_num(int score_num) {
        this.score_num = score_num;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public int getViews_total() {
        return views_total;
    }

    public void setViews_total(int views_total) {
        this.views_total = views_total;
    }

    public int getViews_today() {
        return views_today;
    }

    public void setViews_today(int views_today) {
        this.views_today = views_today;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title_tip='" + title_tip + '\'' +
                ", type='" + type + '\'' +
                ", cover_url='" + cover_url + '\'' +
                ", score=" + score +
                ", score_total='" + score_total + '\'' +
                ", score_num=" + score_num +
                ", alias='" + alias + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", release_time='" + release_time + '\'' +
                ", length='" + length + '\'' +
                ", update_time=" + update_time +
                ", views_total=" + views_total +
                ", views_today=" + views_today +
                ", summary='" + summary + '\'' +
                ", PlayAddress='" + playAddresses.get(0).toString() + '\'' +
                '}';
    }
}
