/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestUser
 * Author:   ClarkSong
 * Date:     2019/7/15 11:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.test;

import com.thirdgroup.service.UserService;
import com.thirdgroup.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/15
 * @since 1.0.0
 */

public class TestUser {

    @Test
    public void testUpdateBalance() {
        UserService userService = new UserServiceImpl();
        try {
            userService.updateUserBalance(-100.0f, "songvc15");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
