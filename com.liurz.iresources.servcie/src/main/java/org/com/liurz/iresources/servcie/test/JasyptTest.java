package org.com.liurz.iresources.servcie.test;

import org.com.liurz.iresources.servcie.mapper.RoleMapper;
import org.jasypt.encryption.StringEncryptor;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.util
 * @date 2020/8/17 20:13
 * @Copyright © 2020-2028
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JasyptTest {

	@Autowired
	StringEncryptor stringEncryptor;

	@Autowired
	RoleMapper roleMapper;


	public void test() {
		roleMapper.copyData("zhujie");
	}

}
