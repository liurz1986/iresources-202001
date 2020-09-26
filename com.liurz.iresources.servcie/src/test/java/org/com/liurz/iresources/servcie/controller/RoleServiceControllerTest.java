package org.com.liurz.iresources.servcie.controller;

import com.alibaba.fastjson.JSON;
import org.com.liurz.iresources.core.util.ResponseVO;
import org.com.liurz.iresources.servcie.UserApplication;
import org.com.liurz.iresources.servcie.entity.UserVO;
import org.com.liurz.iresources.servcie.service.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.controller
 * @date 2020/9/20 12:17
 * @Copyright © 2020-2028
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= UserApplication.class)
@Transactional // 加上事务后spirngboottest会在执行后数据自动回滚
public class RoleServiceControllerTest {
    @Autowired
    private IRoleService roleService;

    @Test
    public void saveRole() throws InterruptedException {
        Map<String, Object> role = new HashMap<String, Object>();
        roleService.saveRole(role);
    }

    @Test
    public void batchSaveRole() {
        List<Map<String, Object>> roles = new ArrayList<Map<String, Object>>();
        roleService.batchSaveRole(roles);
    }

    @Test
    public void findAll() {
        ResponseVO result = roleService.findAll(1, 10);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void findaById() {
        roleService.findaById(1);
    }

    @Test
    public void test3() {
        roleService.copyData("remark");
    }

    @Test
    public void test4() {

        roleService.test();
    }

    @Test
    public void test5() {
        roleService.getUser(1);
    }
}