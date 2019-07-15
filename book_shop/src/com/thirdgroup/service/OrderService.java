/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderService
 * Author:   ClarkSong
 * Date:     2019/7/11 8:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.service;

import com.thirdgroup.po.Order;

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
public interface OrderService {
    int insertOrder(Order order, int bookId[]) throws SQLException;

    int deleteOrderById(int id) throws SQLException;

    int updateOrderContactInformation(Order order) throws SQLException;

    int updateOrderPayStatus(int status, int id) throws SQLException;

    int updateOrderDeliverStatus(int status, int id) throws SQLException;

    int updateOrderFinishStatus(int status, int id) throws SQLException;

    Order getOrderById(int id) throws SQLException;

    List<Order> listAllOrder() throws SQLException;

    List<Order> listOrderByUserId(int id) throws SQLException;

    List<Order> listOrderByIsPay(int status) throws SQLException;

    List<Order> listOrderByIsDeliver(int status) throws SQLException;

    List<Order> listOrderByIsFinish(int status) throws SQLException;

}
