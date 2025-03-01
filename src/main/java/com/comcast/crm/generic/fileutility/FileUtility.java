package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String gatDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);
		
		return data;
	}

}
