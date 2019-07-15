package com.thirdgroup.servlet;

import com.thirdgroup.po.Book;
import com.thirdgroup.service.BookService;
import com.thirdgroup.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookInventoryServlet", urlPatterns = {"/queryBookInventoryById.do", "/queryBookInventory.do"})
public class BookInventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的URI地址信息
        String url = request.getRequestURI();
        // 截取其中的方法名
        String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        Method method = null;
        try {
            // 使用反射机制获取在本类中声明了的方法
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("调用方法出错！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void queryBookInventoryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            Book book = bookService.getBookById(bookId);
            request.setAttribute("currentBookNumber", book.getInventory());             //进行查询图书库存时，将book放入request中
            //System.out.println(request.getAttribute("currentBookNumber"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void queryBookInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        try {
            List<Book> bookList = bookService.listAllBook();
            request.getSession().setAttribute("bookInventoryList", bookList);               //进行查询图书库存列表时，将bookList放入session中
            for (Book book : bookList) {
                System.out.println(book.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBookInventoryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int number = Integer.parseInt(request.getParameter("number"));
        try {
            bookService.addBookInventory(number, bookId);
            Book book = bookService.getBookById(bookId);  //增加图书库存时，将book放入request中
            request.setAttribute("currentBookNumber", book.getInventory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void redBookInventoryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int number = Integer.parseInt(request.getParameter("number"));
        try {
            bookService.redBookInventory(number, bookId);
            Book book = bookService.getBookById(bookId);  //减少图书库存时，将book放入request中
            request.setAttribute("currentBookNumber", book.getInventory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
