package com.news.Service;

import com.news.entity.UserEntity;

public interface IUserService {
    boolean createUser(UserEntity userEntity);
    UserEntity findUserById(Integer userId);
}
