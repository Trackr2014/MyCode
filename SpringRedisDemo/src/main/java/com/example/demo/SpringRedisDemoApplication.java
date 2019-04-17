package com.example.demo;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisDemoApplication.class, args);
		String filePath = "";
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				File parteFile = file.getParentFile();
				if (!parteFile.exists()) {
					boolean mkdirs = parteFile.mkdirs();
					if (!mkdirs) {
						System.out.println("ccc");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("dddd");
			}
			System.out.print("aaa");
		} else {
			System.out.println("bbb");
		}
	}

}
