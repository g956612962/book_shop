/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InventoryAdminServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.InventoryAdminDao;
import com.thirdgroup.dao.impl.InventoryAdminDaoImpl;
import com.thirdgroup.po.InventoryAdmin;
import com.thirdgroup.service.InventoryAdminService;

import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/11
 * @since 1.0.0
 */
public class InventoryAdminServiceImpl implements InventoryAdminService {
    private InventoryAdminDao inventoryAdminDao;

    public InventoryAdminServiceImpl() {
        try {
            inventoryAdminDao = new InventoryAdminDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertInventoryAdmin(InventoryAdmin inventoryAdmin) throws SQLException {
        return inventoryAdminDao.insertInventoryAdmin(inventoryAdmin);
    }

    @Override
    public int updateInventoryAdminPassword(String password, String username) throws SQLException {
        return inventoryAdminDao.updateInventoryAdminPassword(password, username);
    }

    @Override
    public InventoryAdmin getInventoryAdmin(String username, String password) throws SQLException {
        return inventoryAdminDao.getInventoryAdmin(username, password);
    }
}
