/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserService
 * Author:   ClarkSong
 * Date:     2019/7/11 8:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.service;

import com.thirdgroup.po.User;

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
public interface UserService {
    int insertUser(User user) throws SQLException;

    int deleteUserByUsername(String username) throws SQLException;

    int updateUserBalance(float price, String username) throws SQLException;

    int updateUserPassword(String password, String username) throws SQLException;

    int updateUserContactInformation(User user) throws SQLException;

    User getUserByUsernameAndPassword(String username, String password) throws SQLException;

    User getUserByUsername(String username) throws SQLException;

    List<User> listAllUser() throws SQLException;
}
