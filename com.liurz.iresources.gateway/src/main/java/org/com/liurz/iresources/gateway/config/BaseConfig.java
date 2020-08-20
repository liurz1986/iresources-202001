package org.com.liurz.iresources.gateway.config;

import org.com.liurz.iresources.gateway.entity.RedisVO;
import org.com.liurz.iresources.gateway.service.RedisService;
import org.springframework.beans.factory.annotation.Qualifier;

//@Configuration
public class BaseConfig {

	// @Bean("redisVo")
	// @ConfigurationProperties(prefix = "redis")
	public RedisVO redisVo() {

		return new RedisVO();
	}

	// @Bean
	public RedisService redisService(@Qualifier("redisVo") RedisVO redisVO) {
		RedisService redisService = new RedisService(redisVO.getIp(), redisVO.getPort());
		return redisService;
	}

}
