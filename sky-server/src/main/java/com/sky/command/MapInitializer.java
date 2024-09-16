package com.sky.command;

import com.sky.orther.GlobalMapSingleton;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MapInitializer implements CommandLineRunner {

    public static final String KEY = "SHOP_STATUS";


    @Override
    public void run(String... args) throws Exception {
        // 初始化 HashMap 数据
        GlobalMapSingleton globalMapSingleton = GlobalMapSingleton.getInstance();
        globalMapSingleton.putData(KEY,1);

        System.out.println("店铺状态初始化: " + globalMapSingleton.getData(KEY));
    }
}
