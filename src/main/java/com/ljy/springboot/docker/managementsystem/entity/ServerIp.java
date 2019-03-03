package com.ljy.springboot.docker.managementsystem.entity;

import org.json.JSONObject;

public class ServerIp {
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString (){
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("ip",this.ip);
        return jsonObject.toString();
    }
}
