package org.com.liurz.iresources.core.lock;

import javax.annotation.PreDestroy;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

//@Configuration
@ConfigurationProperties(prefix = "curator")
public class ZookeeperConfig {
	private final Logger LOGGER = LoggerFactory.getLogger(ZookeeperConfig.class);

	private int retryCount;

	private int elapsedTimeMs;

	private String connectString;

	private int sessionTimeoutMs;

	private int connectionTimeoutMs;

	// 连接zookeeper
	@Bean
	public CuratorFramework curatorFramework() {
		return CuratorFrameworkFactory.newClient(getConnectString(), getSessionTimeoutMs(), getConnectionTimeoutMs(),
				new RetryNTimes(getRetryCount(), getElapsedTimeMs()));
	}

	@PreDestroy
	private void destroyClient() {
		curatorFramework().close();
		LOGGER.info("==================关闭成功==================");
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getElapsedTimeMs() {
		return elapsedTimeMs;
	}

	public void setElapsedTimeMs(int elapsedTimeMs) {
		this.elapsedTimeMs = elapsedTimeMs;
	}

	public String getConnectString() {
		return connectString;
	}

	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

}
