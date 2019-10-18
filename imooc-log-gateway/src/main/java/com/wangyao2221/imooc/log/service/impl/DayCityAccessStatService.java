package com.wangyao2221.imooc.log.service.impl;

import com.wangyao2221.imooc.log.entity.DayCityAccessStat;
import com.wangyao2221.imooc.log.mapper.DayCityAccessStatMapper;
import com.wangyao2221.imooc.log.service.IDayCityAccessStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayCityAccessStatService implements IDayCityAccessStatService {
    @Autowired
    DayCityAccessStatMapper dayCityAccessStatMapper;

    @Override
    public List<DayCityAccessStat> findAll() {
        return dayCityAccessStatMapper.findAll();
    }
}
