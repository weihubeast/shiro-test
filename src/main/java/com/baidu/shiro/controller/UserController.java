package com.baidu.shiro.controller;

import com.baidu.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {


    @RequestMapping(value = "/subLogin", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        if (subject.hasRole("admin")) {
            return  "有admin权限";
        }

        return "无admin权限";
    }
    //注解判断是否有admin角色
    @RequiresRoles("admin")
    @ResponseBody
    @RequestMapping(value = "/testRole", method = RequestMethod.GET)
    public String testRole() {
        return "testRole success";
    }

    @RequiresPermissions("admin1")
    @ResponseBody
    @RequestMapping(value = "/testRole1", method = RequestMethod.GET)
    public String testRole1() {
        return "testRole success";
    }

    @ResponseBody
    @RequestMapping(value = "/testRole2", method = RequestMethod.GET)
    public String testRole2() {
        return "testRole2 success";
    }

    @ResponseBody
    @RequestMapping(value = "/testPerms", method = RequestMethod.GET)
    public String testPerms() {
        return "testPerms success";
    }

    @ResponseBody
    @RequestMapping(value = "/testPerms1", method = RequestMethod.GET)
    public String testPerms1() {
        return "testPerms1 success";
    }
}
