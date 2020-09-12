package org.com.liurz.iresources.servcie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.liurz.iresources.servcie.annocation.Author;
import org.com.liurz.iresources.servcie.entity.UserVO;
import org.com.liurz.iresources.servcie.service.IRoleService;
import org.com.liurz.iresources.servcie.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/role")
public class RoleServiceController {

	@Autowired
	private IRoleService roleService;

	@Author(value = UserRole.ADMINISTRATOR)
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public Map<String, Object> saveRole(@RequestBody Map<String, Object> role) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			roleService.saveRole(role);
			result.put("data", "");
			result.put("status", "success");
			result.put("id", role.get("currentId")); // 获取保存数据的id,保存sql中定义的返回id的属性名称为currentId
		} catch (Exception e) {
			result.put("data", e.getMessage());
			result.put("status", "error");
		}
		return result;
	}

	@Author(value = UserRole.ADMINISTRATOR)
	@RequestMapping(value = "/saveRoles", method = RequestMethod.POST)
	public Map<String, Object> batchSaveRole(List<Map<String, Object>> roles) {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			roleService.batchSaveRole(roles);
			result.put("data", "");
			result.put("status", "success");
		} catch (Exception e) {
			result.put("data", e.getMessage());
			result.put("status", "error");
		}
		return result;
	}

	// http://localhost:8080/service/rest/v1/role/findAll/1/2
	@Author(value = UserRole.ALLUSER)
	@RequestMapping(value = "/findAll/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public ResponseVO findAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
		try {
			return roleService.findAll(pageNum, pageSize);
		} catch (Exception e) {
			return ResponseVO.errorResult(e.getMessage());
		}
	}

	// @Author(value = UserRole.ALLUSER)
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	public Map<String, Object> findaById(@PathVariable("id") int id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> data = roleService.test();
			result.put("data", data);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("data", e.getMessage());
			result.put("status", "error");
		}
		return result;
	}

	@RequestMapping(value = "/demoMsg", method = RequestMethod.GET)
	public Map<String, Object> Demo() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("data", "demo message");
			result.put("status", "success");
		} catch (Exception e) {
			result.put("data", e.getMessage());
			result.put("status", "error");
		}
		return result;
	}

	@RequestMapping("/test3")
	public int test3(String remark) {

		return roleService.copyData(remark);
	}
}
