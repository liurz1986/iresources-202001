package org.com.liurz.iresources.servcie.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.com.liurz.iresources.core.util.ResponseVO;
import org.com.liurz.iresources.servcie.entity.UserVO;
import org.com.liurz.iresources.servcie.mapper.RoleMapper;
import org.com.liurz.iresources.servcie.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *
 */
@Service
public class RoleService implements IRoleService {
	Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ExtendRoleService extendRoleService;

	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager;

	@Autowired
	TransactionDefinition transactionDefinition;

	// @Autowired
	// private UserVOMapper userVOMapper;

	TransactionStatus transactionStatus = null;

	/**
	 * 主方法不加事务，调用子方法及时有事务也不生效；
	 * 异步调用子方法，子方法不能和主方法为同一个类，不然即使子方法加了事务也不生效，如果不在同一个类上加事务子方法是可以生效的
	 * 
	 */
	public void saveRole(Map<String, Object> role) {
		// roleMapper.deleteAll();
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread start");
				extendRoleService.save(role);
				System.out.print("Thread end");
			}
		});
		service.shutdown();
		System.out.println("dfasfasfdas");

	}

	/**
	 *
	 * @param roles
	 */
	public void batchSaveRole(List<Map<String, Object>> roles) {
		try {
			// 手动开启事务
			transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
			roleMapper.batchSaveRole(roles);
			// 提交事务
			dataSourceTransactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// 手动回滚事务
			dataSourceTransactionManager.rollback(transactionStatus);
		}
	}

	public ResponseVO findAll(int pageNum, int pageSize) {
		ResponseVO responseVO = ResponseVO.create();
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> userList = roleMapper.findAll();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(userList);
		responseVO.setPageInfo(pageInfo);
		responseVO.setItems(userList);
		return responseVO;
	}

	public Map<String, Object> findaById(int id) {

		// throw new RuntimeException("查询异常");
		return roleMapper.findaById(id);
	}

	public List<Map<String, Object>> test() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		ids.add(3);
		ids.add(4);
		List<String> names = new ArrayList<String>();
		names.add("test4");
		names.add("test3");
		names.add("liurz");
		return roleMapper.test(ids, names);
	}

	public UserVO getUser(int id) {

		return null;
	}
	public int copyData(String remark){
		return roleMapper.copyData(remark);
	}
}
