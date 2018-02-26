package com.boco.eoms.tawStatisticModel.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * <p>
 * Title:单例，获取spring bean console统一入口
 * </p>
 * <p>
 * Description:取配置文件config/applicationContext-all.xml，通过getBean(String)获取bean
 * </p>
 * <p>
 * Apr 11, 2007 3:44:17 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ApplicationContextHolder {
	/**
	 * spring 上下文
	 */
	private ApplicationContext ctx = null;

	private static ApplicationContextHolder holder = null;

	/**
	 * 单例,通过该方法获取实例
	 * 
	 * @return
	 */
	public static ApplicationContextHolder getInstance() {

		if (holder == null) {
			holder = new ApplicationContextHolder();
		}
		return holder;
	}

	/**
	 * @param ctx
	 *            the ctx to set
	 */
	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	/**
	 * 获取bean
	 * 
	 * @param beanId
	 *            beanId
	 * @return 返回spring配置的对象
	 */
	public Object getBean(String beanId) {
		if (ctx == null) {
			String[] fn = new String[] { "spring/spring-mybatis.xml","spring/spring.xml"};
			ctx = new ClassPathXmlApplicationContext(fn);
		}
		return ctx.getBean(beanId);
	}

	private ApplicationContextHolder() {

	}


	public ApplicationContext getCtx() {
		return ctx;
	}

}
