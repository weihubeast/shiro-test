package com.baidu.shiro.service;

import com.baidu.shiro.domain.User;

import java.util.List;

/**
 * @Author: WH
 * @Date: 2019/3/1 15:16
 * @Version 1.0
 */
public interface UserService {
    User getUserByUserName(String username);
    List<String> getRolesByUserName(String username);
}
