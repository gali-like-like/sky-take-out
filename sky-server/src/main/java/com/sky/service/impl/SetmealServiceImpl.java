package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.j2objc.annotations.Property;
import com.sky.constant.MessageConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.exception.AccountNotFoundException;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    //修改套餐
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Boolean updateSetmeal(SetmealDTO setmeal) {
        Long id = setmeal.getId();
        if(existsId(id) == 1) {
            try{
                setmealMapper.updateSetmeal(setmeal);
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
            return true;
        }
        return false;
    }
    //分页查询
    @Override
    public PageInfo<Setmeal> pageSetmeal(SetmealPageQueryDTO queryDTO) {
        List<Setmeal> setmeals = setmealMapper.pageSetmeal(queryDTO);
        PageHelper.startPage(queryDTO.getPage(),queryDTO.getPageSize());
        PageInfo<Setmeal> pageInfo = new PageInfo<>(setmeals);
        return pageInfo;
    }
    //修改套餐状态
    @Override
    public Boolean updateSetmealStatus(Integer status, Long id) throws AccountNotFoundException {
        if(existsId(id) == 1) {
            setmealMapper.updateSetmealStatus(status,id);
            return true;
        }
        return false;
    }
    //批量删除套餐
    @Override
    public Boolean deleteSetmals(List<Long> ids) {
        if(Objects.isNull(ids) || ids.isEmpty())
            return false;
        setmealMapper.deleteSetmals(ids);
        return true;
    }
    //添加套餐
    @Override
    public Long addSetmeal(SetmealDTO setmealDTO) {
        try {
            Long result =  setmealMapper.addSetmeal(setmealDTO);
            return result;
        }
        catch(Exception e) {
            log.info("发生sql异常");
            e.printStackTrace();
            return null;
        }
    }
    //根据id查询套餐
    @Override
    public Setmeal getSetmealById(Long id) {
        if(Objects.isNull(id))
            return null;
        return setmealMapper.getSetmealById(id);
    }
    //判断id是否存在
    private Integer existsId(Long id) {
        if(id == null || id < 0) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        Setmeal setmealId = setmealMapper.getSetmealById(id);
        if(setmealId == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        return 1;
    }
}
