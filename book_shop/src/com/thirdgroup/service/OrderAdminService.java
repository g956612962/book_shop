/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderAdminService
 * Author:   ClarkSong
 * Date:     2019/7/11 8:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.service;

import com.thirdgroup.po.OrderAdmin;

import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/11
 * @since 1.0.0
 */
public interface OrderAdminService {
    int insertOrderAdmin(OrderAdmin orderAdmin) throws SQLException;

    int updateOrderAdminPassword(String password, String username) throws SQLException;

    OrderAdmin getOrderAdmin(String username, String password) throws SQLException;

}
