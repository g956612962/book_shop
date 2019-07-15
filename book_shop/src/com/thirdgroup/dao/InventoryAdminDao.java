/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InventoryAdminDao
 * Author:   ClarkSong
 * Date:     2019/7/10 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.dao;

import com.thirdgroup.po.InventoryAdmin;

import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public interface InventoryAdminDao {
    int insertInventoryAdmin(InventoryAdmin inventoryAdmin) throws SQLException;

    int updateInventoryAdminPassword(String password, String username) throws SQLException;

    InventoryAdmin getInventoryAdmin(String username, String password) throws SQLException;
}
