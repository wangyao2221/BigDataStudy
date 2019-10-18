package com.wangyao2221.imooc.log.mapper;

import com.wangyao2221.imooc.log.entity.DayCityAccessStat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayCityAccessStatMapper {
    List<DayCityAccessStat> findAll();
}
