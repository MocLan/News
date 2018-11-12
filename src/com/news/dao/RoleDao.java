package com.news.dao;

import com.news.entity.RoleEntity;

public interface RoleDao {
    Boolean createRole(RoleEntity roleEntity);
    Boolean updataRole(long roleId, RoleEntity roleEntity);
    Boolean deleteRole(long roleId);
}
