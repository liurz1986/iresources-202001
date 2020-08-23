package org.com.liurz.iresources.servcie.service.common;

import org.com.liurz.iresources.servcie.mapper.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 获取表的序列：主要是针对通过序列生成主键，不用id自增生成主键的情况
 *
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.service.common
 * @date 2020/8/20 22:58
 * @Copyright © 2020-2028
 */
@Service
public class SequenceService {

	@Autowired
	private SequenceMapper sequenceMapper;

	/**
	 * 针对单条记录获取序列
	 *
	 * @param tableName
	 *            表名
	 * @return
	 */
	public long getNextSquenceId(String tableName) {
		return sequenceMapper.getNextSquenceId(tableName);
	}

	/**
	 * 针对多条记录获取序列：主要在序列表中增加记录数的增加序列数
	 *
	 * @param tableName
	 *            表名
	 * @param size
	 *            记录总条数
	 * @return
	 */
	public long getNextSquenceId(String tableName, int size) {
		// 获取当前设置的第一个id
		long sequenId = sequenceMapper.getNextSquenceId(tableName);
		// 设置表的序列，针对需要多个序列的情况
		sequenceMapper.setTableSquenceId(tableName, size);
		return sequenId;
	}
}
