package com.sky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.sky.controller.admin.CategoryController;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
public class CategoryTest {
    @Autowired
    private CategoryController categoryController;

    @Test
    @DisplayName("根据类型查询菜品")
    public void testCategoryByType() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.getCategoryByType(1L);
        log.info("result:\n{}",result);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("根据类型查询分页成功")
    public void testQueryCategoryByTypeSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryPageQueryDTO queryDTO = new CategoryPageQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(3);
        queryDTO.setType(1);
        Result result = categoryController.queryPageCategory(queryDTO);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("根据名字查询分页成功")
    public void testQueryCategoryByNameSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryPageQueryDTO queryDTO = new CategoryPageQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(3);
        queryDTO.setName("牛蛙");
        Result result = categoryController.queryPageCategory(queryDTO);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("根据名字和类型查询分页成功")
    public void testQueryCategoryByNameAndTypeSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryPageQueryDTO queryDTO = new CategoryPageQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(3);
        queryDTO.setName("牛蛙");
        queryDTO.setType(1);
        Result result = categoryController.queryPageCategory(queryDTO);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("修改分类成功")
    public void testUpdateCategorySuccess() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(12L);
        categoryDTO.setType(2);
        categoryDTO.setName("香干炒肉");
        categoryDTO.setSort(100);
        Result result = categoryController.updateCategory(categoryDTO);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("因id不存在而修改分类失败")
    public void testUpdateCategoryFailById() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(111111L);
        categoryDTO.setType(2);
        categoryDTO.setName("酒水饮料");
        categoryDTO.setSort(100);
        Result result = categoryController.updateCategory(categoryDTO);
        assertEquals(0,result.getCode());
    }

    @Test
    @DisplayName("因name重复而修改分类失败")
    public void testUpdateCategoryFailByName() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(15L);
        categoryDTO.setType(2);
        categoryDTO.setName("传统主食");
        categoryDTO.setSort(100);
        Result result = categoryController.updateCategory(categoryDTO);
        assertEquals(0,result.getCode());
    }

    @Test
    @DisplayName("启用状态成功")
    public void testChangeEnableSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.changeStatus(15L,1);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("禁用状态成功")
    public void testChangeDisableSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.changeStatus(15L,0);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("禁用状态失败")
    public void testChangeDisableFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.changeStatus(1220L,0);
        assertEquals(0,result.getCode());
    }

    @Test
    @DisplayName("启用状态失败")
    public void testChangeEnableFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.changeStatus(1220L,1);
        assertEquals(0,result.getCode());
    }

    @Test
    @DisplayName("新增分类成功")
    public void testAddCategorySuccess() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(11L);
        categoryDTO.setSort(30);
        categoryDTO.setName("啤酒");//唯一标识，导致不能重复执行
        categoryDTO.setType(2);
        Result result = categoryController.addCategory(categoryDTO);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("因重复了而添加分类失败")
    public void testAddCategoryFail() throws ExecutionException, InterruptedException, TimeoutException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(11L);
        categoryDTO.setSort(30);
        categoryDTO.setName("传统主食");
        categoryDTO.setType(2);
        Result result = categoryController.addCategory(categoryDTO);
        assertEquals(0,result.getCode());
    }

    @Test
    @DisplayName("根据id删除成功")
    public void testDeleteCategorySuccess() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.deleteCategoryById(12L);
        assertEquals(1,result.getCode());
    }

    @Test
    @DisplayName("根据id删除失败")
    public void testDeleteCategoryFail() throws ExecutionException, InterruptedException, TimeoutException {
        Result result = categoryController.deleteCategoryById(11110L);
        assertEquals(0,result.getCode());
    }
}
