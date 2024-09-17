package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.convert.DishConvert;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品(Dish)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
@Service("dishService")
public class DishServiceImpl implements DishService {
    @Resource
    private DishMapper dishMapper;

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DishVO queryById(Long id) {
        Dish dish = dishMapper.queryById(id);
        DishVO dishVO = DishConvert.INSTANCE.dishToDishVO(dish);
        // 查询菜品的flavor
        List<DishFlavor> flavorIds = dishFlavorMapper.queryByDishId(id);
        dishVO.setFlavors(flavorIds);
        return dishVO;
    }

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO   筛选条件
     * @return 查询结果
     */
    @Override
    public HashMap<String, Object> queryByPage(DishPageQueryDTO dishPageQueryDTO) {
        // generate getter
        Integer categoryId = dishPageQueryDTO.getCategoryId();
        String name = dishPageQueryDTO.getName();
        Integer status = dishPageQueryDTO.getStatus();
        // 开启分页
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        List<Dish> dishList = dishMapper.getAllDish(categoryId,name,status);
        if (dishList.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", 0);
            resultMap.put("records", new ArrayList<>());
            return resultMap;
        }
        List<DishVO> collect = dishList.stream()
                .map(DishConvert.INSTANCE::dishToDishVO)
                .collect(Collectors.toList());
        PageInfo<DishVO> pageInfo = new PageInfo<>(collect);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", pageInfo.getTotal());
        hashMap.put("records", pageInfo.getList());
        return hashMap;
    }

    /**
     * 新增数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Dish dish) {
        return dishMapper.insert(dish);
    }

    /**
     * 修改数据
     *
     * @param dishDTO 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int update(DishDTO dishDTO) {
        // 对flavor判空
        if (dishDTO.getFlavors() != null) {
            dishDTO.getFlavors().stream()
                    .map(dishFlavor -> dishFlavorMapper.update(dishFlavor))
                    .close();
        }
        Dish dish = dishMapper.queryById(dishDTO.getId());
        DishConvert.INSTANCE.dishDTOToDish(dishDTO, dish);
        return dishMapper.update(dish);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return dishMapper.deleteById(id) > 0;
    }

    /**
     * 通过多个ID删除数据
     *
     * @param ids 多个主键
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Boolean deleteByIds(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            dishMapper.deleteById(Long.parseLong(id));
        }
        return true;
    }

    /**
     * 根据分类ID查询菜品
     *
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    @Override
    public List<DishVO> queryByCategoryId(Long categoryId) {
        List<Dish> dishList = dishMapper.queryByCategoryId(categoryId);
        return dishList.stream()
                .map(DishConvert.INSTANCE::dishToDishVO)
                .collect(Collectors.toList());
    }

    /**
     * 更新菜品状态
     *
     * @param id 菜品ID
     * @param status 状态
     * @return 更新结果
     */
    @Override
    public int updateStatus(Long id, Integer status) {
        return dishMapper.updateStatus(id,status);
    }
}
