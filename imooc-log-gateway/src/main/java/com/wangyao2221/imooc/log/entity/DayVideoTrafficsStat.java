package com.wangyao2221.imooc.log.entity;

public class DayVideoTrafficsStat {
    private String day;
    private Long cmsId;
    private Long traffics;

    public DayVideoTrafficsStat(String day, Long cmsId, Long traffics) {
        this.day = day;
        this.cmsId = cmsId;
        this.traffics = traffics;
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

    public Long getTraffics() {
        return traffics;
    }

    public void setTraffics(Long traffics) {
        this.traffics = traffics;
    }
}
