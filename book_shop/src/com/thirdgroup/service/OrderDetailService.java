/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderDetailService
 * Author:   ClarkSong
 * Date:     2019/7/11 8:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.service;

import com.thirdgroup.po.OrderDetail;

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
public interface OrderDetailService {
    int insertOrderDetail(OrderDetail orderDetail) throws SQLException;

    int deleteOrderDetailById(int id) throws SQLException;

    int deleteOrderDetailByOrderId(int id) throws SQLException;

    int updateOrderDetail(OrderDetail orderDetail) throws SQLException;

    OrderDetail getOrderDetailById(int id) throws SQLException;

    List<OrderDetail> listOrderDetailByOrderId(int id) throws SQLException;

    List<OrderDetail> listAllOrderDetail() throws SQLException;

}
