package com.example.demo.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReadFilesUtil {
    public static Double getStore() {
        //1.先从文件中读取OID内容，一共1373行
        File file = new File("C:\\Users\\wang.pengfei\\Desktop\\rmcdata.lc");
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
        //先除去前8行的错误数据
        for (int i = 1; i <= 8; i++) {
            try {
                reader.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String str = null;
        double result = 0;
        try {
            while ((str = reader.readLine()) != null) {
                if (str.equals("PSU")) {
                    break;
                }
                System.out.println(str);
                String regEx = "[' ']+"; // 一个或多个空格
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(str);
                str = m.replaceAll(",").trim();
                result = result + getAdd(str);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    private static double getAdd(String str) {
        if (!str.isEmpty()) {
            String tmp[] = str.split(",");
            int len = tmp.length;
            String hddLen = tmp[len - 2];
            String nvmeLen = tmp[len - 1];
            double hddLenInt = 0, nvmeLenInt = 0;
            if (!hddLen.equalsIgnoreCase("NA")) {
                hddLenInt = getCovertUnit(hddLen);
            }
            if (!nvmeLen.equalsIgnoreCase("NA")) {
                nvmeLenInt = getCovertUnit(nvmeLen);
            }
            return hddLenInt + nvmeLenInt;
        }
        return 0;
    }

    /**
     * @param hddLen
     * @return 转换成GB统一计算
     */
    private static double getCovertUnit(String hddLen) {
        if (!hddLen.isEmpty()) {
            if (hddLen.contains("G")) {
                String tmp = hddLen.substring(0, hddLen.length() - 1);
                return Double.parseDouble(tmp);
            } else if (hddLen.contains("T")) {
                String tmp = hddLen.substring(0, hddLen.length() - 1);
                return Double.parseDouble(tmp) * 1024;
            } else if (hddLen.contains("M")) {
                String tmp = hddLen.substring(0, hddLen.length() - 1);
                return Double.parseDouble(tmp) / 1024;
            } else if (hddLen.contains("GB")) {
                String tmp = hddLen.substring(0, hddLen.length() - 2);
                return Double.parseDouble(tmp);
            } else if (hddLen.contains("TB")) {
                String tmp = hddLen.substring(0, hddLen.length() - 2);
                return Double.parseDouble(tmp) * 1024;
            } else if (hddLen.contains("MB")) {
                String tmp = hddLen.substring(0, hddLen.length() - 2);
                return Double.parseDouble(tmp) / 1024;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("OOK");
        list.add("wang");
        list.add("tsk");
        int startLine = 0;
        for (String string : list) {
            if (string.contains("wang")) {
                startLine = list.indexOf(string);
            }
        }
//		getStore();
    }

}
