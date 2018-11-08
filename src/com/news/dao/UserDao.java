package com.news.dao;

import com.news.entity.UserEntity;

public interface UserDao {
    UserEntity createUser(UserEntity userEntity);
    boolean updataUser(UserEntity userEntity, long userid);
    boolean deleteUser(UserEntity userEntity, long userid );
    boolean findUserByRoleId(UserEntity userEntity, long userid);

}
//find user by roleid hàm tìm ra tất cả các user có cx role id
// viết update, delete
// tạo tài khoản github