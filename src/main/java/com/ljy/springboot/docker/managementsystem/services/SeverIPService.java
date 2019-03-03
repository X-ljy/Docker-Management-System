package com.ljy.springboot.docker.managementsystem.services;

import com.ljy.springboot.docker.managementsystem.entity.ServerIp;
import com.ljy.springboot.docker.managementsystem.mapper.ServerIpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeverIPService {
    @Autowired
    ServerIpMapper serverIpMapper;

    public List<ServerIp> getAllServerIP(){
        return serverIpMapper.getAllServerIP();
    }

    public int deleteServerIP(String ip){
        return serverIpMapper.deleteServerIP(ip);
    }

    public int insertServerIP(String ip){
        return serverIpMapper.insertServerIP(ip);
    }
}
