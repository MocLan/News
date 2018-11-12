package com.news.dao.impl;

import com.news.common.util.MySQLUtil;
import com.news.dao.RoleDao;
import com.news.entity.RoleEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {
    private String url;
    private String user;
    private String password;

    public RoleDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Boolean createRole(RoleEntity roleEntity) {
        boolean x = false;
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();
        String sql = "INSERT INTO news.role(rolename) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,roleEntity.getRolename());
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
        return false;
    }
    @Override
    public Boolean deleteRole(long roleId) {
        MySQLUtil mySQLUtil = new MySQLUtil(url,user,password);
        Connection connection = mySQLUtil.getConnection();

        String sql = "delete from role where roleid = ?";
        boolean x = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement .setLong(1,roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean updataRole(long roleId, RoleEntity roleEntity) {
        boolean x = false;
        MySQLUtil mySQLUtil = new MySQLUtil(url, user, password);
        Connection connection = mySQLUtil.getConnection();
        String sql = "update role set rolename = ? where roleid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roleEntity.getRolename());
            preparedStatement.setLong(1,roleId);
            x = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    public static void main(String[] args) {
        RoleDao roleDao = new RoleDaoImpl("jdbc:mysql://localhost:3306/news","root", "1234");
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRolename("lan");
        Boolean x = roleDao.createRole(roleEntity);
        System.out.println(x);
        //============================
//        RoleDao roleDao = new RoleDaoImpl("jdbc:mysql://localhost:3306/news","root", "1234");
//        Boolean x = roleDao.deleteRole(1);
      //=====================
//        RoleDao roleDao = new RoleDaoImpl("jdbc:mysql://localhost:3306/news","root", "1234");
//        Boolean x = roleDao.updataRole(1, roleEntity);

    }
}
