package com.sky;

import com.sky.orther.GenerateData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class GenerateDataTest {

    @Resource
    private GenerateData generateData;

    @Test
    public void generateData() {
        generateData.generateData();
    }
}
