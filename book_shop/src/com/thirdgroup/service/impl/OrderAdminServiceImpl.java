/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderAdminServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.OrderAdminDao;
import com.thirdgroup.dao.impl.OrderAdminDaoImpl;
import com.thirdgroup.po.OrderAdmin;
import com.thirdgroup.service.OrderAdminService;

import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/11
 * @since 1.0.0
 */
public class OrderAdminServiceImpl implements OrderAdminService {
    private OrderAdminDao orderAdminDao;

    public OrderAdminServiceImpl() {
        try {
            orderAdminDao = new OrderAdminDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrderAdmin(OrderAdmin orderAdmin) throws SQLException {
        return orderAdminDao.insertOrderAdmin(orderAdmin);
    }

    @Override
    public int updateOrderAdminPassword(String password, String username) throws SQLException {
        return orderAdminDao.updateOrderAdminPassword(password, username);
    }

    @Override
    public OrderAdmin getOrderAdmin(String username, String password) throws SQLException {
        return orderAdminDao.getOrderAdmin(username, password);
    }
}
