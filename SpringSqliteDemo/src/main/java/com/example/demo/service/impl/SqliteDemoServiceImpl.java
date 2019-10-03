package com.example.demo.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.demo.bean.HuaweiTrapAlarmBean;
import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.HuaweiTrapAlarmBeanMapper;
import com.example.demo.mapper.StudentEntityMapper;
import com.example.demo.service.SqliteDemoService;
import com.example.demo.utils.GoogleUtil;
import com.example.demo.utils.SortList;

@Service
public class SqliteDemoServiceImpl implements SqliteDemoService {

	long Number = 10000;

	@Autowired
	StudentEntityMapper studentEntityMapper;

	@Autowired
	HuaweiTrapAlarmBeanMapper huaweiTrapAlarmBeanMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertFirst() {
		List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
		for (int i = 0; i < Number; i++) {
			StudentEntity studentEntity = new StudentEntity(UUID.randomUUID().toString(), "TOM", "12", "SHANGHAI");
			studentEntities.add(studentEntity);
		}
		long startTime = System.currentTimeMillis();
		for (StudentEntity studentEntity : studentEntities) {
			studentEntityMapper.insert(studentEntity);
		}
		long endTime = System.currentTimeMillis();
		long timeDef = endTime - startTime;
		System.out.println("逐条插入" + Number + "条耗时：" + timeDef);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertSec() {
		List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
		for (int i = 0; i < Number; i++) {
			StudentEntity studentEntity = new StudentEntity(UUID.randomUUID().toString(), "TOM", "12", "SHANGHAI");
			studentEntities.add(studentEntity);
		}
		long startTime = System.currentTimeMillis();
		studentEntityMapper.insertList(studentEntities);
		long endTime = System.currentTimeMillis();
		long timeDef = endTime - startTime;
		System.out.println("批量插入" + Number + "条耗时：" + timeDef);
	}

	@Override
	public List<StudentEntity> getList() {
		List<StudentEntity> list = studentEntityMapper.getStudent();
		SortList<StudentEntity> sortList = new SortList<>(StudentEntity.class);
		sortList.getSortList(list, "Name", "ASC");
		return list;
	}

	private boolean ifHasZh(String str) {
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<HuaweiTrapAlarmBean> getOidList() {
		// 1.先从文件中读取OID内容，一共1373行
		File file = new File("D:\\WorkingDocument\\2019-09-05上海工行V2R1新需求\\华为\\snmp trap\\trap0.txt");
		if (!file.exists()) {
			return null;
		}
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (Exception e) {
			return null;
		}
		LineNumberReader reader = new LineNumberReader(fileReader);
		List<HuaweiTrapAlarmBean> resultList = new ArrayList<HuaweiTrapAlarmBean>();
		int num = 1;
		String str = null;
		try {
			while ((str = reader.readLine()) != null) {
				HuaweiTrapAlarmBean huaweiTrapAlarmBean = new HuaweiTrapAlarmBean();
				String[] arrays = str.split(";");
				huaweiTrapAlarmBean.setId(String.valueOf(num) + "-" + UUID.randomUUID().toString());
				huaweiTrapAlarmBean.setName(arrays[2].replace("\"", ""));
				huaweiTrapAlarmBean.setOid(arrays[1].replace("\"", ""));
				huaweiTrapAlarmBean.setDescription(arrays[0].replace("\"", ""));
				huaweiTrapAlarmBean.setStatus(Integer.parseInt(arrays[3]));
				huaweiTrapAlarmBean.setSystemDefault("1");
				huaweiTrapAlarmBean.setFaultFlag("0");
				huaweiTrapAlarmBean.setLogCollectFlag("0");
				resultList.add(huaweiTrapAlarmBean);
				num++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 看一看含有BMC的名字有多少个 是不是1373条
		int reverCount = 0;
		int alarmCount = 0;
		Map<String, HuaweiTrapAlarmBean> mapRever = new HashMap<String, HuaweiTrapAlarmBean>();
		Map<String, HuaweiTrapAlarmBean> mapAlarm = new HashMap<String, HuaweiTrapAlarmBean>();
		for (HuaweiTrapAlarmBean a : resultList) {

			if (a.getDescription().contains("DeassertBMC")) {
				reverCount++;
				mapRever.put(a.getDescription(), a);
			} else {
				alarmCount++;
				mapAlarm.put(a.getDescription(), a);
			}
		}

		List<HuaweiTrapAlarmBean> result = new ArrayList<HuaweiTrapAlarmBean>();

		for (String keyString : mapAlarm.keySet()) {
			// 去掉key后三位
			int length = keyString.length();
			String keyString2 = keyString.substring(0, length - 3);
			String keyString3 = keyString2.concat("DeassertBMC");
			if (mapRever.get(keyString3) != null) {
				String reverIDString = mapRever.get(keyString3).getId();
				mapAlarm.get(keyString).setReverseId(reverIDString);
			}
			// 有三个特殊的OID
			if (keyString.equals("hwSELAlmostFullAssertBMC")) {
				String reverIDString = mapRever.get("hwSELAlmostFullDeassertBMC").getId();
				mapAlarm.get(keyString).setReverseId(reverIDString);
			}
			if (keyString.equals("hwBoardMismatchAssertBMC")) {
				String reverIDString = mapRever.get("hwBoardMismatchDeassertBMC").getId();
				mapAlarm.get(keyString).setReverseId(reverIDString);
			}
			if (keyString.equals("hwNoSDCardAssertBMC")) {
				String reverIDString = mapRever.get("hwNoSDCardDeassertBMC").getId();
				mapAlarm.get(keyString).setReverseId(reverIDString);
			}
			result.add(mapAlarm.get(keyString));
		}

		for (String keyString : mapRever.keySet()) {
			result.add(mapRever.get(keyString));
		}

		for (HuaweiTrapAlarmBean bean : result) {
			huaweiTrapAlarmBeanMapper.insert(bean);
		}
		int number = reverCount + alarmCount;

		return result;
	}

	@Override
	public List<HuaweiTrapAlarmBean> getNotUsedOid() {
		List<HuaweiTrapAlarmBean> list = huaweiTrapAlarmBeanMapper.getStatusAsZero();
		List<HuaweiTrapAlarmBean> result = new ArrayList<HuaweiTrapAlarmBean>();
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : list) {
			String id = huaweiTrapAlarmBean.getId();
			List<HuaweiTrapAlarmBean> alarmBean = huaweiTrapAlarmBeanMapper.getByReverseId(id);
			if (alarmBean.isEmpty()) {
				result.add(huaweiTrapAlarmBean);
			}
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void convertStatus() {
		// 告警级别转换 首先查询没有ReversOid且状态不为0的全部为5事件，如果为
		List<HuaweiTrapAlarmBean> hasNotReversOid = huaweiTrapAlarmBeanMapper.getReverOidISnull();
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : hasNotReversOid) {
			huaweiTrapAlarmBeanMapper.deleteById(huaweiTrapAlarmBean.getId());
			huaweiTrapAlarmBean.setStatus(55555);
			huaweiTrapAlarmBeanMapper.insert(huaweiTrapAlarmBean);
		}

		// 将告警级别为5且有告警恢复的 映射到5 严重
		List<HuaweiTrapAlarmBean> hasStatus5 = huaweiTrapAlarmBeanMapper.getByStatusAndHaveReveroid(5);
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : hasStatus5) {
			huaweiTrapAlarmBeanMapper.deleteById(huaweiTrapAlarmBean.getId());
			huaweiTrapAlarmBean.setStatus(55553);
			huaweiTrapAlarmBeanMapper.insert(huaweiTrapAlarmBean);
		}

		// 将告警级别为4且有告警恢复的 映射到3 严重
		List<HuaweiTrapAlarmBean> hasStatus4 = huaweiTrapAlarmBeanMapper.getByStatusAndHaveReveroid(4);
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : hasStatus4) {
			huaweiTrapAlarmBeanMapper.deleteById(huaweiTrapAlarmBean.getId());
			huaweiTrapAlarmBean.setStatus(44442);
			huaweiTrapAlarmBeanMapper.insert(huaweiTrapAlarmBean);
		}

		// 将告警级别为1或2或3且有告警恢复的 映射到2严重
		List<HuaweiTrapAlarmBean> hasStatus123 = huaweiTrapAlarmBeanMapper.getByStatus123AndHaveReveroid();
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : hasStatus123) {
			huaweiTrapAlarmBeanMapper.deleteById(huaweiTrapAlarmBean.getId());
			huaweiTrapAlarmBean.setStatus(22221);
			huaweiTrapAlarmBeanMapper.insert(huaweiTrapAlarmBean);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public List<HuaweiTrapAlarmBean> googleTrans() {
		List<HuaweiTrapAlarmBean> list = huaweiTrapAlarmBeanMapper.getAllResource();
		GoogleUtil googleUtil = new GoogleUtil().getInstance();
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : list) {
			try {
				if (huaweiTrapAlarmBean.getName() != null && ifHasZh(huaweiTrapAlarmBean.getName())) {
					continue;
				}
				String tmpString = googleUtil.translateText(huaweiTrapAlarmBean.getName(), "en", "zh_cn");
				huaweiTrapAlarmBean.setName(tmpString);
				huaweiTrapAlarmBeanMapper.deleteById(huaweiTrapAlarmBean.getId());
				huaweiTrapAlarmBeanMapper.insert(huaweiTrapAlarmBean);
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<HuaweiTrapAlarmBean> saveInitAlarmIdIntoEnSql() {
		List<HuaweiTrapAlarmBean> list = huaweiTrapAlarmBeanMapper.getAllFromZhcnSql();
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : list) {
			HuaweiTrapAlarmBean enBean = huaweiTrapAlarmBeanMapper.getEnAlarm(huaweiTrapAlarmBean.getId());
			if (enBean != null) {
				enBean.setInitAlarmId(huaweiTrapAlarmBean.getInitAlarmId());
				// 先英文版删除数据库对应的告警
				huaweiTrapAlarmBeanMapper.deleteEnSqlById(huaweiTrapAlarmBean.getId());
				// 再插入新告警
				huaweiTrapAlarmBeanMapper.insertToEnsql(enBean);
			}
		}
		return null;
	}

	@Override
	public String checkInitAlarmIdIntoEnSql() {
		List<HuaweiTrapAlarmBean> list = huaweiTrapAlarmBeanMapper.getAllFromZhcnSql();
		for (HuaweiTrapAlarmBean huaweiTrapAlarmBean : list) {
			HuaweiTrapAlarmBean enBean = huaweiTrapAlarmBeanMapper.getEnAlarm(huaweiTrapAlarmBean.getId());
			if (enBean == null) {
				return "error!id" + huaweiTrapAlarmBean.getId() + "获取为空";
			} else if (!enBean.getOid().equals(huaweiTrapAlarmBean.getOid())
					|| !enBean.getInitAlarmId().equals(huaweiTrapAlarmBean.getInitAlarmId())
					|| enBean.getStatus() != huaweiTrapAlarmBean.getStatus()) {
				return "error";
			} else if (!StringUtils.isEmpty(huaweiTrapAlarmBean.getReverseId()) && !huaweiTrapAlarmBean.getReverseId().equals(enBean.getReverseId())) {
				return "error";
			}
		}
		return "OK";
	}

}
