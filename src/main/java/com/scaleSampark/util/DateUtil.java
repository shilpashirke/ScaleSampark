package com.scaleSampark.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String setDateTime(Date sentTime) {
		if(sentTime !=null){
			DateFormat dateFormat = new SimpleDateFormat(ScaleSamparkConstant.DD_MM_YYYY_HH_MM_SS);
			return dateFormat.format(sentTime);
		}
		else
			return ScaleSamparkConstant.EMPTY;
	}

}
