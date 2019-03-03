package com.ljy.springboot.docker.managementsystem.controller;

import com.ljy.springboot.docker.managementsystem.entity.ServerIp;
import com.ljy.springboot.docker.managementsystem.mapper.ServerIpMapper;
import com.ljy.springboot.docker.managementsystem.services.SeverIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServerIpController {

    @Autowired
    SeverIPService severIPService;

    @GetMapping("/allServerIP")
    public List<ServerIp> getAllip(){
        return severIPService.getAllServerIP();
    }

    @PostMapping("/deleteIP")
    public int deleteIP(@RequestParam(value = "ip")String ip){
        return severIPService.deleteServerIP(ip);
    }

    @PostMapping("/insertIP")
    public int insertIP(@RequestParam(value = "ip")String ip){
        int i = severIPService.insertServerIP(ip);
        System.out.println(i);
        return i;
    }

}
