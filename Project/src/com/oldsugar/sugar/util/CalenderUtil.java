package com.oldsugar.sugar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Oldsugar
 *
 */
public class CalenderUtil {
	Calendar cale;
	SimpleDateFormat format;
	{
		cale = null;
		cale = Calendar.getInstance();
		int year = cale.get(Calendar.YEAR);
		int month = cale.get(Calendar.MONTH) + 1;
		int day = cale.get(Calendar.DATE);
		int hour = cale.get(Calendar.HOUR_OF_DAY);
		int minute = cale.get(Calendar.MINUTE);
		int second = cale.get(Calendar.SECOND);
		int dow = cale.get(Calendar.DAY_OF_WEEK);
		int dom = cale.get(Calendar.DAY_OF_MONTH);
		int doy = cale.get(Calendar.DAY_OF_YEAR);
		
		format = new SimpleDateFormat("yyyyMMdd");

	}
	
	/**
	 * 
	 * @return current time
	 */
	public Date returnCurrentDate(){
		return cale.getTime();
	}
	/**
	 * 
	 * @return First Day of month
	 */
	public String returnFirstDayOfMonth() {
        String firstday;
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
	}
	/**
	 * 
	 * @return Last Day of month
	 */
	public String returnLastDayOfMonth() {
		cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(cale.getTime());
	}
	
	public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar calendar1 = Calendar.getInstance();
		try {
			calendar1.setTime(simpleDateFormat.parse("2017-07-01 00:00:00"));
			calendar1.add(Calendar.DATE, 30);
			String three_days_ago = simpleDateFormat.format(calendar1.getTime());
			System.out.println(three_days_ago);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
