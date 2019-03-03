package com.ljy.springboot.docker.managementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;

@Controller
public class HtmlController {

  @GetMapping("/")
    public String Default(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "index";
        }else {
            return "login";
        }
    }
    @GetMapping("/index")
    public String IndexInfo(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "index";
        }else {
            return "login";
        }
    }

    @GetMapping("/images")
    public String Images(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "images";
        }else {
            return "login";
        }

    }

    @GetMapping("/containers")
    public String Containers(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "containers";
        }else {
            return "login";
        }
    }
    @GetMapping("/deleteuser")
    public String DeleteUser(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "deleteUser";
        }else {
            return "login";
        }
    }

    @GetMapping("/reguser")
    public String RegUser(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "regUser";
        }else {
            return "login";
        }
    }

    @GetMapping("/login")
    public String Login(HttpSession httpSession){
        if(httpSession.getAttribute("isLogin") == "true"){
            return "index";
        }else {
            return "login";
        }
    }
}
