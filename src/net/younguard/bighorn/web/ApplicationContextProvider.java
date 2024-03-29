package net.younguard.bighorn.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider
		implements ApplicationContextAware
{
	// @Resource
	// @Autowired
	private static ApplicationContext ctx = null;

	public static ApplicationContext getApplicationContext()
	{
		return ctx;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException
	{
		this.ctx = ctx;
	}

}
