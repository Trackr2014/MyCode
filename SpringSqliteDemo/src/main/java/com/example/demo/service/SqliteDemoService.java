package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.HuaweiTrapAlarmBean;
import com.example.demo.entity.StudentEntity;

public interface SqliteDemoService {

    void insertFirst();

    void insertSec();

    List<StudentEntity> getList();

    List<HuaweiTrapAlarmBean> getOidList();

    List<HuaweiTrapAlarmBean> getNotUsedOid();

    void convertStatus();

    List<HuaweiTrapAlarmBean> googleTrans();

	List<HuaweiTrapAlarmBean> saveInitAlarmIdIntoEnSql();

	String checkInitAlarmIdIntoEnSql();

}
