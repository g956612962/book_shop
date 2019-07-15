/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderDetailDao
 * Author:   ClarkSong
 * Date:     2019/7/10 16:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.dao;

import com.thirdgroup.po.OrderDetail;

import java.sql.SQLException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public interface OrderDetailDao {
    int insertOrderDetail(OrderDetail orderDetail) throws SQLException;

    int deleteOrderDetailById(int id) throws SQLException;

    int deleteOrderDetailByOrderId(int id) throws SQLException;

    int updateOrderDetail(OrderDetail orderDetail) throws SQLException;

    OrderDetail getOrderDetailById(int id) throws SQLException;

    List<OrderDetail> listOrderDetailByOrderId(int id) throws SQLException;

    List<OrderDetail> listAllOrderDetail() throws SQLException;


}
