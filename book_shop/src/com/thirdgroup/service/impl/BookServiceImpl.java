/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.BookDao;
import com.thirdgroup.dao.impl.BookDaoImpl;
import com.thirdgroup.po.Book;
import com.thirdgroup.service.BookService;

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
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl() {
        try {
            bookDao = new BookDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertBook(Book book) throws SQLException {
        return bookDao.insertBook(book);
    }

    @Override
    public int deleteBookById(int id) throws SQLException {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int addBookInventory(int number, int id) throws SQLException {
        return bookDao.addBookInventory(number, id);
    }

    @Override
    public int redBookInventory(int number, int id) throws SQLException {
        return bookDao.redBookInventory(number, id);
    }


    @Override
    public int updateBookInformation(Book book) throws SQLException {
        return bookDao.updateBookInformation(book);
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> listAllBook() throws SQLException {
        return bookDao.listAllBook();
    }

    @Override
    public List<Book> listBookByClassName(String bookClassName) throws SQLException {
        return bookDao.listBookByClassName(bookClassName);
    }
}
