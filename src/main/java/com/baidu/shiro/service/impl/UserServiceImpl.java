package com.baidu.shiro.service.impl;

import com.baidu.shiro.dao.UserMapper;
import com.baidu.shiro.domain.User;
import com.baidu.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: WH
 * @Date: 2019/3/1 15:17
 * @Version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    public List<String> getRolesByUserName(String username) {
        return userMapper.getRolesByUserName(username);
    }
}
