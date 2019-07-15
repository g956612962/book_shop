package com.thirdgroup.servlet;

import com.thirdgroup.po.Book;
import com.thirdgroup.po.InventoryAdmin;
import com.thirdgroup.po.Order;
import com.thirdgroup.po.User;
import com.thirdgroup.service.BookService;
import com.thirdgroup.service.OrderService;
import com.thirdgroup.service.UserService;
import com.thirdgroup.service.impl.BookServiceImpl;
import com.thirdgroup.service.impl.OrderServiceImpl;
import com.thirdgroup.service.impl.UserServiceImpl;
import com.thirdgroup.util.DateFormat;
import com.thirdgroup.util.GetCurrentTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "CartServlet", urlPatterns = {"/addBookToCart.do", "/removeBookFromCart.do",
        "/clearCart.do", "/checkOutCart.do", "/listMyCart.do"})
public class CartServlet extends HttpServlet {
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
            e.printStackTrace();
            throw new RuntimeException("调用方法出错！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Map<Integer, Integer> cartMap = (HashMap<Integer, Integer>)request.getSession().getAttribute("cartMap");
        if (cartMap == null) {
            cartMap = new HashMap<Integer, Integer>();
        }

        if (!cartMap.containsKey(bookId)) {
            cartMap.put(bookId, 1);
        } else {
            int tmp = cartMap.get(bookId) + 1;
            cartMap.put(bookId, tmp);
        }
        request.getSession().setAttribute("cartMap", cartMap);
        response.sendRedirect("test.jsp");

        System.out.println("----------------添加购物车操作");
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("---------------结束");
    }

    private void removeBookFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] bookIdTmp = request.getParameterValues("bookId");
        int[] bookId = new int[bookIdTmp.length + 1];
        for (int i = 0; i < bookIdTmp.length; i++) {
             bookId[i] = Integer.parseInt(bookIdTmp[i]);
        }
        Map<Integer, Integer> cartMap = (HashMap<Integer, Integer>)request.getSession().getAttribute("cartMap");
        for (int i = 0 ; i < bookId.length; i++) {
            if (cartMap.containsKey(bookId[i])) {
                int tmp = cartMap.get(bookId[i]) - 1;
                if (tmp == 0) {
                    cartMap.remove(bookId[i]);
                } else {
                    cartMap.put(bookId[i], tmp);
                }
            } else {
                cartMap.remove(bookId[i]);
            }
        }
        System.out.println("----------------移除购物车操作");
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("---------------结束");
        request.getSession().setAttribute("cartMap", cartMap);
        response.sendRedirect("test.jsp");
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> cartMap = (HashMap<Integer, Integer>)request.getSession().getAttribute("cartMap");
        cartMap.clear();
        request.getSession().setAttribute("cartMap", cartMap);
        response.sendRedirect("test.jsp");
        System.out.println("----------------清空购物车操作");
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("---------------结束");
    }

    private void checkOutCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] bookIdTmp = request.getParameterValues("bookId");
        int[] bookId = new int[bookIdTmp.length + 1];
        for (int i = 0; i < bookIdTmp.length; i++) {
            bookId[i] = Integer.parseInt(bookIdTmp[i]);
        }
        Book book;
        float totPrice = 0;
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>)request.getSession().getAttribute("cartMap");
        User user = (User) request.getSession().getAttribute("currentUser");
        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();
        BookService bookService = new BookServiceImpl();
        Order order = new Order();
        order.setUserId(user.getId());
        order.setCreateTime(DateFormat.timestampToString(GetCurrentTime.getcurrentTime()));
        order.setEmail(user.getEmail());
        order.setPhone(user.getPhone());
        order.setAddress(user.getAddress());
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            int bookKey = entry.getKey();
            int bookValue = entry.getValue();
            try {
                book = bookService.getBookById(bookKey);
                totPrice += book.getPrice() * bookValue;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        order.setPrice(totPrice);
        try {
            userService.updateUserBalance(totPrice, user.getUsername());
            orderService.insertOrder(order, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("cartMap", cartMap);
        response.sendRedirect("test.jsp");
        System.out.println("----------------结算购物车操作");
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("总价格："+ order.getPrice());
        System.out.println("---------------结束");
    }

    private void listMyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        Book book;
        Map<Integer, Integer> cartMap = (HashMap<Integer, Integer>)request.getSession().getAttribute("cartMap");
        Map<Integer, Book> bookMap = new HashMap<Integer, Book>();
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            int bookKey = entry.getKey();
            try {
                book = bookService.getBookById(bookKey);
                bookMap.put(bookKey, book);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
