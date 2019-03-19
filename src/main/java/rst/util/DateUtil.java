package rst.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static final String DATAFORMAT = "yyyyMMdd";
	
	public static String getDate() {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat(DATAFORMAT);
		String time=format.format(date);
		return time;
	}
	public static String getTime() {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("HHmmssSSS");
		String time=format.format(date);
		return time;
	}
	
	public static String addOneDay(String date){
		Date a = parseDate(date, null);
		Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(a); 
	     calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
	     a=calendar.getTime();
		return formatDate(a, null);
	}
	
	/**
	 * 
	 * parseDate:将日期字符串按照指定格式转换为日期类型 <br/>
	 * @author Kevin Chen
	 * @param date 日期字符串
	 * @param pattern 日期格式
	 * @return
	 * @since JDK 1.6
	 */
	public static Date parseDate(String date ,String pattern){
		if (pattern == null) {
			pattern = DATAFORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("日期格式错误[{}]",date);
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 
	 * formatDate:根据pattern 格式化日期为字符串. <br/>
	 *
	 * @author Kevin Chen
	 * @param date
	 * @param pattern
	 * @return
	 * @since JDK 1.6
	 */
	public static String formatDate(Date date , String pattern ){
		if (pattern == null) {
			pattern = DATAFORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
		
	}
	public static int compareDate(Date dt1, Date dt2) {
     
            //System.out.println(dt1.getTime());  
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
       
        
    }
	
	public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	
	public static String getYesterday(){
		Calendar   cal   =   Calendar.getInstance();
		  cal.add(Calendar.DATE,   -1);
		  String yesterday = new SimpleDateFormat( DATAFORMAT).format(cal.getTime());
		  System.out.println(yesterday);
		return yesterday;
	}
	
	public static void main(String[] args) {
		getYesterday();
		System.out.println(getDate());
	}
}
