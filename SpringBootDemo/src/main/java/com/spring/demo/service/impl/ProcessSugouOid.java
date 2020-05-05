package com.spring.demo.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.StringUtils;

public class ProcessSugouOid {
	private static final String TMP_ALARM_STRING = "INSERT INTO `t_init_alarm_oid` VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s', '1', '0', '0');";
	private static final String TMP_CLEAR_EVENT_STRING = "INSERT INTO `t_init_alarm_oid` VALUES ('%s', '%s', '%s', NULL, %d, NULL, '%s', '1', '0', '0');";
	private static final String TMP_RELATION_STRING = "INSERT INTO `t_oid_group_relation`(`id`, `oid_id`, `group_id`, `system_default`) VALUES (%d, '%s', 1009, '1');";
	
	private static int init_num =  5400;
	private static final String fileName = "D:\\WorkingDocument\\2020-04-28国产服务器适配\\sugou.txt";
	
	private static final String file_init_alarm = "D:\\WorkingDocument\\2020-04-28国产服务器适配\\sugou_init_alarm_oid.sql";
	private static final String FILE_INIT_ALARM_EN_STRING = "D:\\WorkingDocument\\2020-04-28国产服务器适配\\sugou_init_alarm_oid_en.sql";
	private static final String file_relation_alarm = "D:\\WorkingDocument\\2020-04-28国产服务器适配\\sugou_relation_alarm.sql";
	
	
	public static void writeStringToFile2(String file, String tmpString) {
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tmpString + "\r\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public static void main(String[] args) {

	        File file = new File(fileName);  
	        BufferedReader reader = null;  
	        try {  
	            reader = new BufferedReader(new FileReader(file));  
	            String tempString = null;  
	            int line = 1;  
	            // 一次读入一行，直到读入null为文件结束  
	            while ((tempString = reader.readLine()) != null) {  
	                // 显示行号  
	                String[] splits = tempString.split(",");
	                if (!StringUtils.isEmpty(splits[4]) && splits[4].contentEquals("5")) {
	                	System.out.println("line " + line + ": " + tempString);
	                	String uuidString = UUID.randomUUID().toString().substring(5).concat("sugou");
	                	String tmp = String.format(TMP_CLEAR_EVENT_STRING, uuidString,splits[1],splits[3], 5, splits[5]);
	                	String tmp_en = String.format(TMP_CLEAR_EVENT_STRING, uuidString,splits[1],splits[2], 5, splits[5]);
	                	String relation = String.format(TMP_RELATION_STRING, init_num, uuidString);
	                	writeStringToFile2(file_init_alarm, tmp);
	                	writeStringToFile2(FILE_INIT_ALARM_EN_STRING, tmp_en);
	                	writeStringToFile2(file_relation_alarm, relation);
	                	init_num++;
	                	line++;
					} else if (splits[4].contentEquals("1") || splits[4].contentEquals("2") || splits[4].contentEquals("3")) {
						//第一次读取line
						System.out.println("line " + line + ": " + tempString);
						String uuidAlarm = UUID.randomUUID().toString().substring(5).concat("sugou");
						String uuidClear = UUID.randomUUID().toString().substring(5).concat("sugou");
						String alarm = String.format(TMP_ALARM_STRING, uuidAlarm, splits[1], splits[3], splits[0], Integer
								.valueOf(splits[4]), uuidClear, splits[5]);
						String relation = String.format(TMP_RELATION_STRING, init_num, uuidAlarm);
						String alarm_en = String.format(TMP_ALARM_STRING, uuidAlarm, splits[1], splits[2], splits[0], Integer
								.valueOf(splits[4]), uuidClear, splits[5]);
						writeStringToFile2(file_init_alarm, alarm);
						writeStringToFile2(FILE_INIT_ALARM_EN_STRING, alarm_en);
						writeStringToFile2(file_relation_alarm, relation);
						init_num++;
						line++;
						
						//第二次读取line
						String readLine = reader.readLine();
						String[] splits2 = readLine.split(",");
						System.out.println("line " + line + ": " + readLine);
						String clear_alarm = String.format(TMP_CLEAR_EVENT_STRING, uuidClear, splits2[1], splits2[3], 0, splits2[5]);
						String clear_relation = String.format(TMP_RELATION_STRING, init_num, uuidClear);
						String clear_alarm_en = String.format(TMP_CLEAR_EVENT_STRING, uuidClear, splits2[1], splits2[2], 0, splits2[5]);
						writeStringToFile2(file_init_alarm, clear_alarm);
						writeStringToFile2(FILE_INIT_ALARM_EN_STRING, clear_alarm_en);
						writeStringToFile2(file_relation_alarm, clear_relation);
						init_num++;
						line++;
					}
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
	}
	
//	public static void main(String[] args) {
//        File file = new File(fileName);  
//        BufferedReader reader = null;  
//        try {  
//            reader = new BufferedReader(new FileReader(file));  
//            String tempString = null;  
//            int line = 1;  
//            // 一次读入一行，直到读入null为文件结束  
//            while ((tempString = reader.readLine()) != null) {  
//                // 显示行号  
//                String[] splits = tempString.split(",");
//                if (!StringUtils.isEmpty(splits[4]) && splits[4].contentEquals("5")) {
//                	System.out.println("line " + line + ": " + tempString);
//                	String uuidString = UUID.randomUUID().toString();
//                	String tmp = String.format(TMP_CLEAR_EVENT_STRING, uuidString,splits[1],splits[2], 5, splits[5]);
////                	String relation = String.format(TMP_RELATION_STRING, init_num, uuidString);
//                	writeStringToFile2(FILE_INIT_ALARM_EN_STRING, tmp);
////                	writeStringToFile2(file_relation_alarm, relation);
//                	init_num++;
//                	line++;
//				} else if (splits[4].contentEquals("1") || splits[4].contentEquals("2") || splits[4].contentEquals("3")) {
//					//第一次读取line
//					System.out.println("line " + line + ": " + tempString);
//					String uuidAlarm = UUID.randomUUID().toString();
//					String uuidClear = UUID.randomUUID().toString();
//					String alarm = String.format(TMP_ALARM_STRING, uuidAlarm, splits[1], splits[2], splits[0], Integer
//							.valueOf(splits[4]), uuidClear, splits[5]);
////					String relation = String.format(TMP_RELATION_STRING, init_num, uuidAlarm);
//					writeStringToFile2(FILE_INIT_ALARM_EN_STRING, alarm);
////					writeStringToFile2(file_relation_alarm, relation);
//					init_num++;
//					line++;
//					
//					//第二次读取line
//					String readLine = reader.readLine();
//					String[] splits2 = readLine.split(",");
//					System.out.println("line " + line + ": " + readLine);
//					String clear_alarm = String.format(TMP_CLEAR_EVENT_STRING, uuidClear, splits2[1], splits2[2], 0, splits2[5]);
////					String clear_relation = String.format(TMP_RELATION_STRING, init_num, uuidClear);
//					writeStringToFile2(FILE_INIT_ALARM_EN_STRING, clear_alarm);
////					writeStringToFile2(file_relation_alarm, clear_relation);
//					init_num++;
//					line++;
//				}
//            }  
//            reader.close();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } finally {  
//            if (reader != null) {  
//                try {  
//                    reader.close();  
//                } catch (IOException e1) {  
//                }  
//            }  
//        }  
//
//	}
}
