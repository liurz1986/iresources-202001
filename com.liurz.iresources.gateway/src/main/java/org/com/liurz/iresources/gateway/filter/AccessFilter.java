package org.com.liurz.iresources.gateway.filter;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.com.liurz.iresources.gateway.service.LogService;
import org.com.liurz.iresources.gateway.util.SSOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * 1.用户鉴权 2.记录登录信息
 *
 */
// Zuul过滤器,在实现了自定义过滤器之后，它并不会直接生效，我们还需要为其创建具体的Bean才能启动该过滤器(应用主类中创建)
// 可定义一些与业务无关的通用逻辑实现对请求的过滤和拦截，比如：签名校验、权限校验、请求限流等功能。
@Component
public class AccessFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	private static final String[] IGNORE_URI = { "/login", "/css/", "/js/", "/img/" };
	@Autowired
	private SSOUtil ssoUtil;
	@Autowired
	private LogService logService;

	// 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
	@Override
	public String filterType() {

		return "pre";// pre，代表会在请求被路由之前执行
	}

	// 过滤器的执行顺序
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 判断该过滤器是否需要被执行 可以在此进行部分服务不进行过滤
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String servletPath = request.getServletPath();
		// 登录接口、静态资源不需要进行用户登录验证
		for (String s : IGNORE_URI) {
			if (servletPath.contains(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 执行过滤
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		// 日志信息记录
		BasicDBObject logInformaton = new BasicDBObject();
		logInformaton.put("id", UUID.randomUUID().toString());
		logInformaton.put("url", request.getRequestURI());
		logInformaton.put("ip", request.getRemoteAddr());
		logInformaton.put("port", request.getRemotePort());
		logInformaton.put("starttime", new Date());
		// token和userId同时验证
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			ctx.setResponseBody("token不能为空");
			logInformaton.put("status", -1);
			logInformaton.put("message", "token不能为空");
			logService.saveLogs(logInformaton);
			return null;
		}
		// SSO验证
		boolean ssoResult = ssoUtil.Author(token);
		if (!ssoResult) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			ctx.setResponseBody("SSO验证失败");
			logInformaton.put("status", -1);
			logInformaton.put("message", "SSO验证失败");
			logService.saveLogs(logInformaton);
			return null;
		}
		// 路由转发
		ctx.setSendZuulResponse(true);// 对该请求进行路由
		ctx.setResponseStatusCode(200);
		logInformaton.put("status", 0);
		logInformaton.put("message", "SSO验证成功");
		logService.saveLogs(logInformaton);
		return null;

	}
}
