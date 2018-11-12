package com.news.Service.Impl;

import com.news.Service.IUserService;
import com.news.dao.UserDao;
import com.news.dao.impl.UserDaoImpl;
import com.news.entity.UserEntity;

public class UserService implements IUserService {
    private String url;
    private String user;
    private String password;


    public UserService(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    private UserDao userDao = UserDaoImpl.getUserDao(url, user, password);
    @Override
    public boolean createUser(UserEntity userEntity) {

        return userDao.createUser(userEntity);
    }

    @Override
    public UserEntity findUserById(Integer userId) {

        return null;
    }
}
