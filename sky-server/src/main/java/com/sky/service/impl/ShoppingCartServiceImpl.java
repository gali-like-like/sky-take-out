package com.sky.service.impl;

import com.sky.constant.MessageConstant;
import com.sky.dto.ShoppingCartDTO;
import com.sky.dto.ShoppingCartDishDTO;
import com.sky.dto.ShoppingCartSetmealDTO;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:01
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private DishMapper dishMapper;

    //删除商品
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,timeout = 3)
    public String removeGoods(ShoppingCartDTO shoppingCartDTO) {
        Long userId = shoppingCartDTO.getUserId();
        if (userMapper.getUserById(userId) != null) {
            shoppingCartMapper.removeGoods(shoppingCartDTO);
            return MessageConstant.REMOVE_GODDS_SUCCESS;
        }
        return MessageConstant.USER_NOT_FOUND;
    }
    //查看个人购物车中所有菜品
    @Override
    public HashMap<String, Object> catDishsInShoppingCart(Long userId) {
        HashMap<String,Object> data = new HashMap<>();
        if(userMapper.getUserById(userId) != null) {
            List<ShoppingCartDishDTO> shoppingCartDishDTOS = shoppingCartMapper.catDishsInShoppingCart();
            data.put("msg",MessageConstant.USER_QUERY_ALL_DISHES_IN_SHOPPING_CART);
            data.put("data",shoppingCartDishDTOS);
            return data;
        }
        data.put("msg",MessageConstant.USER_NOT_FOUND);
        data.put("data",null);
        return data;
    }
    //查看个人购物车中所有套餐
    /**
     * 首先获取全部套餐
     * 再逐个套餐获取套餐包含在内的菜品信息
     * 最后返回通过hashMap返回结果
     * */
    @Override
    public HashMap<String, Object> catSetmealsInShoppingCart(Long userId) {
        HashMap<String,Object> data = new HashMap<>();
        if(userMapper.getUserById(userId) != null) {
            List<ShoppingCartSetmealDTO> shoppingCartSetmealDTOS = shoppingCartMapper.catSetmealsInShoppingCart();
            for (ShoppingCartSetmealDTO setmealDTO:shoppingCartSetmealDTOS) {
                Long setmealId = setmealDTO.getId();
                List<ShoppingCartDishDTO> dishDTOS = shoppingCartMapper.getDishBySetmealId(setmealId);
                setmealDTO.setDishDTOS(dishDTOS);
            }

            data.put("msg",MessageConstant.USER_QUERY_ALL_DISHES_IN_SHOPPING_CART);
            data.put("data",shoppingCartSetmealDTOS);
            return data;
        }
        data.put("msg",MessageConstant.USER_NOT_FOUND);
        data.put("data",null);
        return data;
    }
    //查看个人购物车中的所有商品
    @Override
    public HashMap<String,Object> catGoodsInShoppingCart(Long userId) {
        HashMap<String,Object> data = new HashMap<>();
        if(userMapper.getUserById(userId) != null) {
            HashMap<String,Object> actrualData = new HashMap<>();//用来存储菜品和套餐的
            List<ShoppingCartSetmealDTO> shoppingCartSetmealDTOS = shoppingCartMapper.catSetmealsInShoppingCart();
            List<ShoppingCartDishDTO> shoppingCartDishDTOS = shoppingCartMapper.catDishsInShoppingCart();
            actrualData.put("dishes",shoppingCartDishDTOS);
            actrualData.put("setmeales",shoppingCartSetmealDTOS);
            data.put("msg",MessageConstant.USER_QUERY_ALL_GOODS_IN_SHOPPING_CART);
            data.put("data",actrualData);
            return data;
        }
        data.put("msg",MessageConstant.USER_NOT_FOUND);
        data.put("data",null);
        return data;
    };
    //添加商品到个人购物车
    @Override
    public HashMap<String, Object> addGoodsInShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        Long userId = shoppingCartDTO.getUserId();
        Long setmealId = shoppingCartDTO.getSetmealId();
        Long dishId = shoppingCartDTO.getDishId();
        if(Objects.nonNull(setmealId)) {
            Integer status = setmealMapper.getStatusById(setmealId);
            return addGoodsProcess(status,userId);
        }
        else {
            Integer status = dishMapper.getStatusById(dishId);
            return addGoodsProcess(status,userId);
        }

    }

    private HashMap<String,Object> addGoodsProcess(Integer status,Long userId) {
        if(status == 1) {
            if(userMapper.getUserById(userId) != null) {
                shoppingCartMapper.addGoodsInShoppingCart();
                return getHashMap(MessageConstant.ADD_GOODS_IN_SHOPPING_CART,null);
            }
            return getHashMap(MessageConstant.USER_NOT_FOUND,null);
        }
        return getHashMap(MessageConstant.SETMEAL_STATUS_DISABLE_NOT_ADD,null);
    }

    private HashMap<String,Object> getHashMap(String msg,Object data) {
        HashMap<String,Object> hashMapData = new HashMap<>();
        hashMapData.put("msg",msg);
        hashMapData.put("data",data);
        return hashMapData;
    }

    //根据套餐id查询商品
    @Override
    public HashMap<String, Object> getDishBySetmealId(Long setmealId) {
        HashMap<String,Object> data = new HashMap<>();
        if(setmealMapper.getStatusById(setmealId) != null) {
            List<ShoppingCartDishDTO> shoppingCartDishDTOS = shoppingCartMapper.getDishBySetmealId(setmealId);
            data.put("msg",MessageConstant.USER_QUERY_ALL_DISHES_IN_SHOPPING_CART);
            data.put("data",shoppingCartDishDTOS);
            return data;
        }
        data.put("msg",MessageConstant.ADD_GOODS_IN_SHOPPING_CART);
        data.put("data",null);
        return data;
    }
    //清空购物车
    @Override
    public String cleanShoppingCart(Long userId) {
        if(userMapper.getUserById(userId) != null) {
            shoppingCartMapper.addGoodsInShoppingCart();
            return MessageConstant.CLEAN_SHOPPING_CART;
        }
        return MessageConstant.USER_NOT_FOUND;
    }
    //更新某个商品再购物车中的数量
    @Override
    public String updateGoodsCount(ShoppingCartDTO shoppingCartDTO) {
        Long userId = shoppingCartDTO.getUserId();
        if(userMapper.getUserById(userId) != null) {
            shoppingCartMapper.updateGoodsCount(shoppingCartDTO);
            return MessageConstant.CLEAN_SHOPPING_CART;
        }
        return MessageConstant.USER_NOT_FOUND;
    }

//    private HashMap<String,Object> totalAmount(List<ShoppingCartDishDTO> dishDTOS,List<ShoppingCartSetmealDTO> setmealDTOS) {
//        //计算菜品和套餐的总和
//        HashMap<String,Object> detailData = new HashMap<>();
//        if(dishDTOS != null) {
//            detailData.put("dishs",dishDTOS);
//            BigDecimal totalAmount = dishDTOS.parallelStream()
//                    .map(dish -> dish.getPrice().multiply(new BigDecimal(dish.getNumber()))) // 先计算每个 DishDTO 的总价
//                    .reduce(BigDecimal.valueOf(0L), BigDecimal::add);
//            detailData.put("dishTotal",totalAmount);
//        }
//        if(setmealDTOS != null) {
//            //parallelStream 并行流
//            detailData.put("setmeals",setmealDTOS);
//            BigDecimal totalAmount = setmealDTOS.parallelStream().map(dish -> dish.getPrice().multiply(new BigDecimal(dish.getNumber())))
//                    .reduce(BigDecimal.valueOf(0L),BigDecimal::add);
//            detailData.put("setmealTotal",totalAmount);
//        }
//
//        detailData.put("total",)
//        return detailData;
//    }
}
