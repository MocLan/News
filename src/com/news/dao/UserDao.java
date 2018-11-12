package com.news.dao;

import com.news.entity.UserEntity;

import java.util.List;

public interface UserDao {
    boolean createUser(UserEntity userEntity);
    boolean updataUser(long userId, UserEntity userEntity);
    boolean deleteUser(long userId);
    List<UserEntity> findUserByRoleId(long roleId);
    List<UserEntity> findAll(int page, int size);
}
//find user by roleid hàm tìm ra tất cả các user có cx role id
// viết update, delete
// tạo tài khoản github