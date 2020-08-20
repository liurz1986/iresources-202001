package org.com.liurz.iresources.servcie.mapper;

import org.com.liurz.iresources.servcie.entity.UserVO;

public interface UserVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVO record);

    int insertSelective(UserVO record);

}