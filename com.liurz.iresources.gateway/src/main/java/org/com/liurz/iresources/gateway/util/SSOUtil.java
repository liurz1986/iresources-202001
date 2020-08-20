package org.com.liurz.iresources.gateway.util;

import java.util.HashMap;
import java.util.Map;

import org.com.liurz.iresources.gateway.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 单点登录 基于redis实现 实现原理： 拦截未登录用户请求，对请求进行用户有效性校验
 * 
 * @ClassName: SSOUtil
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年12月21日 下午7:46:16
 *
 */
@Component
public class SSOUtil {
	private Logger logger = LoggerFactory.getLogger(SSOUtil.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private static String redisKey = "org.dcom.gataway.sso.usercode";
	static Map<String, String> cacheSession = new HashMap<String, String>();

	static {
		cacheSession.put(redisKey + "LIURZ_0001", "0000001");
	}

	// @Autowired
	private RedisService redisService;

	public boolean Author(String token) {
		if (null == token || "" == token) {
			logger.info("token is null");
			return false;
		}
		try {
			Map tokenMapper = objectMapper.readValue(token, Map.class);
			String sessionId = tokenMapper.get("sessionId") == null ? "" : String.valueOf(tokenMapper.get("sessionId"));
			if (null == sessionId || "" == sessionId) {
				logger.info("sessionId is null");
				return false;
			}

			String userCode = tokenMapper.get("userCode") == null ? "" : String.valueOf(tokenMapper.get("userCode"));
			if (null == userCode || "" == userCode) {
				logger.info("userCode is null");
				return false;
			}

			// 根据userCode从redis获取对用的用户sesionId,然后两个sessoinId进行对比，相同验证通过
			// String sessionIdCache = redisService.get(redisKey + userCode);
			String sessionIdCache = cacheSession.get(redisKey + userCode);
			if (sessionId.equalsIgnoreCase(sessionIdCache)) {
				logger.info("SSO验证通过");
				return true;
			} else {
				logger.info("SSO验证失败");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("token parse fail," + e.getMessage());
			return false;
		}
	}
}
