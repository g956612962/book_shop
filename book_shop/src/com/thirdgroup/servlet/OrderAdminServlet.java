package com.thirdgroup.servlet;

import com.thirdgroup.po.Order;
import com.thirdgroup.po.OrderAdmin;
import com.thirdgroup.po.OrderDetail;
import com.thirdgroup.service.OrderAdminService;
import com.thirdgroup.service.OrderDetailService;
import com.thirdgroup.service.OrderService;
import com.thirdgroup.service.impl.OrderAdminServiceImpl;
import com.thirdgroup.service.impl.OrderDetailServiceImpl;
import com.thirdgroup.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderAdminServlet", urlPatterns = {"/orderAdminLogin.do", "/orderAdminUpdatePassword.do",
        "/orderAdminListAllOrder.do", "/orderAdminGetOrderById.do", "/orderAdminListAllNotDeliverOrder.do",
        "/orderAdminDeliverOrder.do", "/orderAdminDeleteOrder.do"})
public class OrderAdminServlet extends HttpServlet {
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

    //订单管理员登录
    private void orderAdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderAdminService orderAdminService = new OrderAdminServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            OrderAdmin orderAdmin = orderAdminService.getOrderAdmin(username, password);
            request.getSession().setAttribute("currentOrderAdmin", orderAdmin);
            if (username.equals(orderAdmin.getUsername()) && password.equals(orderAdmin.getPassword())) {
                request.getRequestDispatcher(".jsp").forward(request, response);            //登录成功
            } else {
                request.getRequestDispatcher(".jsp").forward(request, response);            //登录失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //订单管理员更改密码
    private void orderAdminUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderAdminService orderAdminService = new OrderAdminServiceImpl();
        String password = request.getParameter("password");
        OrderAdmin currentOrderAdmin = (OrderAdmin) request.getSession().getAttribute("currentOrderAdmin");
        try {
            if (orderAdminService.updateOrderAdminPassword(password, currentOrderAdmin.getUsername()) != 0) {
                currentOrderAdmin.setPassword(password);
                request.getSession().setAttribute("currentOrderAdmin", currentOrderAdmin);
                request.getRequestDispatcher(".jsp").forward(request, response);        //修改成功
            } else {
                request.getRequestDispatcher(".jsp").forward(request, response);        //修改失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //订单管理员列出所有订单
    private void orderAdminListAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
        try {
            List<Order> allOrderList = orderService.listAllOrder();
            List<OrderDetail> allOrderDetailList = orderDetailService.listAllOrderDetail();
            request.getSession().setAttribute("allOrderList", allOrderList);
            request.getSession().setAttribute("allOrderDetailList", allOrderDetailList);
            request.getRequestDispatcher("all_order_list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查看单个订单信息
    private void orderAdminGetOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderService orderService = new OrderServiceImpl();
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
        try {
            Order order = orderService.getOrderById(orderId);
            List<OrderDetail> orderDetailList = orderDetailService.listOrderDetailByOrderId(orderId);
            request.setAttribute("order", order);
            request.setAttribute("orderDetailList", orderDetailList);
            request.getRequestDispatcher("admin_current_order.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //列出所有未发货订单
    private void orderAdminListAllNotDeliverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int isDeliver = Integer.parseInt(request.getParameter("isDeliver"));
        OrderService orderService = new OrderServiceImpl();
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
        try {
            List<Order> adminNotDeliverOrderList = orderService.listOrderByIsDeliver(isDeliver);
            List<OrderDetail> adminNotDeliverOrderDetailList = new ArrayList<OrderDetail>();
            List<OrderDetail> tmpList = new ArrayList<OrderDetail>();
            for (Order order : adminNotDeliverOrderList) {
                tmpList = orderDetailService.listOrderDetailByOrderId(order.getId());
                for (OrderDetail orderDetail : tmpList) {
                    adminNotDeliverOrderDetailList.add(orderDetail);
                }
            }
            request.setAttribute("adminNotDeliverOrderList", adminNotDeliverOrderList);
            request.setAttribute("adminNotDeliverOrderDetailList", adminNotDeliverOrderDetailList);
            request.getRequestDispatcher("admin_not_deliver_order_list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //订单管理员发货
    private void orderAdminDeliverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        int isDeliver = Integer.parseInt(request.getParameter("isDeliver"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        try {
            orderService.updateOrderDeliverStatus(isDeliver, orderId);
            request.getRequestDispatcher("admin_not_deliver_order_list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //订单管理员删除订单
    private void orderAdminDeleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        try {
            orderService.deleteOrderById(orderId);
            orderDetailService.deleteOrderDetailByOrderId(orderId);
            request.getRequestDispatcher(".jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
