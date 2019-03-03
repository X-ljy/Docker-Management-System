package com.ljy.springboot.docker.managementsystem.services;

import com.ljy.springboot.docker.managementsystem.dockerServerAPI.DockerContainerAPI;
import com.ljy.springboot.docker.managementsystem.dockerServerAPI.DockerImageAPI;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContainerService {

    //获取目标主机容器信息（all=true 代表容器无论运行是否正常都返回）
    public  String queryContainersInfo(String dockerServerIP) throws IOException {
        return DockerContainerAPI.queryContainersInfo(dockerServerIP);
    }

    //创建容器但不运行
    public  int createContainer(String dockerServerIP,String containerName,String imageID){
        return DockerContainerAPI.createContainer(dockerServerIP,containerName,imageID);
    }

    //运行容器
    public  int  startContainer(String dockerServerIP ,String containerID){
        return DockerContainerAPI.startContainer(dockerServerIP,containerID);
    }

    //重启容器
    public  int  restartContainer(String dockerServerIP,String containerID){
        return  DockerContainerAPI.restartContainer(dockerServerIP,containerID);
    }
    //杀死容器
    public  int  killContainer(String dockerServerIP,String containerID){
        return DockerContainerAPI.killContainer(dockerServerIP,containerID);
    }

    //移除容器
    public  int  removeContainer(String dockerServerIP,String containerID){
        return DockerContainerAPI.removeContainer(dockerServerIP,containerID);
    }
}
