package com.thirdgroup.servlet;

import com.thirdgroup.po.User;
import com.thirdgroup.service.UserService;
import com.thirdgroup.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = {"/userLogin.do", "/userRegist.do"})
public class UserServlet extends HttpServlet {
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

    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        try {
            User user = userService.getUserByUsernameAndPassword(username, password);
            request.getSession().setAttribute("currentUser", user);
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                request.getRequestDispatcher(".jsp").forward(request, response);            //登录成功
            } else {
                request.getRequestDispatcher(".jsp").forward(request, response);            //登录失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("pwd"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setAddress(request.getParameter("address"));
        try {
            if (userService.insertUser(user) != 0) {
                request.getRequestDispatcher("login.jsp").forward(request, response);        //注册成功，跳转到登陆页面
            } else {
                request.getRequestDispatcher(".jsp").forward(request, response);        //注册失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
