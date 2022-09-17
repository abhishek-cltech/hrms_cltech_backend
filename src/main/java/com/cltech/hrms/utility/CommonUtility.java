package com.cltech.hrms.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cltech.hrms.constant.GlobalConstant;
import com.cltech.hrms.service.common.PropertyService;

public class CommonUtility {
	
	@Autowired
	@Qualifier("propertyServiceImpl")
	PropertyService propertyService;

    public static final DateFormat dateTimeFormate= new SimpleDateFormat(GlobalConstant.DATETIME_FORMAT);
    public static final DateFormat dateParse= new SimpleDateFormat(GlobalConstant.DATE_FORMAT);

    
      public static String converDateToString (Date date) {
    	  try {
    		  if(date!=null) {
    			 return  dateTimeFormate.format(date);
    		  }
    		  
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
    	  
    	  return null;
    	
    }
      public static boolean isNumeric(String strNum) {
		    if (strNum == null) {
		        return false;
		    }
		    try {
		        double d = Double.parseDouble(strNum);
		    } catch (NumberFormatException nfe) {
		        return false;
		    }
		    return true;
		}
      
      
      public static Date parseDate (String date) {
    	  try {
    		  if(date!=null) {
    			 return  dateParse.parse(date);
    		  }
    		  
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
    	  return null;
    	
    }

}
