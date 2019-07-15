/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookDao
 * Author:   ClarkSong
 * Date:     2019/7/10 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.dao;

import com.thirdgroup.po.Book;

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
public interface BookDao {
    int insertBook(Book book) throws SQLException;

    int deleteBookById(int id) throws SQLException;

    int addBookInventory(int number, int id) throws SQLException;//增加图书库存

    int redBookInventory(int number, int id) throws SQLException;//减少图书库存

    int updateBookInformation(Book book) throws SQLException;

    Book getBookById(int id) throws SQLException;

    List<Book> listAllBook() throws SQLException;

    List<Book> listBookByClassName(String bookClassName) throws SQLException;


}
