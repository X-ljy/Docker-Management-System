package com.ljy.springboot.docker.managementsystem.dockerServerAPI;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class DockerImageAPI {

    static  OkHttpClient client = new OkHttpClient();

    //获取目标主机所有的镜像信息
    public static String queryAllImageInfo(String dockerServerIP) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(dockerServerIP+"/images/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response.body().string();
    }

    //删除镜像
    public static int deleteImages(String dockerServerIP, String imageID){
        Request request = new Request.Builder()
                            .delete()
                            .url(dockerServerIP+"/images/"+imageID+"?force=true")
                            .build();
        Response response = null;
        try{
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }

    //依照Dockerfile构建镜像

    /**
     * 200 no error
     * 400 Bad parameter
     * 500 server error
     * @param dockerServerIP
     * @param dockerfilePath
     * @param name
     * @param tag
     * @return
     */
    public static int buileByDockerfile(String dockerServerIP,String dockerfilePath,String name,String tag){
        Response response = null;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),new File(dockerfilePath));
        Request request = new Request.Builder()
                .post(requestBody)
                .url(dockerServerIP+"/build?t="+name+":"+tag)
                .build();
        try {
            response = client.newCall(request).execute();
            System.out.println("BuileByDockerfile" + request.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return  response.code();
    }
}
