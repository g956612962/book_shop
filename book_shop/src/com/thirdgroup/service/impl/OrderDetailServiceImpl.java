/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderDetailServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.OrderDetailDao;
import com.thirdgroup.dao.impl.OrderDetailDaoImpl;
import com.thirdgroup.po.OrderDetail;
import com.thirdgroup.service.OrderDetailService;

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
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailDao orderDetailDao;

    public OrderDetailServiceImpl() {
        try {
            orderDetailDao = new OrderDetailDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        return orderDetailDao.insertOrderDetail(orderDetail);
    }

    @Override
    public int deleteOrderDetailById(int id) throws SQLException {
        return orderDetailDao.deleteOrderDetailById(id);
    }

    @Override
    public int deleteOrderDetailByOrderId(int id) throws SQLException {
        return orderDetailDao.deleteOrderDetailByOrderId(id);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        return orderDetailDao.updateOrderDetail(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws SQLException {
        return orderDetailDao.getOrderDetailById(id);
    }

    @Override
    public List<OrderDetail> listOrderDetailByOrderId(int id) throws SQLException {
        return orderDetailDao.listOrderDetailByOrderId(id);
    }

    @Override
    public List<OrderDetail> listAllOrderDetail() throws SQLException {
        return orderDetailDao.listAllOrderDetail();
    }
}
