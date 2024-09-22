package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.convert.SetmealToSetmealDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.AccountNotFoundException;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealDishService;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
@Service("setmealService")
public class SetmealServiceImpl implements SetmealService {
    private static final Logger log = LoggerFactory.getLogger(SetmealServiceImpl.class);
    @Resource
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishService setmealDishService;
    @Resource
    private DishServiceImpl dishService;

    //修改套餐
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Boolean updateSetmeal(SetmealDTO setmeal) {
        Long id = setmeal.getId();
        List<SetmealDish> setmealDishes = setmeal.getSetmealDishes();
        if (existsId(id) == 1) {
            //以防setmealDish里的setmealid为空
            if (Objects.nonNull(setmealDishes)) {
                for (SetmealDish setmealDish : setmealDishes) {
                    setmealDish.setSetmealId(id);
                    setmealDishService.updateSetmealDish(setmealDish);
                }
            }
            setmealMapper.updateSetmeal(setmeal);
            return true;
        }
        return false;
    }

    //分页查询
    @Override
    public PageInfo<Setmeal> pageSetmeal(SetmealPageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        List<Setmeal> setmeals = setmealMapper.pageSetmeal(queryDTO);
        PageInfo<Setmeal> pageInfo = new PageInfo<>(setmeals);
        return pageInfo;
    }

    //修改套餐状态
    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE, timeout = 5)
    public Boolean updateSetmealStatus(Integer status, Long id) throws AccountNotFoundException {
        if (existsId(id) == 1) {
            setmealMapper.updateSetmealStatus(status, id);
            return true;
        }
        return false;
    }

    //批量删除套餐,及其套餐关联的菜品
    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE, timeout = 5)
    public Boolean deleteSetmals(List<Long> ids) {
        if (Objects.isNull(ids) || ids.isEmpty())
            return false;
        setmealMapper.deleteSetmals(ids);
        for (Long id : ids) {
            setmealDishService.deleteSetmealDishById(id);
        }
        return true;
    }

    //添加套餐
    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE, timeout = 5)
    public Long addSetmeal(SetmealDTO setmealDTO) {
        try {
            Long result = setmealMapper.addSetmeal(setmealDTO);
            List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
            for (SetmealDish setmealDish : setmealDishes) {
                setmealDishService.insertSetmealDish(setmealDish);
            }
            return result;
        } catch (Exception e) {
            log.info("发生sql异常");
            e.printStackTrace();
            return null;
        }
    }

    //根据id查询套餐
    @Override
    public SetmealDTO getSetmealById(Long id) {
        if (Objects.isNull(id))
            return null;
        List<SetmealDish> setmealDishes = setmealDishService.selectSetmealDishById(id);
        Setmeal setmeal = setmealMapper.getSetmealById(id);
        SetmealDTO setmealDTO = SetmealToSetmealDTO.conversionDTO(setmeal, setmealDishes);
        return setmealDTO;
    }

    // 根据分类id查询套餐
    @Override
    public List<Setmeal> getSetmealByCategoryId(Long categoryId) {
        return setmealMapper.getSetmealByCategoryId(categoryId);
    }

    // 根据套餐id查询菜品
    @Override
    public List<DishVO> getMealListBySetmealId(Long setmealId) {
        // 根据套餐id查询套餐菜品关联表
        List<SetmealDish> setmealDishes = setmealDishService.selectSetmealDishById(setmealId);
        // 根据套餐菜品关联表查询菜品
        List<DishVO> dishList = new ArrayList<>();
        setmealDishes.forEach(setmealDish -> {
            DishVO dishVO = dishService.queryById(setmealDish.getDishId());
            dishList.add(dishVO);
        });
        return dishList;
    }

    //判断id是否存在
    private Integer existsId(Long id) {
        if (id == null || id < 0) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        Setmeal setmealId = setmealMapper.getSetmealById(id);
        if (setmealId == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        return 1;
    }

    public Integer getStatusById(Long id) {
        return setmealMapper.getStatusById(id);
    };
}
