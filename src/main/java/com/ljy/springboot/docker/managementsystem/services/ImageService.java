package com.ljy.springboot.docker.managementsystem.services;

import com.ljy.springboot.docker.managementsystem.dockerServerAPI.DockerImageAPI;
import org.springframework.stereotype.Service;
import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarOutputStream;

import java.io.*;

import static com.ljy.springboot.docker.managementsystem.dockerServerAPI.DockerImageAPI.deleteImages;
import static com.ljy.springboot.docker.managementsystem.dockerServerAPI.DockerImageAPI.queryAllImageInfo;
@Service
public class ImageService {

    //获取目标主机所有的镜像信息
    public String getAllImageInfo(String dockerServerIP) throws IOException {
        return DockerImageAPI.queryAllImageInfo(dockerServerIP);
    }

    //删除镜像
    public int deleteImage(String dockerServerIP, String imageID){
        return DockerImageAPI.deleteImages(dockerServerIP, imageID);
    }

    //将上传的dockerfile 打包成tar文件
    public  boolean packTarFile(String sourcefilename, String tarFilePath){
        FileOutputStream dest = null;
        TarOutputStream out;
        try {
            dest = new FileOutputStream(tarFilePath);
            out = new TarOutputStream(new BufferedOutputStream(dest));
            File sourceFile = new File(sourcefilename);
            out.putNextEntry(new TarEntry(sourceFile,sourceFile.getName()));
            BufferedInputStream origin = new BufferedInputStream(new FileInputStream(sourceFile));
            int count ;
            byte data[] = new byte[2048];
            while ((count = origin.read(data)) != -1){
                out.write(data,0,count);
            }
            out.flush();
            origin.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("打包文件 ：FileNotFoundException");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("打包文件 ： IOException");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //依照Dockerfile构建镜像
    public int buileByDockerfile(String dockerServerIP,String dockerfilePath,String name,String tag){
        return DockerImageAPI.buileByDockerfile(dockerServerIP,dockerfilePath,name,tag);
    }


}
