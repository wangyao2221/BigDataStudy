package com.wangyao2221.imooc.log.mapper;

import com.wangyao2221.imooc.log.entity.DayCityAccessStat;
import com.wangyao2221.imooc.log.entity.DayVideoAccessStat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayVideoAccessStatMapper {
    List<DayVideoAccessStat> findAll();
}
