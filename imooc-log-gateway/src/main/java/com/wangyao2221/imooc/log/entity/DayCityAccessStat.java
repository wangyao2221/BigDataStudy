package com.wangyao2221.imooc.log.entity;

public class DayCityAccessStat {
    private String day;
    private Long cmsId;
    private String city;
    private String times;
    private Integer timesRank;

    public DayCityAccessStat(String day, Long cmsId, String city, String times, Integer timesRank) {
        this.day = day;
        this.cmsId = cmsId;
        this.city = city;
        this.times = times;
        this.timesRank = timesRank;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getTimesRank() {
        return timesRank;
    }

    public void setTimesRank(Integer timesRank) {
        this.timesRank = timesRank;
    }
}
