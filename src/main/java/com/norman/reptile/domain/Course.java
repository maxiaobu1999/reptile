package com.norman.reptile.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 课程
 */
public class Course implements Serializable {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCourseProfile() {
        return courseProfile;
    }

    public void setCourseProfile(String courseProfile) {
        this.courseProfile = courseProfile;
    }


    /**
     * 编号
     */
    private int ID;
    /**
     * 课程编号
     */
    private int courseId;
    /**
     * 封面图片
     */
    private String imgUrl;
    /**
     * 商业产品运营分享 滕蓓蓓
     */
    private String title;
    /**
     * 所属分类：产品&市场
     */
    private String node;

    /**
     * 所属分类：产品&市场》编号
     */
    private int nodeId;
    /**
     * 来源：综合管理部
     */
    private String origin;
    /**
     * 2019-06-04
     */
    private String time;
    /**
     * 播放次数：1885
     */
    private String views;
    /**
     * 课程简介
     */
    private String courseProfile;

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", courseId=" + courseId +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", node='" + node + '\'' +
                ", nodeId=" + nodeId +
                ", origin='" + origin + '\'' +
                ", time='" + time + '\'' +
                ", views='" + views + '\'' +
                ", courseProfile='" + courseProfile + '\'' +
                '}';
    }
}
