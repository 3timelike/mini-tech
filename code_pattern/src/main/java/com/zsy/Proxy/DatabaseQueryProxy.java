package com.zsy.Proxy;

import java.util.HashMap;
import java.util.Map;

public class DatabaseQueryProxy implements DatabaseQuery {
    private RealDatabaseQuery realQuery;
    private Map<String, String> cache = new HashMap<>();

    @Override
    public String query(String query) {
        // 缓存检查
        if (cache.containsKey(query)) {
            System.out.println("Returning cached result for: " + query);
            return cache.get(query);
        }

        // 延迟初始化真实对象
        if (realQuery == null) {
            realQuery = new RealDatabaseQuery();
        }

        // 调用真实对象方法
        String result = realQuery.query(query);

        // 缓存结果
        cache.put(query, result);
        System.out.println("New query executed and cached: " + query);

        return result;
    }
}
