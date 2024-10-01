package com.github.paicoding.forum.core.rabbitmq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RabbitmqConnectionPool {

    private static BlockingQueue<RabbitmqConnection> pool;

    public static void initRabbitmqConnectionPool(String host, int port, String userName, String password,
                                             String virtualhost,
                                           Integer poolSize)  {}

    public static RabbitmqConnection getConnection() throws InterruptedException  { return null; }

    public static void returnConnection(RabbitmqConnection connection)  {}

    public static void close()  {}
}
