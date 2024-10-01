package com.github.paicoding.forum.service.statistics.service.impl;

import com.github.paicoding.forum.api.model.vo.statistics.dto.StatisticsCountDTO;
import com.github.paicoding.forum.api.model.vo.statistics.dto.StatisticsDayDTO;
import com.github.paicoding.forum.service.statistics.service.StatisticsSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 统计配置服务
 */
@Slf4j
@Service
public class StatisticsSettingServiceImpl implements StatisticsSettingService {

    @Override
    public void saveRequestCount(String host) {}

    @Override
    public StatisticsCountDTO getStatisticsCount() { return null; }

    @Override
    public List<StatisticsDayDTO> getPvUvDayList(Integer day) { return null; }

    @Override
    public void download2Excel(Integer day, HttpServletResponse response) {}
}