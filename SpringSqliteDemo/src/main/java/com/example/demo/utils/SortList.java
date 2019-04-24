package com.example.demo.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


 public class SortList<T> {

    private Class<T> classType;
    
    public SortList(Class<T> classType){
        this.classType = classType;
    }
    
    public List<T> getSortList(List<T> list,final String sortFile,final String sortTYpe){
        Collections.sort(list, new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                Method method = getFileMethod(classType, sortFile); 
                try {
                    if(method.invoke(o1) == null || method.invoke(o2) == null){
                        return 0;
                    }
                    Collator collator = Collator.getInstance(Locale.CHINA);
                    String result1 = "";
                    String result2 = "";
                    if(method.getReturnType().getSimpleName().equals("String")){
                        result1 = (String)method.invoke(o1);
                        result2 = (String)method.invoke(o2);
                        if("ASC".equals(sortTYpe.toUpperCase())){
                            return collator.compare(result1, result2);
                        }
                        return -1*collator.compare(result1, result2);
                    }else{
                        result1 = method.invoke(o1).toString();
                        result2 = method.invoke(o2).toString();
                        if("ASC".equals(sortTYpe.toUpperCase())){
                            return new BigDecimal(result1).compareTo(new BigDecimal(result2));
                        }
                        return new BigDecimal(result2).compareTo(new BigDecimal(result1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 
                return 0;
            }
        });
        
        return list;
    }
    //获取指定字段的get方法名
    private Method getFileMethod(Class<T> clazz,String sortFile){
        Method mthod = null;
        try {
            mthod = clazz.getMethod("get" + sortFile.substring(0,1) + sortFile.substring(1));
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return mthod;
    }
}
