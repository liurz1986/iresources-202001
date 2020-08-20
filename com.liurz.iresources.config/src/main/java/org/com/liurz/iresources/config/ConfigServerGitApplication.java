package org.com.liurz.iresources.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigServer
@SpringBootApplication
@EnableEurekaClient
@RefreshScope // 实现刷新 http://localhost:9090/bus/refresh post的方式请求实现刷新
public class ConfigServerGitApplication {
	// 启动项目后访问：http://localhost:1201/eureka-web/prod/master可查看到远程仓库配置文件信息
	// 参考博客；http://blog.didispace.com/spring-cloud-starter-dalston-3/
	//我们可以通过http://localhost:1201/servicecloud-dev.properties来访问servicecloud-dev.properties文件的内容，
	// 也可以通过http://localhost:1201/servicecloud/dev来访问
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerGitApplication.class, args);
	}
}
