package com.hexun.yewu.orders.constants;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
/**
 * 常量
 * @author zhoudong
 *
 */
public class Constants {

	public static Configuration config =null;
 	static{
 		
		try {
			config= new PropertiesConfiguration("config.properties");
		}catch(Exception e){
			e.printStackTrace();
		}
 	}
}
