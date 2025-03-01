package com.comcast.crm.generic.webdriverutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	SimpleDateFormat sim;
	
	public int getRandomNumber() {
		Random r = new Random();
		int random = r.nextInt(5000);
		return random;
		
	}
	
	public String getSystemDateYYYYDDMM() {
	    Date dateObj = new Date();
	    sim = new SimpleDateFormat("yyyy-MM-dd");
		String data = sim.format(dateObj);
		return data;
		
	}
	
	public String getReguiredDateYYYYDDMM(int days) {
        
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String regdate = sim.format(cal.getTime());
		return regdate;
		
	}
}
