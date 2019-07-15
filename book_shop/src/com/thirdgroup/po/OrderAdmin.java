/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderAdmin
 * Author:   ClarkSong
 * Date:     2019/7/10 16:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.po;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public class OrderAdmin {
    private int id;
    private String username;
    private String password;

    public OrderAdmin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public OrderAdmin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
