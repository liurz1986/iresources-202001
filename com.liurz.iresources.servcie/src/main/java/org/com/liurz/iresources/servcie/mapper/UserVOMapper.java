package org.com.liurz.iresources.servcie.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.com.liurz.iresources.servcie.entity.UserVO;


public interface UserVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserVO record);

}