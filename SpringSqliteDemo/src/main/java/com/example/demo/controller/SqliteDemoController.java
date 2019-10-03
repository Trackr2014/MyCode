package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.HuaweiTrapAlarmBean;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.SqliteDemoService;

@RestController
@RequestMapping("/v1/sqlite")
public class SqliteDemoController {

    @Autowired
    SqliteDemoService sqliteDemoService;

    @RequestMapping(value = "/insert1", method = RequestMethod.GET)
    public void insertData1() {
        sqliteDemoService.insertFirst();
    }

    @RequestMapping(value = "/insert2", method = RequestMethod.GET)
    public void insertData2() {
        sqliteDemoService.insertSec();
    }

    @RequestMapping(value = "/get-list", method = RequestMethod.GET)
    public List<StudentEntity> getStudent() {

        return sqliteDemoService.getList();
    }

    @RequestMapping(value = "/oid", method = RequestMethod.GET)
    public List<HuaweiTrapAlarmBean> getAlarmOid() {
        return sqliteDemoService.getOidList();
    }

    @RequestMapping(value = "/notUsed", method = RequestMethod.GET)
    public List<HuaweiTrapAlarmBean> getNotUsedReverseOID() {
        return sqliteDemoService.getNotUsedOid();
    }

    @RequestMapping(value = "/covert", method = RequestMethod.GET)
    public void convert() {
        sqliteDemoService.convertStatus();
        return;
    }

    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public List<HuaweiTrapAlarmBean> googleTranslation() {
        return sqliteDemoService.googleTrans();

    }
    
    @RequestMapping(value = "/intoEnSql", method = RequestMethod.GET)
    public List<HuaweiTrapAlarmBean> saveInitAlarmIdIntoEnSql(){
    	return sqliteDemoService.saveInitAlarmIdIntoEnSql();
    }
    
    @RequestMapping(value = "/checkIntoEnSqlOver", method = RequestMethod.GET)
    public String checkInitAlarmIdIntoEnSql(){
    	return sqliteDemoService.checkInitAlarmIdIntoEnSql();
    }

}
