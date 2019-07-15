/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderDetailDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.OrderDetailDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.OrderDetail;

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
 * @create 2019/7/11
 * @since 1.0.0
 */
public class OrderDetailDaoImpl implements OrderDetailDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public OrderDetailDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) throws SQLException {         //插入订单细节
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO order_detail VALUES(DEFAULT, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, orderDetail.getOrderId());
        pstmt.setInt(2, orderDetail.getBookId());
        pstmt.setFloat(3, orderDetail.getBookPrice());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int deleteOrderDetailById(int id) throws SQLException {          //通过ID删除订单细节
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "DELETE FROM order_detail where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int count = pstmt.executeUpdate();
        if (count > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int deleteOrderDetailByOrderId(int id) throws SQLException {         //通过订单ID删除订单细节
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "DELETE FROM order_detail where order_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int count = pstmt.executeUpdate();
        if (count > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) throws SQLException {     //更新订单细节
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE order_detail SET order_id = ?, book_id = ?, book_price = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, orderDetail.getOrderId());
        pstmt.setInt(2, orderDetail.getBookId());
        pstmt.setFloat(2, orderDetail.getBookPrice());
        pstmt.setInt(2, orderDetail.getId());
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws SQLException {     //通过ID得到订单细节
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderDetail orderDetail = new OrderDetail();
        String sql = "SELECT * FROM order_detail where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            orderDetail.setId(rs.getInt("id"));
            orderDetail.setOrderId(rs.getInt("order_id"));
            orderDetail.setBookId(rs.getInt("book_id"));
            orderDetail.setBookPrice(rs.getFloat("book_price"));
        }
        pstmt.close();
        return orderDetail;
    }

    @Override
    public List<OrderDetail> listOrderDetailByOrderId(int id) throws SQLException {     //通过订单ID得到订单细节
        PreparedStatement pstmt = null;
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        String sql = "SELECT * FROM order_detail WHERE order_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        OrderDetail orderDetail;
        while (rs.next()) {
            orderDetail = new OrderDetail();
            orderDetail.setId(rs.getInt("id"));
            orderDetail.setOrderId(rs.getInt("order_id"));
            orderDetail.setBookId(rs.getInt("book_id"));
            orderDetail.setBookPrice(rs.getFloat("book_price"));
            orderDetailList.add(orderDetail);
        }
        pstmt.close();
        rs.close();
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> listAllOrderDetail() throws SQLException {         //列出所有订单细节
        PreparedStatement pstmt = null;
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        String sql = "SELECT * FROM order_detail";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        OrderDetail orderDetail;
        while (rs.next()) {
            orderDetail = new OrderDetail();
            orderDetail.setId(rs.getInt("id"));
            orderDetail.setOrderId(rs.getInt("order_id"));
            orderDetail.setBookId(rs.getInt("book_id"));
            orderDetail.setBookPrice(rs.getFloat("book_price"));
            orderDetailList.add(orderDetail);
        }
        pstmt.close();
        rs.close();
        return orderDetailList;
    }
}
