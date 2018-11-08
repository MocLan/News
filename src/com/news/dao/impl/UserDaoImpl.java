package com.news.dao.impl;

import com.news.common.util.MySQLUtil;
import com.news.dao.UserDao;
import com.news.entity.UserEntity;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private String url;
    private String user;
    private String password;
    public UserDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
       boolean x = false;
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();
        String sql = "INSERT INTO news.user( username, password, firstname, lastname, createddate, email, sex, roleid) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userEntity.getUsername());
            preparedStatement.setString(2,userEntity.getPassword());
            preparedStatement.setString(3,userEntity.getFirstname());
            preparedStatement.setString(4, userEntity.getLastname());
            preparedStatement.setTimestamp(5,userEntity.getCreateddate());
            preparedStatement.setString(6,userEntity.getEmail());
            preparedStatement.setBoolean(7,userEntity.isSex());
            preparedStatement.setLong(8,userEntity.getRoleid());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userEntity.setUserid(resultSet.getLong(1));
                userEntity.setUsername(resultSet.getString(2));
                userEntity.setPassword(resultSet.getString(3));
                userEntity.setFirstname(resultSet.getString(4));
                userEntity.setLastname(resultSet.getString(5));
                userEntity.setCreateddate(resultSet.getTimestamp(6));
                userEntity.setEmail(resultSet.getString(7));
                userEntity.setSex(resultSet.getBoolean(8));
                userEntity.setRoleid(resultSet.getLong(9));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    @Override
    public boolean updataUser(UserEntity userEntity, long userid) {
        boolean x = false;
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();
        //String sql =

        return false;
    }

    @Override
    public boolean deleteUser(UserEntity userEntity, long userid) {
        return false;
    }

    @Override
    public boolean findUserByRoleId(UserEntity userEntity, long userid) {
        return false;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl("jdbc:mysql://localhost:3306/news","root","1234");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("lan");
        userEntity.setPassword("1234");
        userEntity.setLastname("nguyen");
        userEntity.setFirstname("lan");
        userEntity.setCreateddate(new Timestamp(System.currentTimeMillis()));
        userEntity.setEmail("lan.gmail");
        userEntity.setRoleid(32);
        boolean x = userDao.createUser(userEntity);

        System.out.println(x);
    }

}






