package com.ljy.springboot.docker.managementsystem.controller;

import com.ljy.springboot.docker.managementsystem.entity.User;
import com.ljy.springboot.docker.managementsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {


    @Autowired
    UserMapper userMapper;

    @PostMapping("/account")
    public String loginUser(@RequestParam(value = "username")String username,
                            @RequestParam(value = "userpw")String userpw,
                            HttpSession httpSession){
        if(userMapper.getUserPasswd(username).equals(userpw)){
            httpSession.setAttribute("isLogin","true");
            httpSession.setMaxInactiveInterval(1000);
            return "true";
        }else {
            return "false";
        }
    }

    @PostMapping("/getAllUser")
    public Object[] getAllUser(){
        return userMapper.getAllUser().toArray();
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "numberPhone")String numberPhone){
        if (userMapper.deleteUser(numberPhone) == 1){
            return "true";
        }else {
            return "false";
        }
    }

    @PostMapping("/regUser")
    public String regUser(@RequestParam(value = "username")String username,
                          @RequestParam(value = "userpasswd")String userpasswd,
                          @RequestParam(value = "numberPhone")String numberPhone){

        User user = new User();
        user.setName(username);
        user.setPasswd(userpasswd);
        user.setNumberPhone(numberPhone);
        if (userMapper.installUser(user) == 1){
            return "true";
        }else {
            return "false";
        }
    }



}
