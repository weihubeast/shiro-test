package com.baidu.shiro.dao;

import com.baidu.shiro.domain.User;

import java.util.List;

/**
 * @Author: WH
 * @Date: 2019/3/1 15:36
 * @Version 1.0
 */
public interface UserMapper {
   public  User getUserByUserName(String username);
    List<String> getRolesByUserName(String username);
}
