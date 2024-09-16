package com.sky.orther;

import java.util.HashMap;

public class GlobalMapSingleton {

    // 持有单例对象的私有静态变量
    private static volatile GlobalMapSingleton instance = null;

    // 全局保存的 HashMap
    private HashMap<String, Object> globalMap;

    // 私有的构造方法，防止通过 `new` 进行实例化
    private GlobalMapSingleton() {
        globalMap = new HashMap<>();
    }

    // 提供全局访问点，使用双重检查锁实现懒加载
    public static GlobalMapSingleton getInstance() {
        if (instance == null) {
            synchronized (GlobalMapSingleton.class) {
                if (instance == null) {
                    instance = new GlobalMapSingleton();
                }
            }
        }
        return instance;
    }

    // 获取 HashMap
    public HashMap<String, Object> getGlobalMap() {
        return globalMap;
    }

    // 设置 HashMap 的值
    public void putData(String key, Object value) {
        globalMap.put(key, value);
    }

    // 获取 HashMap 中的值
    public Object getData(String key) {
        return globalMap.get(key);
    }
}
