package com.github.paicoding.forum.core.senstive.ibatis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 敏感词缓存
 * */
public class SensitiveMetaCache {
    private static ConcurrentHashMap<String, SensitiveObjectMeta> CACHE = new ConcurrentHashMap<>();

    public static SensitiveObjectMeta get(String key)  { return null; }

    public static void put(String key, SensitiveObjectMeta meta)  {}

    public static void remove(String key)  {}

    public static boolean contains(String key)  { return false; }

    public static SensitiveObjectMeta putIfAbsent(String key, SensitiveObjectMeta meta)  { return null; }

    public static SensitiveObjectMeta computeIfAbsent(String key, Function<String, SensitiveObjectMeta> function)  { return null; }
}
