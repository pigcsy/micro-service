package com.mapper.ext;


import com.entity.UserBase;
import com.mapper.generate.UserBaseMapper;

public interface UserBaseMapperExt extends UserBaseMapper {

    UserBase selectByName(String code);
}