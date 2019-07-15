/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/10 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.UserDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public class UserDaoImpl implements UserDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public UserDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }


    @Override
    public int insertUser(User user) throws SQLException {          //插入用户
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO user VALUES(DEFAULT, ?, ?, DEFAULT, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPhone());
        pstmt.setString(5, user.getAddress());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int deleteUserByUsername(String username) throws SQLException {      //通过用户名删除用户
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "DELETE FROM user where user_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        int count = pstmt.executeUpdate();
        if (count > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateUserBalance(float price, String username) throws SQLException {      //更新用户余额
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE user SET user_balance = user_balance - ? WHERE user_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setFloat(1, price);
        pstmt.setString(2, username);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateUserPassword(String password, String username) throws SQLException {       //更新用户密码
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE user SET user_password = ? WHERE user_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateUserContactInformation(User user) throws SQLException {            //更新个人联系方式
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE user SET user_email = ?, user_phone = ?, user_address = ? WHERE user_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPhone());
        pstmt.setString(3, user.getAddress());
        pstmt.setString(4, user.getUsername());
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {            //通过用户名密码得到用户
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();
        String sql = "SELECT * FROM user where user_username = ? and user_password = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("user_username"));
            user.setPassword(rs.getString("user_password"));
            user.setBalance(rs.getInt("user_balance"));
            user.setEmail(rs.getString("user_email"));
            user.setPhone(rs.getString("user_phone"));
            user.setAddress(rs.getString("user_address"));
        }
        pstmt.close();
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {            //通过用户名得到用户
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();
        String sql = "SELECT * FROM user where user_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("user_username"));
            user.setPassword(rs.getString("user_password"));
            user.setBalance(rs.getInt("user_balance"));
            user.setEmail(rs.getString("user_email"));
            user.setPhone(rs.getString("user_phone"));
            user.setAddress(rs.getString("user_address"));
        }
        pstmt.close();
        return user;
    }

    @Override
    public List<User> listAllUser() throws SQLException {       //列出所有用户
        PreparedStatement pstmt = null;
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM student";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        User user;
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("user_username"));
            user.setPassword(rs.getString("user_password"));
            user.setBalance(rs.getInt("user_balance"));
            user.setEmail(rs.getString("user_email"));
            user.setPhone(rs.getString("user_phone"));
            user.setAddress(rs.getString("user_address"));
            userList.add(user);
        }
        pstmt.close();
        rs.close();
        return userList;
    }
}
