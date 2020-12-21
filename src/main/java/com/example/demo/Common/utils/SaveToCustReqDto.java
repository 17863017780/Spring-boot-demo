package com.example.demo.Common.utils;


import java.util.List;

/**
 * Class desc:
 * <p>
 * Created by sunzhihao on 2020-02-23
 */

public class SaveToCustReqDto {
        private String id;
        private String activityName;
        private String activityBeginTime;
        private String activityEndTime;
        private Integer twoCategoryId;
        private String imgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityBeginTime() {
        return activityBeginTime;
    }

    public void setActivityBeginTime(String activityBeginTime) {
        this.activityBeginTime = activityBeginTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getTwoCategoryId() {
        return twoCategoryId;
    }

    public void setTwoCategoryId(Integer twoCategoryId) {
        this.twoCategoryId = twoCategoryId;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
