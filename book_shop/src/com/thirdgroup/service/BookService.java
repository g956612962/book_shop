/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookService
 * Author:   ClarkSong
 * Date:     2019/7/11 8:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong        修改时间           版本号              描述
 */
package com.thirdgroup.service;

import com.thirdgroup.po.Book;

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
public interface BookService {
    int insertBook(Book book) throws SQLException;

    int deleteBookById(int id) throws SQLException;

    int addBookInventory(int number, int id) throws SQLException;

    int redBookInventory(int number, int id) throws SQLException;

    int updateBookInformation(Book book) throws SQLException;

    Book getBookById(int id) throws SQLException;

    List<Book> listAllBook() throws SQLException;

    List<Book> listBookByClassName(String bookClassName) throws SQLException;
}
