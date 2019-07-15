/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InventoryAdminDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/10 16:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.InventoryAdminDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.InventoryAdmin;

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
public class InventoryAdminDaoImpl implements InventoryAdminDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public InventoryAdminDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }

    @Override
    public int insertInventoryAdmin(InventoryAdmin inventoryAdmin) throws SQLException {        //添加库存管理员
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO inventory_admin VALUES(DEFAULT, ?, ?)";
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, inventoryAdmin.getUsername());
        pstmt.setString(2, inventoryAdmin.getPassword());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int updateInventoryAdminPassword(String password, String username) throws SQLException {     //修改库存管理员密码
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE inventory_admin SET inventory_admin_password = ? WHERE inventory_admin_username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public InventoryAdmin getInventoryAdmin(String username, String password) throws SQLException {      //得到库存管理员信息
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        InventoryAdmin inventoryAdmin = new InventoryAdmin();
        String sql = "SELECT * FROM inventory_admin where inventory_admin_username = ? AND inventory_admin_password = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            inventoryAdmin.setId(rs.getInt("id"));
            inventoryAdmin.setUsername(rs.getString("inventory_admin_username"));
            inventoryAdmin.setPassword(rs.getString("inventory_admin_password"));
        }
        pstmt.close();
        return inventoryAdmin;
    }

}
