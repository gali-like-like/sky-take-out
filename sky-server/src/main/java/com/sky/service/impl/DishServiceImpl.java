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
    public Boolean insert(Dish dish) {
        int insert = dishMapper.insert(dish);
        return insert>0;
    }

    /**
     * 修改数据
     *
     * @param dishDTO 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Boolean update(DishDTO dishDTO) {
        // 对flavor判空
        if (dishDTO.getFlavors() != null) {
            dishDTO.getFlavors().stream()
                    .map(dishFlavor -> dishFlavorMapper.update(dishFlavor))
                    .close();
        }
        Dish dish = dishMapper.queryById(dishDTO.getId());
        DishConvert.INSTANCE.dishDTOToDish(dishDTO, dish);
        int result = dishMapper.update(dish);
        return result > 0;
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

    @Override
    @Transactional
    public Boolean deleteByIds(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            dishMapper.deleteById(Long.parseLong(id));
        }
        return true;
    }

    @Override
    public List<DishVO> queryByCategoryId(Long categoryId) {
        List<Dish> dishList = dishMapper.queryByCategoryId(categoryId);
        List<DishVO> collect = dishList.stream()
                .map(DishConvert.INSTANCE::dishToDishVO)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        return dishMapper.updateStatus(id,status) > 0;
    }
}
