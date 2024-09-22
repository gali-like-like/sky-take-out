package com.sky.constant;

/**
 * 信息提示常量类
 */
public class MessageConstant {

    public static final String PASSWORD_ERROR = "密码错误";
    public static final String ACCOUNT_NOT_FOUND = "账号不存在";
    public static final String ACCOUNT_LOCKED = "账号被锁定";
    public static final String UNKNOWN_ERROR = "未知错误";
    public static final String USER_NOT_LOGIN = "用户未登录";
    public static final String CATEGORY_BE_RELATED_BY_SETMEAL = "当前分类关联了套餐,不能删除";
    public static final String CATEGORY_BE_RELATED_BY_DISH = "当前分类关联了菜品,不能删除";
    public static final String SHOPPING_CART_IS_NULL = "购物车数据为空，不能下单";
    public static final String ADDRESS_BOOK_IS_NULL = "用户地址为空，不能下单";
    public static final String LOGIN_FAILED = "登录失败";
    public static final String UPLOAD_FAILED = "文件上传失败";
    public static final String SETMEAL_ENABLE_FAILED = "套餐内包含未启售菜品，无法启售";
    public static final String PASSWORD_EDIT_FAILED = "密码修改失败";
    public static final String DISH_ON_SALE = "起售中的菜品不能删除";
    public static final String SETMEAL_ON_SALE = "起售中的套餐不能删除";
    public static final String DISH_BE_RELATED_BY_SETMEAL = "当前菜品关联了套餐,不能删除";
    public static final String ORDER_STATUS_ERROR = "订单状态错误";
    public static final String ORDER_NOT_FOUND = "订单不存在";
    public static final String ORDER_TIME_OUT = "订单超时";
    public static final String ORDER_DOWN_SUCCESS = "下单成功";
    public static final String ORDER_GET_SUCCESS = "接单成功";
    public static final String ORDER_COMPLETE_SUCCESS = "完成订单成功";
    public static final String ORDER_CANCEL_SUCCESS = "取消订单成功";
    public static final String ORDER_REJECT_SUCCESS = "拒绝订单成功";
    public static final String ORDER_DELIVERY_SUCCESS = "派送订单成功";
    public static final String SETMEAL_UPDATE_SUCCESS = "套餐修改成功";
    public static final String SETMEAL_DELETE_SUCCESS = "套餐删除成功";
    public static final String SETMEAL_ADD_SUCCESS = "套餐添加成功";
    public static final String SETMEAL_CHANG_STATUS_SUCCESS = "套餐切换状态成功";
    public static final String SETMEAL_QUERY_SUCCESS = "套餐查询成功";
    public static final String SETMEAL_NOT_FOUND = "套餐不存在";
    public static final String CATEGORY_NOT_FOUND = "分类不存在";
    public static final String EMPLOYEE_ADD_SUCCESS = "添加员工成功";
    public static final String EMPLOYEE_UPDATE_SUCCESS = "修改员工信息成功";
    public static final String EMPLOYEE_DELETE_SUCCESS = "删除员工信息成功";
    public static final String EMPLOYEE_QUERY_SUCCESS = "查询员工信息成功";
    public static final String SYSTEM_ERROR = "系统错误";//上面错误无法描述就用系统错误代替

    public static final String EMPLOYEE_ID_NOT_NULL = "员工编号不能为空";
    public static final String EMPLOYEE_NAME_NOT_NULL = "员工姓名不能为空";
    public static final String EMPLOYEE_USERNAME_NOT_NULL = "员工账号不能为空";
    public static final String EMPLOYEE_IDNUMBER_NOT_NULL = "员工身份证不能为空";
    public static final String EMPLOYEE_SEX_NOT_NULL = "员工性别不能为空";
    public static final String EMPLOYEE_PHONE_NOT_NULL = "员工手机号不能为空";
    public static final String EMPLOYEE_STATUS_NOT_NULL = "员工状态不能为空";
    public static final String EMPLOYEE_STATUS_VALUE_ERROR = "员工状态只有启用和禁用";
    public static final String EMPLOYEE_USERNAME_DUPLICATION = "该账号被其他人使用了,你无法修改为此账号";
    public static final String PAGE_NOT_NULL = "页码不能为空";
    public static final String PAGE_SIZE_NOT_NULL = "分页大小不能为空";
    public static final String PASSWORD_NOT_NULL = "密码不能为空";
    public static final String TIME_OUT_ERROR = "处理超时,请重试";
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String REMOVE_GODDS_SUCCESS = "清空购物车中的这个商品数量";
    public static final String USER_NOT_FOUND = "用户不存在";
    public static final String USER_QUERY_ALL_DISHES_IN_SHOPPING_CART = "查看自己的购物车里的所有菜品";
    public static final String USER_QUERY_ALL_SETMEAL_IN_SHOPPING_CART = "查看自己的购车里的是所有套餐";
    public static final String USER_QUERY_ALL_GOODS_IN_SHOPPING_CART = "查看自己购车里所有的商品";
    public static final String CLEAN_SHOPPING_CART = "清空个人购物车";
    public static final String ADD_GOODS_IN_SHOPPING_CART = "添加商品到个人购物车";
    public static final String UPDATE_GOODS_COUNT = "修改同个商品数量";
    public static final String DISH_STATUS_DISABLE_NOT_ADD = "菜品没上架,不能添加到购物车!";
    public static final String SETMEAL_STATUS_DISABLE_NOT_ADD = "套餐没上架,不能添加到购物车!";

    public static final String USER_DOWN_MESSAGE = "用户下单了";
    public static final String USER_SAY_MESSAGE = "用户催促了";
    public static final String ONE_MORE_ORDER_SUCCESS = "下单成功";
}
