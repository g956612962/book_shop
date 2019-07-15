/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/10 20:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.OrderDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.Order;

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
public class OrderDaoImpl implements OrderDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public OrderDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }

    @Override
    public int insertOrder(Order order) throws SQLException {           //插入订单
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO book_shop.`order` VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, order.getUserId());
        pstmt.setFloat(2, order.getPrice());
        pstmt.setString(3, order.getCreateTime());
        pstmt.setString(4, order.getEmail());
        pstmt.setString(5, order.getPhone());
        pstmt.setString(6, order.getAddress());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int deleteOrderById(int id) throws SQLException {        //通过ID删除订单
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "DELETE FROM book_shop.`order` where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int count = pstmt.executeUpdate();
        if (count > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateOrderContactInformation(Order order) throws SQLException {         //修改订单联系方式（邮箱，电话，地址）
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE book_shop.`order` SET order_email = ?, order_phone = ?, order_address = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order.getEmail());
        pstmt.setString(2, order.getPhone());
        pstmt.setString(3, order.getAddress());
        pstmt.setInt(4, order.getId());
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateOrderPayStatus(int status, int id) throws SQLException {           //更新订单支付状态，可为1或0
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE book_shop.`order` SET is_pay = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, status);
        pstmt.setInt(2, id);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateOrderDeliverStatus(int status, int id) throws SQLException {       //更新订单发货状态，可为1或0
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE book_shop.`order` SET is_deliver = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, status);
        pstmt.setInt(2, id);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateOrderFinishStatus(int status, int id) throws SQLException {        //更新订单完成状态，可为1或0
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE book_shop.`order` SET is_finish = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, status);
        pstmt.setInt(2, id);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {     //通过ID得到订单
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Order order = new Order();
        String sql = "SELECT * FROM book_shop.`order` where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("order_user_id"));
            order.setPrice(rs.getFloat("order_price"));
            order.setCreateTime(rs.getString("order_create_time"));
            order.setEmail(rs.getString("order_email"));
            order.setPhone(rs.getString("order_phone"));
            order.setAddress(rs.getString("order_address"));
            order.setIsPay(rs.getInt("is_pay"));
            order.setIsDeliver(rs.getInt("is_deliver"));
            order.setIsFinish(rs.getInt("is_finish"));
        }
        pstmt.close();
        return order;
    }

    @Override
    public List<Order> listAllOrder() throws SQLException {         //列出所有订单
        PreparedStatement pstmt = null;
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM book_shop.`order`";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Order order;
        while (rs.next()) {
            order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("order_user_id"));
            order.setPrice(rs.getFloat("order_price"));
            order.setCreateTime(rs.getString("order_create_time"));
            order.setEmail(rs.getString("order_email"));
            order.setPhone(rs.getString("order_phone"));
            order.setAddress(rs.getString("order_address"));
            order.setIsPay(rs.getInt("is_pay"));
            order.setIsDeliver(rs.getInt("is_deliver"));
            order.setIsFinish(rs.getInt("is_finish"));
            orderList.add(order);
        }
        pstmt.close();
        rs.close();
        return orderList;
    }

    @Override
    public List<Order> listOrderByUserId(int id) throws SQLException {      //通过用户ID列出订单
        PreparedStatement pstmt = null;
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM book_shop.`order` WHERE order_user_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        Order order;
        while (rs.next()) {
            order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("order_user_id"));
            order.setPrice(rs.getFloat("order_price"));
            order.setCreateTime(rs.getString("order_create_time"));
            order.setEmail(rs.getString("order_email"));
            order.setPhone(rs.getString("order_phone"));
            order.setAddress(rs.getString("order_address"));
            order.setIsPay(rs.getInt("is_pay"));
            order.setIsDeliver(rs.getInt("is_deliver"));
            order.setIsFinish(rs.getInt("is_finish"));
            orderList.add(order);
        }
        pstmt.close();
        rs.close();
        return orderList;
    }

    @Override
    public List<Order> listOrderByIsPay(int status) throws SQLException {           //通过支付状态列出订单
        PreparedStatement pstmt = null;
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM book_shop.`order` WHERE is_pay = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, status);
        ResultSet rs = pstmt.executeQuery();
        Order order;
        while (rs.next()) {
            order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("order_user_id"));
            order.setPrice(rs.getFloat("order_price"));
            order.setCreateTime(rs.getString("order_create_time"));
            order.setEmail(rs.getString("order_email"));
            order.setPhone(rs.getString("order_phone"));
            order.setAddress(rs.getString("order_address"));
            order.setIsPay(rs.getInt("is_pay"));
            order.setIsDeliver(rs.getInt("is_deliver"));
            order.setIsFinish(rs.getInt("is_finish"));
            orderList.add(order);
        }
        pstmt.close();
        rs.close();
        return orderList;
    }

    @Override
    public List<Order> listOrderByIsDeliver(int status) throws SQLException {       //通过发货状态列出订单
        PreparedStatement pstmt = null;
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM book_shop.`order` WHERE is_deliver = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, status);
        ResultSet rs = pstmt.executeQuery();
        Order order;
        while (rs.next()) {
            order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("order_user_id"));
            order.setPrice(rs.getFloat("order_price"));
            order.setCreateTime(rs.getString("order_create_time"));
            order.setEmail(rs.getString("order_email"));
            order.setPhone(rs.getString("order_phone"));
            order.setAddress(rs.getString("order_address"));
            order.setIsPay(rs.getInt("is_pay"));
            order.setIsDeliver(rs.getInt("is_deliver"));
            order.setIsFinish(rs.getInt("is_finish"));
            orderList.add(order);
        }
        pstmt.close();
        rs.close();
        return orderList;
    }

    @Override
    public List<Order> listOrderByIsFinish(int status) throws SQLException {        //通过完成状态列出订单
        PreparedStatement pstmt = null;
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM book_shop.`order` WHERE is_finish = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, status);
        ResultSet rs = pstmt.executeQuery();
        Order order;
        while (rs.next()) {
            order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("order_user_id"));
            order.setPrice(rs.getFloat("order_price"));
            order.setCreateTime(rs.getString("order_create_time"));
            order.setEmail(rs.getString("order_email"));
            order.setPhone(rs.getString("order_phone"));
            order.setAddress(rs.getString("order_address"));
            order.setIsPay(rs.getInt("is_pay"));
            order.setIsDeliver(rs.getInt("is_deliver"));
            order.setIsFinish(rs.getInt("is_finish"));
            orderList.add(order);
        }
        pstmt.close();
        rs.close();
        return orderList;
    }

}
