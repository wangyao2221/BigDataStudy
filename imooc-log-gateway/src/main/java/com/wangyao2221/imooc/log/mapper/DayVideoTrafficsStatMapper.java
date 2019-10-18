package com.wangyao2221.imooc.log.mapper;

import com.wangyao2221.imooc.log.entity.DayVideoAccessStat;
import com.wangyao2221.imooc.log.entity.DayVideoTrafficsStat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayVideoTrafficsStatMapper {
    List<DayVideoTrafficsStat> findAll();
}
