package com.ljy.springboot.docker.managementsystem.dockerServerAPI;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class DockerContainerAPI {

    static OkHttpClient client = new OkHttpClient();


    //获取目标主机容器信息（all=true 代表容器无论运行是否正常都返回）
    public static String queryContainersInfo(String dockerServerIP) throws IOException {
        Request request = new Request.Builder()
                                    .get()
                                    .url(dockerServerIP+"/containers/json?all=true")
                                    .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body().string();
    }

    //创建容器但不运行
    /**
     * 201 Container created successfully
     * 400 bad parameter
     * 404 no such container
     * 406 impossible to attach
     * 409 conflict
     * 500 server error
     * @param dockerServerIP
     * @param containerName
     * @param imageID
     * @return
     */
    public static int createContainer(String dockerServerIP,String containerName,String imageID){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("image",imageID);
        //jsonObject.put("HostConfig","{\"PortBindings\": { \"22/tcp\": [{ \"HostPort\": \""+ hostPort +"\" }] }}");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        Request request = new Request.Builder()
                                    .post(requestBody)
                                    .url(dockerServerIP+"/containers/create"+"?name="+containerName)
                                    .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }

    //运行容器
    /**
     *
     204 no error
     304 container already started
     404 no such container
     500 server error
     * @param dockerServerIP
     * @param containerID
     * @return
     */
    public static int  startContainer(String dockerServerIP ,String containerID){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),"temp");
        Request request = new Request.Builder()
                .post(requestBody)
                .url(dockerServerIP+"/containers/"+containerID+"/start")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }

    //重启容器
    /**
     * 204 no error
     * 404 no such container
     * 500 server error
     * @param dockerServerIP
     * @param containerID
     * @return
     */
    public static int  restartContainer(String dockerServerIP,String containerID){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),"temp");
        Request request = new Request.Builder()
                .post(requestBody)
                .url(dockerServerIP+"/containers/"+containerID+"/restart")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }

    //杀死容器

    /**
     * 204 no error
     * 404 no such container
     * 500 server error
     * @param dockerServerIP
     * @param containerID
     * @return
     */
    public static int  killContainer(String dockerServerIP,String containerID){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),"temp");
        Request request = new Request.Builder()
                .post(requestBody)
                .url(dockerServerIP+"/containers/"+containerID+"/kill")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }

    //移除容器
    /**
     * 204 no error
     * 400 bad parameter
     * 404 no such container
     * 409 conflict
     * 500 server error
     * @param dockerServerIP
     * @param containerID
     * @return
     */
    public static int  removeContainer(String dockerServerIP,String containerID) {
        Request request = new Request.Builder()
                .delete()
                .url(dockerServerIP+"/containers/"+containerID)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }

}
