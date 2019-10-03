package com.chenerzhu.crawler.proxy.pool.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.chenerzhu.crawler.proxy.pool.entity.IpBean;


public class ReadFileUtils {
    public static List<IpBean> getIps() {
        //1.先从文件中读取OID内容，一共1373行
        File file = new File("D:\\WorkingDocument\\【Demo】SpingBootWorkCode\\proxylist\\proxy.list");
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
        List<IpBean> resultList = new ArrayList<IpBean>();
        String str = null;
        try {
            while ((str = reader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.parseObject(str);
                IpBean ipBean = JSONObject.toJavaObject(jsonObject, IpBean.class);
                resultList.add(ipBean);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultList;
    }

    public static void main(String[] args) {
        getIps();
    }

}
