package com.chenerzhu.crawler.proxy.pool.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpBean {
    @JsonProperty("host")
    private String host;
    @JsonProperty("port")
    private int port;
    @JsonProperty("type")
    private String type;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IpBean [host=" + host + ", port=" + port + ", type=" + type + "]";
    }

}
