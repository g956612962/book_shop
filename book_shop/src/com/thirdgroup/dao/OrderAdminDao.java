/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderAdminDao
 * Author:   ClarkSong
 * Date:     2019/7/10 16:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.dao;

import com.thirdgroup.po.OrderAdmin;

import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public interface OrderAdminDao {
    int insertOrderAdmin(OrderAdmin orderAdmin) throws SQLException;

    int updateOrderAdminPassword(String password, String username) throws SQLException;

    OrderAdmin getOrderAdmin(String username, String password) throws SQLException;
}
