package org.com.liurz.iresources.servcie.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.mapper
 * @date 2020/8/20 23:03
 * @Copyright © 2020-2028
 */
public interface SequenceMapper {

	long getNextSquenceId(@Param("tableName") String tableName);

	long setTableSquenceId(@Param("tableName") String tableName, @Param("size") int size);
}
