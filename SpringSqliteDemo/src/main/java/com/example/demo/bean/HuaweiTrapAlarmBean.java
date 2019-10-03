package com.example.demo.bean;

public class HuaweiTrapAlarmBean {
    private String id;
    private String oid;
    private String name;
    private String description;
    private int status;
    private String reverseId;
    private String initAlarmId;
    private String systemDefault;
    private String faultFlag;
    private String logCollectFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReverseId() {
        return reverseId;
    }

    public void setReverseId(String reverseId) {
        this.reverseId = reverseId;
    }

    public String getInitAlarmId() {
        return initAlarmId;
    }

    public void setInitAlarmId(String initAlarmId) {
        this.initAlarmId = initAlarmId;
    }

    public String getSystemDefault() {
        return systemDefault;
    }

    public void setSystemDefault(String systemDefault) {
        this.systemDefault = systemDefault;
    }

    public String getFaultFlag() {
        return faultFlag;
    }

    public void setFaultFlag(String faultFlag) {
        this.faultFlag = faultFlag;
    }

    public String getLogCollectFlag() {
        return logCollectFlag;
    }

    public void setLogCollectFlag(String logCollectFlag) {
        this.logCollectFlag = logCollectFlag;
    }

}
