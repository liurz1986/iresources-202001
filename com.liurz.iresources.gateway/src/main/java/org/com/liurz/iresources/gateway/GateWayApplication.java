package org.com.liurz.iresources.gateway;

import org.com.liurz.iresources.gateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 网关
 * 
 * @ClassName: GateWayApplication
 * @Description: TODO
 * @author lwx393577：
 * @date 2019年11月24日 下午12:06:06
 *
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringCloudApplication
public class GateWayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

	// 过滤器配置
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	// 自定义负载均衡策列--全局
	@Bean
	public IRule myRule() {
		/* RandomRule为Ribbon中自带规则实现之一，随机规则 */
		return new RandomRule();
	}

}
