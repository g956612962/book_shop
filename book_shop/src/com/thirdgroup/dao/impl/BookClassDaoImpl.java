/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookClassDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/10 18:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.BookClassDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.BookClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public class BookClassDaoImpl implements BookClassDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public BookClassDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }

    @Override
    public int insertBookClass(BookClass bookClass) throws SQLException {           //添加图书类别
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO book_class VALUES(DEFAULT, ?)";
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, bookClass.getClassName());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int deleteBookClassById(int id) throws SQLException {            //删除图书类别
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "DELETE FROM book_class where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int count = pstmt.executeUpdate();
        if (count > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int updateBookClassName(String name, int id) throws SQLException {       //修改图书类别名称
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE book_class SET class_name = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, id);
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public List<BookClass> listAllBookClass() throws SQLException {             //列出所有图书类别
        PreparedStatement pstmt = null;
        List<BookClass> bookClassList = new ArrayList<BookClass>();
        String sql = "SELECT * FROM book_class";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        BookClass bookClass;
        while (rs.next()) {
            bookClass = new BookClass();
            bookClass.setId(rs.getInt("id"));
            bookClass.setClassName(rs.getString("class_name"));
            bookClassList.add(bookClass);
        }
        pstmt.close();
        rs.close();
        return bookClassList;
    }

    @Override
    public BookClass selectBookClassByName(String name) throws SQLException {
        PreparedStatement pstmt = null;
        List<BookClass> bookClassList = new ArrayList<BookClass>();
        String sql = "SELECT * FROM book_class where class_name=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        BookClass bookClass = new BookClass();
        while (rs.next()) {
            bookClass.setId(rs.getInt("id"));
            bookClass.setClassName(rs.getString("class_name"));
        }
        pstmt.close();
        rs.close();


        return bookClass;
    }

    @Override
    public int getBooKCountByCLass(String name) throws SQLException {
        PreparedStatement pstmt = null;
        List<BookClass> bookClassList = new ArrayList<BookClass>();
        String sql = "SELECT count(id) FROM book where book_class_name=?";
        pstmt = conn.prepareStatement(sql);
        System.out.println(name+"name");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        int count =0;
        while (rs.next()) {
                count=rs.getInt(1);
        }
        System.out.println(count+"shul ");
        pstmt.close();
        rs.close();
        return count;
    }


}
