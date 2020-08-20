package org.com.liurz.iresources.servcie.service.impl;

import java.util.Map;

import org.com.liurz.iresources.servcie.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExtendRoleService {
	Logger LOGGER = LoggerFactory.getLogger(ExtendRoleService.class);
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;

	@Autowired
	TransactionDefinition transactionDefinition;

	TransactionStatus transactionStatus = null;

	/**
	 * 验证异步事务
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param role
	 * @return void
	 */
	@Transactional
	public void save(Map<String, Object> role) {
		try {
			roleMapper.deleteAll();
			// int id = 1 / 0;
			roleMapper.saveRole(role);
			return;
		} catch (Exception e) {
			LOGGER.error("roleService", e);
			throw new RuntimeException();
		} finally {
			LOGGER.info("最后执行");
		}

	}
}
