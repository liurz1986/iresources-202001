package org.com.liurz.iresources.servcie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
	/**
	 * 保存
	 * 
	 * @param role
	 */
	public void saveRole(Map<String, Object> role);

	/**
	 * 批量保存
	 * 
	 * @param roles
	 */
	public void batchSaveRole(List<Map<String, Object>> roles);

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findAll();

	/**
	 * 通过id获取角色对象
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> findaById(int id);

	/**
	 * 通过用户名获取用户角色
	 */
	public List<String> getRoleName(String userName);

	public void deleteAll();

	public List<Map<String, Object>> test(@Param("ids") List<Integer> ids, @Param("names") List<String> names);

}
