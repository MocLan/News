package com.news.dao.impl;

import com.news.common.util.MySQLUtil;
import com.news.dao.UserDao;
import com.news.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private String url;
    private String user;
    private String password;
    private static UserDao userDao = null;

    public static UserDao getUserDao(String url, String user, String password){
        if (userDao == null)
            userDao = new UserDaoImpl(url, user, password);
        return userDao;
    }

    private UserDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public boolean createUser(UserEntity userEntity) {
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
            x = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return x;
    }

    @Override
    public boolean updataUser(long userId, UserEntity userEntity) {
        boolean x = false;
        MySQLUtil mySQLUtil = new MySQLUtil(url, user, password);
        Connection connection = mySQLUtil.getConnection();
        String sql = "UPDATE news.user\n" +
                "SET\n" +
                "  username = ?,\n" +
                "  password = ?,\n" +
                "  firstname = ?,\n" +
                "  lastname = ?,\n" +
                "  createddate = ?,\n" +
                "  email = ?,\n" +
                "  sex = ?,\n" +
                "  roleid = ?\n" +
                "WHERE userid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userEntity.getUsername());
            preparedStatement.setString(2,userEntity.getPassword());
            preparedStatement.setString(3,userEntity.getFirstname());
            preparedStatement.setString(4,userEntity.getLastname());
            preparedStatement.setTimestamp(5,userEntity.getCreateddate());
            preparedStatement.setString(6,userEntity.getEmail());
            preparedStatement.setBoolean(7,userEntity.isSex());
            preparedStatement.setLong(8,userEntity.getRoleid());
            preparedStatement.setLong(9,userId);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return x;

    }


    @Override
    public boolean deleteUser( long userId) {
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();
        String sql = "delete from user where userid = ?";
        boolean x = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,userId);
            x = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return x;
    }


    @Override
    public List<UserEntity> findUserByRoleId(long roleId) {
        String sql = "select * from user where roleid = ?";
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserEntity userEntity = new UserEntity();
                userEntity.setUserid(resultSet.getLong(1));
                userEntity.setUsername(resultSet.getString(2));
                userEntity.setPassword(resultSet.getString(3));
                userEntity.setFirstname(resultSet.getString(4));
                userEntity.setLastname(resultSet.getString(5));
                userEntity.setCreateddate(resultSet.getTimestamp(6));
                userEntity.setEmail(resultSet.getString(7));
                userEntity.setSex(resultSet.getBoolean(8));
                userEntity.setRoleid(resultSet.getLong(9));
                userEntities.add(userEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userEntities;
    }

    @Override
    public List<UserEntity> findAll(int page, int size) {
        String sql = "select * from user limit ? offset ? ";
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();
        List<UserEntity> userEntities = new ArrayList<>();
        int from = (page -1)*size ;
        int to = size;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,to);
            preparedStatement.setInt(2,from);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserEntity userEntity = new UserEntity();
                userEntity.setUserid(resultSet.getLong(1));
                userEntity.setUsername(resultSet.getString(2));
                userEntity.setPassword(resultSet.getString(3));
                userEntity.setFirstname(resultSet.getString(4));
                userEntity.setLastname(resultSet.getString(5));
                userEntity.setCreateddate(resultSet.getTimestamp(6));
                userEntity.setEmail(resultSet.getString(7));
                userEntity.setSex(resultSet.getBoolean(8));
                userEntity.setRoleid(resultSet.getLong(9));
                userEntities.add(userEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userEntities;
    }

    public static void main(String[] args) {
//        UserDao userDao = new UserDaoImpl("jdbc:mysql://localhost:3306/news","root","1234");
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("lan");
//        userEntity.setPassword("1234");
//        userEntity.setLastname("nguyen");
//        userEntity.setFirstname("lan");
//        userEntity.setCreateddate(new Timestamp(System.currentTimeMillis()));
//        userEntity.setEmail("lan.gmail");
//        userEntity.setSex(false);
//        userEntity.setRoleid(32);
//        boolean x = userDao.createUser(userEntity);
//        System.out.println(x);
// --------------------------------------------------------------------
//        UserDao userDao = new UserDaoImpl("jdbc:mysql://localhost:3306/news","root","1234");
//        UserEntity userEntity1 = new UserEntity();
//        userEntity1.setUsername("lan");
//        userEntity1.setFirstname("lan");
//        userEntity1.setLastname("nguyen");
//        userEntity1.setEmail("ttt");
//        userEntity1.setRoleid(3);
//        userEntity1.setCreateddate(new Timestamp(System.currentTimeMillis()));
//        userEntity1.setPassword("1234");
//        userEntity1.setRoleid(1);
//        List<UserEntity> userEntities = userDao.findUserByRoleId(32);
//        userEntities.stream().forEach(System.out::println);
//        ----------------------------------------------------------------------
//        UserDao userDao = new UserDaoImpl("jdbc:mysql://localhost:3306/news","root","1234");
//        boolean x = userDao.deleteUser(1);
//        ----------------------------------------------------------------------------
//        UserDao userDao = new UserDaoImpl("jdbc:mysql://localhost:3306/news","root","1234");
//        List<UserEntity> userEntities = userDao.all(1,3);
//        userEntities.stream().forEach(System.out::println);


    }

}






