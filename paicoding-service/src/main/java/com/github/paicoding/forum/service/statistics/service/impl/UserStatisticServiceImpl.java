package com.github.paicoding.forum.service.statistics.service.impl;

import com.github.paicoding.forum.service.statistics.service.UserStatisticService;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户统计服务
 * */
@Service
public class UserStatisticServiceImpl implements UserStatisticService {
    

    @Override
    public int incrOnlineUserCnt(int add) { return 0; }

    @Override
    public int getOnlineUserCnt() { return 0; }
}
