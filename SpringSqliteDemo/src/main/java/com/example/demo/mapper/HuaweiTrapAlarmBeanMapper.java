package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.example.demo.bean.HuaweiTrapAlarmBean;

@Mapper
@Component
public interface HuaweiTrapAlarmBeanMapper {

    @Insert("INSERT INTO t_init_alarm(id,oid,name,description,status,reverse_id,init_alarm_id,system_default,fault_flag,log_collect_flag)"
            + "VALUES(#{id},#{oid},#{name},#{description},#{status},#{reverseId},#{initAlarmId},#{systemDefault},#{faultFlag},#{logCollectFlag})")
    void insert(HuaweiTrapAlarmBean bean);

    @Select("SELECT * FROM t_init_alarm")
    List<HuaweiTrapAlarmBean> getHuaweiTrap();

    @Select("SELECT a.* FROM t_init_alarm as a WHERE a.status = 0")
    List<HuaweiTrapAlarmBean> getStatusAsZero();

    @Select("SELECT a.* FROM t_init_alarm as a WHERE a.reverse_id = #{id}")
    List<HuaweiTrapAlarmBean> getByReverseId(String id);

    @Select("SELECT a.* FROM t_init_alarm as a WHERE a.status = #{i}")
    List<HuaweiTrapAlarmBean> getByStatus(int i);

    @Delete("DELETE FROM t_init_alarm WHERE id = #{id}")
    void deleteById(String id);

    List<HuaweiTrapAlarmBean> getReverOidISnull();

    List<HuaweiTrapAlarmBean> getByStatusAndHaveReveroid(@Param("i") int i);

    List<HuaweiTrapAlarmBean> getByStatus123AndHaveReveroid();

    List<HuaweiTrapAlarmBean> getAllResource();

	List<HuaweiTrapAlarmBean> getAllFromZhcnSql();

	HuaweiTrapAlarmBean getEnAlarm(String id);

    @Delete("DELETE FROM t_init_alarm_oid_en WHERE id = #{id}")
	void deleteEnSqlById(String id);

    @Insert("INSERT INTO t_init_alarm_oid_en(id,oid,name,description,status,reverse_id,init_alarm_id,system_default,fault_flag,log_collect_flag)"
            + "VALUES(#{id},#{oid},#{name},#{description},#{status},#{reverseId},#{initAlarmId},#{systemDefault},#{faultFlag},#{logCollectFlag})")
	void insertToEnsql(HuaweiTrapAlarmBean enBean);
}
