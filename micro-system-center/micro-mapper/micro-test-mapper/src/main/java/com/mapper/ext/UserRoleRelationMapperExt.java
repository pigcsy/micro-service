package com.mapper.ext;


import com.entity.UserRoleRelation;
import com.mapper.generate.UserRoleRelationMapper;

import java.util.List;

public interface UserRoleRelationMapperExt extends UserRoleRelationMapper {


    List<UserRoleRelation> queryRoleByUserId(Integer userId);
}