/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.UserDao;
import com.thirdgroup.dao.impl.UserDaoImpl;
import com.thirdgroup.po.User;
import com.thirdgroup.service.UserService;

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
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        try {
            userDao = new UserDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertUser(User user) throws SQLException {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUserByUsername(String username) throws SQLException {
        return userDao.deleteUserByUsername(username);
    }

    @Override
    public int updateUserBalance(float price, String username) throws SQLException {
        return userDao.updateUserBalance(price, username);
    }

    @Override
    public int updateUserPassword(String password, String username) throws SQLException {
        return userDao.updateUserPassword(password, username);
    }

    @Override
    public int updateUserContactInformation(User user) throws SQLException {
        return userDao.updateUserContactInformation(user);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<User> listAllUser() throws SQLException {
        return userDao.listAllUser();
    }
}
