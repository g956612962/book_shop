package com.thirdgroup.servlet;

import com.thirdgroup.po.InventoryAdmin;
import com.thirdgroup.service.InventoryAdminService;
import com.thirdgroup.service.impl.InventoryAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "InventoryAdminServlet", urlPatterns = {"/inventoryAdminLogin.do", "/inventoryAdminUpdatePassword.do"})
public class InventoryAdminServlet extends HttpServlet {
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

    //库存管理员登录
    private void inventoryAdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventoryAdminService inventoryAdminService = new InventoryAdminServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            InventoryAdmin inventoryAdmin = inventoryAdminService.getInventoryAdmin(username, password);
            request.getSession().setAttribute("currentInventoryAdmin", inventoryAdmin);
            if (username.equals(inventoryAdmin.getUsername()) && password.equals(inventoryAdmin.getPassword())) {
                request.getRequestDispatcher(".jsp").forward(request, response);            //登录成功
            } else {
                request.getRequestDispatcher(".jsp").forward(request, response);            //登录失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //库存管理员更改密码
    private void inventoryAdminUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventoryAdminService inventoryAdminService = new InventoryAdminServiceImpl();
        String password = request.getParameter("password");
        InventoryAdmin currentInventoryAdmin = (InventoryAdmin) request.getSession().getAttribute("currentInventoryAdmin");
        try {
            if (inventoryAdminService.updateInventoryAdminPassword(password, currentInventoryAdmin.getUsername()) != 0) {
                currentInventoryAdmin.setPassword(password);
                request.getSession().setAttribute("currentInventoryAdmin", currentInventoryAdmin);
                request.getRequestDispatcher(".jsp").forward(request, response);        //修改成功
            } else {
                request.getRequestDispatcher(".jsp").forward(request, response);        //修改失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
