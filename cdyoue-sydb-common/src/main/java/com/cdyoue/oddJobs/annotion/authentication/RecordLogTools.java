package com.cdyoue.oddJobs.annotion.authentication;

import org.apache.log4j.Logger;

/**
 * <p>Title:RecordLogTools </p>
 * <p>Description: 记录日志工具类 </p>
 * <p>Company: </p>
 * @author lijiang
 * @date 2016-6-12
 */
public class RecordLogTools {

	private static Logger logger = Logger.getLogger(RecordLogTools.class);
	private static String split  = ", ";
	private static String user   = "user=";
	private static String entity   = "entity=";
	private static String param  = "param=";
	private static int LV = 2;//调用层级

	/**
	 *
	 * <p>Title: getClassName
	 * <p>Description: 得到调用者的名字
	 * @author lijiang lijiang@youedata.com
	 * @date 2016年8月22日
	 */
	private static void setClassName() {
		Throwable ex = new Throwable();
		StackTraceElement[] stackElements = ex.getStackTrace();
		if (stackElements != null) {
			String clazz = stackElements[LV].getClassName() + "." + stackElements[LV].getMethodName();
			logger = Logger.getLogger(clazz);
		} else {
			logger = Logger.getLogger(RecordLogTools.class);
		}
	}

	/**
	 * 记录追踪常规日志
	 * @param  userName, String method, Object args
	 * @param method
	 * @param args
	 */
	public static void traceLog(String userName, String entityName, Object args) {
		setClassName();
		logger.info(getLogMsg(userName ,entityName, args));
	}
	/**
	 * 记录异常日志
	 * @param  userName,  method,  args,  e
	 * @param method
	 * @param args
	 */
	public static void errorLog(String userName, String entityName,Object args, Throwable e) {
		setClassName();
		logger.error(getLogMsg(userName,entityName, args), e);
	}

	/**
	 * 得到封装后的参数信息
	 * @param userName
	 * @param args
	 * @return
	 */
	private static String getLogMsg(String userName, String entityName, Object args) {
		StringBuffer infoMsg = new StringBuffer();
		infoMsg.append(split);
		infoMsg.append(user);
		infoMsg.append(userName);
		infoMsg.append(split);
		infoMsg.append(entity);
		infoMsg.append(entityName);
		if (args != null) {
			infoMsg.append(split);
			infoMsg.append(param);
			infoMsg.append(args);
		}
		return infoMsg.toString();
	}

	/**
	 * 针对单一的日志记录
	 * @param msg
	 */
	public static void info(String msg) {
		setClassName();
		logger.info(msg);
	}

	/**
	 * 只提供错误信息和异常信息
	 * @param msg
	 * @param e
	 */
	public static void error(String msg, Throwable e) {
		setClassName();
		logger.error(msg, e);
	}

}
