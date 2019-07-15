/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BookDaoImpl
 * Author:   ClarkSong
 * Date:     2019/7/10 20:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.dao.impl;

import com.thirdgroup.dao.BookDao;
import com.thirdgroup.dbc.DatabaseConnection;
import com.thirdgroup.po.Book;

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
public class BookDaoImpl implements BookDao {
    private Connection conn = null;
    private DatabaseConnection dbc = null;

    public BookDaoImpl() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection();
        conn = dbc.getConnection();
    }

    @Override
    public int insertBook(Book book) throws SQLException {              //添加图书
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "INSERT INTO book VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getName());
        pstmt.setString(2, book.getExpress());
        pstmt.setFloat(3, book.getPrice());
        pstmt.setInt(4, book.getClassId());
        pstmt.setString(5, book.getClassName());
        pstmt.setString(6, book.getAuthor());
        pstmt.setString(7, book.getPress());
        pstmt.setInt(8, book.getInventory());
        pstmt.setString(9, book.getImagePath());
        int count = pstmt.executeUpdate();
        if (count > 0) {
            flag = 1;
        }
        pstmt.close();
        return flag;
    }

    @Override
    public int deleteBookById(int id) throws SQLException {             //通过ID删除图书
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "DELETE FROM book where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int count = pstmt.executeUpdate();
        if (count > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public int addBookInventory(int number, int id) throws SQLException {        //增加图书库存
        PreparedStatement pstmt = null;
        String sql = "UPDATE book SET book_inventory = book_inventory + ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, number);
        pstmt.setInt(2, id);
        int flag = pstmt.executeUpdate();
        pstmt.close();
        return flag;
    }

    @Override
    public int redBookInventory(int number, int id) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "UPDATE book SET book_inventory = book_inventory - ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, number);
        pstmt.setInt(2, id);
        int flag = pstmt.executeUpdate();
        pstmt.close();
        return flag;
    }

    @Override
    public int updateBookInformation(Book book) throws SQLException {           //更新图书信息
        PreparedStatement pstmt = null;
        int flag = 0;
        String sql = "UPDATE book SET book_name = ?, book_express = ?, book_price = ?, book_class_id = ?, book_class_name = ?, book_author = ?, book_press = ?, book_inventory = ?, book_image_path = ? WHERE id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getName());
        pstmt.setString(2, book.getExpress());
        pstmt.setFloat(3, book.getPrice());
        pstmt.setInt(4, book.getClassId());
        pstmt.setString(5, book.getClassName());
        pstmt.setString(6, book.getAuthor());
        pstmt.setString(7, book.getPress());
        pstmt.setInt(8, book.getInventory());
        pstmt.setString(9, book.getImagePath());
        pstmt.setInt(10, book.getId());
        if (pstmt.executeUpdate() > 0)
            flag = 1;
        pstmt.close();
        return flag;
    }

    @Override
    public Book getBookById(int id) throws SQLException {               //通过ID得到图书
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book = new Book();
        String sql = "SELECT * FROM book where id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("book_name"));
            book.setExpress(rs.getString("book_express"));
            book.setPrice(rs.getFloat("book_price"));
            book.setClassId(rs.getInt("book_class_id"));
            book.setClassName(rs.getString("book_class_name"));
            book.setAuthor(rs.getString("book_author"));
            book.setPress(rs.getString("book_press"));
            book.setInventory(rs.getInt("book_inventory"));
            book.setImagePath(rs.getString("book_image_path"));
        }
        pstmt.close();
        return book;
    }

    @Override
    public List<Book> listAllBook() throws SQLException {               //列出所有图书
        PreparedStatement pstmt = null;
        List<Book> bookList = new ArrayList<Book>();
        String sql = "SELECT * FROM book";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Book book;
        while (rs.next()) {
            book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("book_name"));
            book.setExpress(rs.getString("book_express"));
            book.setPrice(rs.getFloat("book_price"));
            book.setClassId(rs.getInt("book_class_id"));
            book.setClassName(rs.getString("book_class_name"));
            book.setAuthor(rs.getString("book_author"));
            book.setPress(rs.getString("book_press"));
            book.setInventory(rs.getInt("book_inventory"));
            book.setImagePath(rs.getString("book_image_path"));
            bookList.add(book);
        }
        pstmt.close();
        rs.close();
        return bookList;
    }

    @Override
    public List<Book> listBookByClassName(String bookClassName) throws SQLException {           //通过类别名字列出图书
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book = new Book();
        List<Book> bookList = new ArrayList<Book>();
        String sql = "SELECT * FROM book where book_class_name = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookClassName);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("book_name"));
            book.setExpress(rs.getString("book_express"));
            book.setPrice(rs.getFloat("book_price"));
            book.setClassId(rs.getInt("book_class_id"));
            book.setClassName(rs.getString("book_class_name"));
            book.setAuthor(rs.getString("book_author"));
            book.setPress(rs.getString("book_press"));
            book.setInventory(rs.getInt("book_inventory"));
            book.setImagePath(rs.getString("book_image_path"));
            bookList.add(book);
        }
        pstmt.close();
        return bookList;
    }
}
