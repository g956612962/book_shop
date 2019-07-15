/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookClassDao
 * Author:   ClarkSong
 * Date:     2019/7/10 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.dao;

import com.thirdgroup.po.BookClass;

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
public interface BookClassDao {
    int insertBookClass(BookClass bookClass) throws SQLException;

    int deleteBookClassById(int id) throws SQLException;

    int updateBookClassName(String name, int id) throws SQLException;

    List<BookClass> listAllBookClass() throws SQLException;

    BookClass selectBookClassByName(String name) throws SQLException;
    int getBooKCountByCLass(String name)throws  SQLException;
}
