/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.BookDao;
import com.thirdgroup.dao.OrderDao;
import com.thirdgroup.dao.OrderDetailDao;
import com.thirdgroup.dao.impl.BookDaoImpl;
import com.thirdgroup.dao.impl.OrderDaoImpl;
import com.thirdgroup.dao.impl.OrderDetailDaoImpl;
import com.thirdgroup.po.Book;
import com.thirdgroup.po.Order;
import com.thirdgroup.po.OrderDetail;
import com.thirdgroup.service.OrderService;

import java.sql.SQLException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/11
 * @since 1.0.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;
    private BookDao bookDao;

    public OrderServiceImpl() {
        try {
            orderDao = new OrderDaoImpl();
            orderDetailDao = new OrderDetailDaoImpl();
            bookDao = new BookDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrder(Order order, int[] bookId) throws SQLException {
        OrderDetail orderDetail;
        Book book;
        orderDao.insertOrder(order);
        for (int i = 0; i < bookId.length; i++) {
            orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setBookId(bookId[i]);
            book = bookDao.getBookById(bookId[i]);
            orderDetail.setBookPrice(book.getPrice());
            //order.setPrice(order.getPrice() + orderDetail.getBookPrice());
            orderDetailDao.insertOrderDetail(orderDetail);
        }
        return 1;
    }

    @Override
    public int deleteOrderById(int id) throws SQLException {
        orderDetailDao.deleteOrderDetailByOrderId(id);
        return orderDao.deleteOrderById(id);
    }

    @Override
    public int updateOrderContactInformation(Order order) throws SQLException {
        return orderDao.updateOrderContactInformation(order);
    }

    @Override
    public int updateOrderPayStatus(int status, int id) throws SQLException {
        return orderDao.updateOrderPayStatus(status, id);
    }

    @Override
    public int updateOrderDeliverStatus(int status, int id) throws SQLException {
        return orderDao.updateOrderDeliverStatus(status, id);
    }

    @Override
    public int updateOrderFinishStatus(int status, int id) throws SQLException {
        return orderDao.updateOrderFinishStatus(status, id);
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> listAllOrder() throws SQLException {
        return orderDao.listAllOrder();
    }

    @Override
    public List<Order> listOrderByUserId(int id) throws SQLException {
        return orderDao.listOrderByUserId(id);
    }

    @Override
    public List<Order> listOrderByIsPay(int status) throws SQLException {
        return orderDao.listOrderByIsPay(status);
    }

    @Override
    public List<Order> listOrderByIsDeliver(int status) throws SQLException {
        return orderDao.listOrderByIsDeliver(status);
    }

    @Override
    public List<Order> listOrderByIsFinish(int status) throws SQLException {
        return orderDao.listOrderByIsFinish(status);
    }
}
