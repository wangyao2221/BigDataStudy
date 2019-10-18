package com.wangyao2221.imooc.log.entity;

public class DayCityAccessStat {
    private String day;
    private Long cmdId;
    private String city;
    private String times;
    private Integer timeRank;

    public DayCityAccessStat(String day, Long cmdId, String city, String times, Integer timeRank) {
        this.day = day;
        this.cmdId = cmdId;
        this.city = city;
        this.times = times;
        this.timeRank = timeRank;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getCmdId() {
        return cmdId;
    }

    public void setCmdId(Long cmdId) {
        this.cmdId = cmdId;
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

    public Integer getTimeRank() {
        return timeRank;
    }

    public void setTimeRank(Integer timeRank) {
        this.timeRank = timeRank;
    }
}
