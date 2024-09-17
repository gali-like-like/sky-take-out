package com.sky;

import com.sky.service.impl.admin.SetmealController;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class SetmealTest {
    @Autowired
    private SetmealController setmealController;
    @Test
    @Order(1)
    @DisplayName("添加套餐成功")
    public void testAddSetmealSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setName("套餐"+String.valueOf(22155L));
        setmealDTO.setPrice(BigDecimal.valueOf(56,2));
        setmealDTO.setImage("/4871.png");
        setmealDTO.setCategoryId(3L);
        Result result = setmealController.addSetmeal(setmealDTO);
        assertEquals(1, result.getCode());
    }

    @Test
    @Order(1)
    @DisplayName("因名字重复而添加套餐失败")
    public void testAddSetmealFail() throws ExecutionException, InterruptedException, TimeoutException {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setName("套餐1");
        setmealDTO.setPrice(BigDecimal.valueOf(100,2));
        setmealDTO.setImage("/a.png");
        setmealDTO.setCategoryId(1L);
        Result result = setmealController.addSetmeal(setmealDTO);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("根据id获取套餐成功")
    @Order(2)
    public void testGetSetmealByIdSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.getSetmealById(100L);
        assertEquals(1, result.getCode());
    }

    @Test
    @DisplayName("根据id获取套餐失败")
    @Order(3)
    public void testGetSetmealByIdFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.getSetmealById(10000000L);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("id为null获取套餐失败")
    @Order(3)
    public void testGetSetmealByNullFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.getSetmealById(null);
        assertEquals(0, result.getCode());
    }


    @Test
    @DisplayName("分页查询")
    @Order(4)
    public void testPageSetmealSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        SetmealPageQueryDTO queryDTO = new SetmealPageQueryDTO();
        queryDTO.setPage(2);
        queryDTO.setPageSize(10);
        queryDTO.setStatus(0);
//        queryDTO.setName("套餐1");
        queryDTO.setCategoryId(2L);
        Result result = setmealController.pageSetmeal(queryDTO);
        assertEquals(1, result.getCode());
    }

    @Test
    @DisplayName("禁用套餐成功")
    @Order(5)
    public void testDisableSetmealSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.updateSetmealStatus(1,100L);
        assertEquals(1, result.getCode());
    }

    @Test
    @DisplayName("启用套餐成功")
    @Order(6)
    public void testEnableSetmealSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.updateSetmealStatus(1,100L);
        assertEquals(1, result.getCode());
    }

    @Test
    @DisplayName("修改套餐状态失败")
    @Order(7)
    public void testChangeSetmealStatusFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.updateSetmealStatus(1,111111L);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("批量删除套餐成功")
    @Order(8)
    public void testDeleteSetmeals() throws ExecutionException, InterruptedException, TimeoutException {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        Result result = setmealController.deleteSetmals(ids);
        assertEquals(1, result.getCode());
    }

    @Test
    @DisplayName("因为没有元素,批量删除套餐失败")
    @Order(8)
    public void testDeleteSetmealsFail() throws ExecutionException, InterruptedException, TimeoutException {
        List<Long> ids = new ArrayList<>();
        Result result = setmealController.deleteSetmals(ids);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("因为null,批量删除套餐失败")
    @Order(8)
    public void testDeleteSetmealsNullFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = setmealController.deleteSetmals(null);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("因名字重复而更新套餐失败")
    @Order(9)
    public void testUpdateSetmealFailByName() throws ExecutionException, InterruptedException, TimeoutException {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setId(100L);
        setmealDTO.setImage("/a.png");
        setmealDTO.setName("套餐2");//名字重复了
        setmealDTO.setPrice(BigDecimal.valueOf(30,2));
        setmealDTO.setCategoryId(1L);
        Result result = setmealController.updateSetmeal(setmealDTO);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("因id不存在而更新套餐失败")
    @Order(9)
    public void testUpdateSetmealFailById() throws ExecutionException, InterruptedException, TimeoutException {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setId(31L);
        setmealDTO.setImage("/a.png");
        setmealDTO.setName("套餐2");//名字重复了
        setmealDTO.setPrice(BigDecimal.valueOf(30,2));
        setmealDTO.setCategoryId(1L);
        Result result = setmealController.updateSetmeal(setmealDTO);
        assertEquals(0, result.getCode());
    }

    @Test
    @DisplayName("因id不存在而更新套餐失败")
    @Order(9)
    public void testUpdateSetmealSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setId(100L);
        setmealDTO.setImage("/a.png");
        setmealDTO.setName("套餐2");//名字重复了
        setmealDTO.setPrice(BigDecimal.valueOf(30,2));
        setmealDTO.setCategoryId(1L);
        Result result = setmealController.updateSetmeal(setmealDTO);
        assertEquals(0, result.getCode());
    }
}
