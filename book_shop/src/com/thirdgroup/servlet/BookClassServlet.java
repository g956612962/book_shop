package com.thirdgroup.servlet;

import com.thirdgroup.po.Book;
import com.thirdgroup.po.BookClass;
import com.thirdgroup.service.BookClassService;
import com.thirdgroup.service.impl.BookClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookClassServlet" ,urlPatterns={"/bookClassAdd.do","/bookClassUpdate.do","/bookClassSelect.do","/bookClassIfexitBook.do","/bookClassDelete.do","/bookClassIfexit.do","/getBookClassinfo.do"})
public class BookClassServlet extends HttpServlet {
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
        doPost(request,response);
    }
    private void bookClassAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookClass bookClass = new BookClass();
        String name = request.getParameter("name");

        bookClass.setClassName(name);
        BookClassService bookClassService = new BookClassServiceImpl();
        try {

                bookClassService.insertBookClass(bookClass);
                bookClassSelect(request,response);
                request.getRequestDispatcher("allBookClass.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void bookClassUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String name = request.getParameter("name");
        System.out.println(name+"up");
        BookClassService bookClassService = new BookClassServiceImpl();
        try {
            bookClassService.updateBookClassName(name,Integer.parseInt(request.getParameter("id")));
            bookClassSelect(request,response);
            request.getRequestDispatcher("allBookClass.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getBookClassinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookClassService bookClassService  = new BookClassServiceImpl();
        request.setCharacterEncoding("utf-8");
        BookClass bookClass = new BookClass();
        try {
            bookClass = bookClassService.selectBookClassByName(new String(request.getParameter("bookClassName").getBytes("ISO8859-1"),"UTF-8"));
            request.setAttribute("bookClass",bookClass);

            request.getRequestDispatcher("updateBookClass.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void bookClassSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookClassService bookClassService  = new BookClassServiceImpl();
        request.setCharacterEncoding("utf-8");
        try {
            List<BookClass> bookClassList = bookClassService.listAllBookClass();
            request.getSession().setAttribute("bookClassList",bookClassList);
            request.getRequestDispatcher("allBookClass.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void bookClassDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookClassService bookClassService  = new BookClassServiceImpl();
        PrintWriter out = response.getWriter();
        try {
            if (bookClassService.getBooKCountByCLass(new String(request.getParameter("bookClassName").getBytes("ISO8859-1"),"UTF-8"))!=0)
            {
                System.out.println("delete");
                out.print("<html><head><meta charset='UTF-8'></head>");
                out.print("<script type='text/javascript' charset ='UTF-8'>location.href='allBookClass.jsp';alert('该主题下存在图书，禁止删除！'); </script>");
                out.print("</html>");
                out.flush();out.close();

            }else{
                System.out.println("delete2");
                bookClassService.deleteBookClassById(Integer.parseInt(request.getParameter("id")));
            }
            bookClassSelect(request,response);
            request.getRequestDispatcher("allBookClass.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bookClassIfexit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookClass bookClass = new BookClass();
        BookClassService bookClassService = new BookClassServiceImpl();
        PrintWriter out = response.getWriter();
        bookClass.setClassName(new String(request.getParameter("bookClassName").getBytes("ISO8859-1"),"UTF-8"));
        if (bookClass.getClassName().equals("")||bookClass.getClassName()==null){
            out.print("");
            out.flush();out.close();
        }else {
            try {
                if (bookClassService.bookclassIfexit(bookClass.getClassName())){
                    out.print(true);
                    out.flush();out.close();
                }else {
                    out.print(false);
                    out.flush();out.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void bookClassIfexitBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookClass bookClass = new BookClass();
        BookClassService bookClassService = new BookClassServiceImpl();
        PrintWriter out = response.getWriter();
        bookClass.setClassName(new String(request.getParameter("bookClassName").getBytes("ISO8859-1"),"UTF-8"));
            try {
                if (bookClassService.getBooKCountByCLass(bookClass.getClassName())!=0){
                    out.print(true);
                    out.flush();out.close();
                }else {
                    out.print(false);
                    out.flush();out.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
