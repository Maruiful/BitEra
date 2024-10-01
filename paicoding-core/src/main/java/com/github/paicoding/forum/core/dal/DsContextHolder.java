package com.github.paicoding.forum.core.dal;

/**
 * 数据源选择上下持有类，用于存储当前选中的是哪个数据源
 * */
public class DsContextHolder {
    /**
     * 使用继承的线程上下文，支持异步时选择传递
     * 使用DsNode，支持链式的数据源切换，如最外层使用master数据源，内部某个方法使用slave数据源；但是请注意，对于事务的场景，不要交叉
     */
    private static final ThreadLocal<DsNode> CONTEXT_HOLDER = new InheritableThreadLocal<>();

    private DsContextHolder() {
    }


    public static void set(String dbType)  {}

    public static String get()  { return null; }


    public static void set(DS ds)  {}


    /**
     * 移除上下文
     */
    public static void reset()  {}

    /**
     * 使用主数据源类型
     */
    public static void master()  {}

    /**
     * 使用从数据源类型
     */
    public static void slave()  {}

    public static class DsNode {
        DsNode pre;
        String ds;

        public DsNode(DsNode parent, String ds) {
            pre = parent;
            this.ds = ds;
        }
    }

}
