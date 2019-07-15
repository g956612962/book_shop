/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookClassServiceImpl
 * Author:   ClarkSong
 * Date:     2019/7/11 8:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.service.impl;

import com.thirdgroup.dao.BookClassDao;
import com.thirdgroup.dao.impl.BookClassDaoImpl;
import com.thirdgroup.po.Book;
import com.thirdgroup.po.BookClass;
import com.thirdgroup.service.BookClassService;

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
public class BookClassServiceImpl implements BookClassService {
    private BookClassDao bookClassDao;

    public BookClassServiceImpl() {
        try {
            bookClassDao = new BookClassDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int insertBookClass(BookClass bookClass) throws SQLException {
        return bookClassDao.insertBookClass(bookClass);
    }

    @Override
    public int deleteBookClassById(int id) throws SQLException {
        return bookClassDao.deleteBookClassById(id);
    }

    @Override
    public int updateBookClassName(String name, int id) throws SQLException {
        return bookClassDao.updateBookClassName(name, id);
    }

    @Override
    public List<BookClass> listAllBookClass() throws SQLException {
        return bookClassDao.listAllBookClass();
    }

    @Override
    public BookClass selectBookClassByName(String name) throws SQLException {
        return bookClassDao.selectBookClassByName(name);
    }

    @Override
    public boolean bookclassIfexit(String name) throws SQLException {
        BookClass bookClass = bookClassDao.selectBookClassByName(name);
        boolean st =false;
        if (bookClass.getClassName()!=null)
            st =true;
        return st;
    }

    @Override
    public int getBooKCountByCLass(String name) throws SQLException {
        return bookClassDao.getBooKCountByCLass(name);
    }
}
