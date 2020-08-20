package org.com.liurz.iresources.servcie.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定义访问用户角色
 * 
 * @author admin
 *
 */
public class UserRole {
	/**
	 * 管理员
	 */
	public final static String ADMINISTRATOR = "administrator";
	/**
	 * 系统管理员
	 */
	public final static String SYSTEMADMIN = "systemadmin";
	/**
	 * 生产部角色
	 * 
	 */
	public final static String PRODUCTION = "production";
	/**
	 * 销售部角色
	 */
	public final static String SALE = "sale";
	/**
	 * 人事部角色
	 */
	public final static String PERSONNEL = "personnel";
	/**
	 * 临时角色
	 */
	public final static String TEMPORATY = "temporary";

	/**
	 * 允许所用人访问
	 */
	public final static String ALLUSER = "ALLUSER";

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2019-02-11 23:12:00");
		long time = date.getTime();
		Date currentdate = format.parse("2019-02-12 14:52:00");
		long newtime = currentdate.getTime() - time;
		System.out.println(date.compareTo(currentdate));
	}
}
