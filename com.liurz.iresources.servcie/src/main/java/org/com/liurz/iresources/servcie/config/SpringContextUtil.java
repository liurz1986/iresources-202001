package org.com.liurz.iresources.servcie.config;

//import org.com.liurz.iresources.core.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.config
 * @date 2020/7/19 13:55
 * @Copyright © 2020-2028
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    Logger LOGGER = LoggerFactory.getLogger(SpringContextUtil.class);
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("=============项目加载中,加载上下文，请稍等====================");
        //SpringBeanUtil.applicationContext = applicationContext;
        LOGGER.info("=============加載上下文完成，" ,applicationContext);

    }
}
