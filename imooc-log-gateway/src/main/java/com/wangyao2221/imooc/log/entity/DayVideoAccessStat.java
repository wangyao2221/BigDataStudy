package com.wangyao2221.imooc.log.entity;

public class DayVideoAccessStat {
    private String day;
    private Long cmsId;
    private Long time;

    public DayVideoAccessStat(String day, Long cmsId, Long time) {
        this.day = day;
        this.cmsId = cmsId;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getCmsId() {
        return cmsId;
    }

    public void setCmsId(Long cmsId) {
        this.cmsId = cmsId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
