package com.github.paicoding.forum.core.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.github.paicoding.forum.core.async.AsyncUtil;

/** */
public class AlarmUtil extends AppenderBase<ILoggingEvent> {
    private static final long INTERVAL = 10 * 1000 * 60;
    private long lastAlarmTime = 0;

    @Override
    protected void append(ILoggingEvent iLoggingEvent)  {}

    private boolean canAlarm()  { return false; }
}
