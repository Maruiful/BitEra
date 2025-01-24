package com.github.paicoding.forum.core.async;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.github.paicoding.forum.core.util.EnvUtil;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Closeable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 异步工具类
 * */
@Slf4j
public class AsyncUtil {
    private static final TransmittableThreadLocal<CompletableFutureBridge> THREAD_LOCAL = new TransmittableThreadLocal<>();
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public Thread newThread(Runnable r)  { return null; }
    };
    private static ExecutorService executorService;
    private static SimpleTimeLimiter simpleTimeLimiter;

    static {
        initExecutorService(Runtime.getRuntime().availableProcessors() * 2, 50);
    }

    public static void initExecutorService(int core, int max)  {}


    /**
     * 带超时时间的方法调用执行，当执行时间超过给定的时间，则返回一个超时异常，内部的任务还是正常执行
     * 若超时时间内执行完毕，则直接返回
     *
     * @param time
     * @param unit
     * @param call
     * @param <T>
     * @return
     */
    public static <T> T callWithTimeLimit(long time, TimeUnit unit, Callable<T> call) throws ExecutionException, InterruptedException, TimeoutException {
        return simpleTimeLimiter.callWithTimeout(call, time, unit);
    }


    public static void execute(Runnable call)  {}

    public static <T> Future<T> submit(Callable<T> t) {
        return executorService.submit(t);
    }


    public static boolean sleep(Number timeout, TimeUnit timeUnit)  { return false; }

    public static boolean sleep(Number millis)  { return false; }

    public static boolean sleep(long millis) {
        if (millis > 0L) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException var3) {
                return false;
            }
        }

        return true;
    }


    public static class CompletableFutureBridge implements Closeable {
        private List<CompletableFuture> list;
        private Map<String, Long> cost;
        private String taskName;
        private boolean markOver;
        private ExecutorService executorService;

        public CompletableFutureBridge() {
            this(AsyncUtil.executorService, "CompletableFutureExecute");
        }

        public CompletableFutureBridge(ExecutorService executorService, String task) {
            this.taskName = task;
            list = new CopyOnWriteArrayList<>();
            // 支持排序的耗时记录
            cost = new ConcurrentSkipListMap<>();
            cost.put(task, System.currentTimeMillis());
            this.executorService = TtlExecutors.getTtlExecutorService(executorService);
            this.markOver = false;
        }

        /**
         * 异步执行，带返回结果
         *
         * @param supplier 执行任务
         * @param name     耗时标识
         * @return
         */
        public <T> CompletableFutureBridge async(Supplier<T> supplier, String name) {
            list.add(CompletableFuture.supplyAsync(supplyWithTime(supplier, name), this.executorService));
            return this;
        }

        /**
         * 同步执行，待返回结果
         *
         * @param supplier 执行任务
         * @param name     耗时标识
         * @param <T>      返回类型
         * @return 任务的执行返回结果
         */
        public <T> T sync(Supplier<T> supplier, String name) {
            return supplyWithTime(supplier, name).get();
        }

        /**
         * 异步执行，无返回结果
         *
         * @param run  执行任务
         * @param name 耗时标识
         * @return
         */
        public CompletableFutureBridge async(Runnable run, String name)  { return null; }

        /**
         * 同步执行，无返回结果
         *
         * @param run  执行任务
         * @param name 耗时标识
         * @return
         */
        public CompletableFutureBridge sync(Runnable run, String name)  { return null; }

        private Runnable runWithTime(Runnable run, String name) {
            return () -> {
                startRecord(name);
                try {
                    run.run();
                } finally {
                    endRecord(name);
                }
            };
        }

        private <T> Supplier<T> supplyWithTime(Supplier<T> call, String name) {
            return () -> {
                startRecord(name);
                try {
                    return call.get();
                } finally {
                    endRecord(name);
                }
            };
        }

        public CompletableFutureBridge allExecuted()  { return null; }

        private void startRecord(String name)  {}

        private void endRecord(String name)  {}

        public void prettyPrint() {
            if (EnvUtil.isPro()) {
                // 生产环境默认不打印执行耗时日志
                return;
            }

            if (!this.markOver) {
                // 在格式化输出时，要求所有任务执行完毕
                this.allExecuted();
            }

            StringBuilder sb = new StringBuilder();
            sb.append('\n');
            long totalCost = cost.remove(taskName);
            sb.append("StopWatch '").append(taskName).append("': running time = ").append(totalCost).append(" ms");
            sb.append('\n');
            if (cost.size() <= 1) {
                sb.append("No task info kept");
            } else {
                sb.append("---------------------------------------------\n");
                sb.append("ms         %     Task name\n");
                sb.append("---------------------------------------------\n");
                NumberFormat pf = NumberFormat.getPercentInstance();
                pf.setMinimumIntegerDigits(2);
                pf.setMinimumFractionDigits(2);
                pf.setGroupingUsed(false);
                for (Map.Entry<String, Long> entry : cost.entrySet()) {
                    sb.append(entry.getValue()).append("\t\t");
                    sb.append(pf.format(entry.getValue() / (double) totalCost)).append("\t\t");
                    sb.append(entry.getKey()).append("\n");
                }
            }

            log.info("\n---------------------\n{}\n--------------------\n", sb);
        }

        @Override
        public void close() {
            try {
                if (!this.markOver) {
                    // 做一个兜底，避免业务侧没有手动结束，导致异步任务没有执行完就提前返回结果
                    this.allExecuted();
                }

                AsyncUtil.release();
                prettyPrint();
            } catch (Exception e) {
                log.error("释放耗时上下文异常! {}", taskName, e);
            }
        }
    }

    public static CompletableFutureBridge concurrentExecutor(String... name)  {
        if (name.length > 0) {
            return new CompletableFutureBridge(AsyncUtil.executorService, name[0]);
        }
        return new CompletableFutureBridge();
    }

    /**
     * 开始桥接类
     *
     * @param executorService 线程池
     * @param name            标记名
     * @return 桥接类
     */
    public static CompletableFutureBridge startBridge(ExecutorService executorService, String name)  { return null; }

    /**
     * 获取计时桥接类
     *
     * @return 桥接类
     */
    public static CompletableFutureBridge getBridge()  { return null; }

    /**
     * 释放统计
     */
    public static void release()  {}
}
