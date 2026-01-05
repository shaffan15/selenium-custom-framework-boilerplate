package com.shaffan.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
			properties.load(fis);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static String getProperty(String key) {
		
//		if(System.getenv(key) != null)
//			return System.getenv(key);
		
		String propertyValue = System.getProperty(key); 
		
		if(propertyValue != null && !propertyValue.isEmpty()) {
			System.out.println("value of " + key + " is: " + System.getProperty(key));
			return System.getProperty(key);
		}
			
		
		return properties.getProperty(key);
	}
}
