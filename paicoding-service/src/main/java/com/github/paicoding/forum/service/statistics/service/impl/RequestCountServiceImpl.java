package com.github.paicoding.forum.service.statistics.service.impl;

import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.statistics.dto.StatisticsDayDTO;
import com.github.paicoding.forum.service.statistics.repository.dao.RequestCountDao;
import com.github.paicoding.forum.service.statistics.repository.entity.RequestCountDO;
import com.github.paicoding.forum.service.statistics.service.RequestCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
@Slf4j
@Service
public class RequestCountServiceImpl implements RequestCountService {
    

    @Override
    public RequestCountDO getRequestCount(String host) { return null; }

    @Override
    public void insert(String host) {}

    @Override
    public void incrementCount(Long id) {}

    @Override
    public Long getPvTotalCount() { return null; }

    @Override
    public List<StatisticsDayDTO> getPvUvDayList(Integer day) { return null; }

    @Override
    public long count() { return 0; }

    @Override
    public List<RequestCountDO> listRequestCount(PageParam pageParam) { return null; }
}
