package org.com.liurz.iresources.servcie.test;

import org.com.liurz.iresources.servcie.service.common.SequenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.util
 * @date 2020/8/17 20:13
 * @Copyright © 2020-2028
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceTest {

	@Autowired
	SequenceService sequenceService;

	@Test
	public void test() {
		System.out.println(sequenceService.getNextSquenceId("testSeq"));
	}

}
