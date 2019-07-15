/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderAdminDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/10 16:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.OrderAdminDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.OrderAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public class OrderAdminDaoImpl implements OrderAdminDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public OrderAdminDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }

    @Override
    public int insertOrderAdmin(OrderAdmin orderAdmin) throws SQLException {        //插入订单管理员信息
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO order_admin VALUES(DEFAULT, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, orderAdmin.getUsername());
        pstmt.setString(2, orderAdmin.getPassword());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int updateOrderAdminPassword(String password, String username) throws SQLException {     //修改订单管理员密码
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE order_admin SET order_admin_password = ? WHERE order_admin_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public OrderAdmin getOrderAdmin(String username, String password) throws SQLException {          //得到订单管理员信息
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderAdmin orderAdmin = new OrderAdmin();
        String sql = "SELECT * FROM order_admin where order_admin_username = ? AND order_admin_password = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            orderAdmin.setId(rs.getInt("id"));
            orderAdmin.setUsername(rs.getString("order_admin_username"));
            orderAdmin.setPassword(rs.getString("order_admin_password"));
        }
        pstmt.close();
        return orderAdmin;
    }
}
