package com.thirdgroup.servlet;

import com.thirdgroup.po.Book;
import com.thirdgroup.service.BookClassService;
import com.thirdgroup.service.BookService;
import com.thirdgroup.service.impl.BookClassServiceImpl;
import com.thirdgroup.service.impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = {"/bookAdd.do", "/bookInfo.do", "/bookUpdate.do", "/selectBookList.do", "/bookDelete.do"})
public class BookServlet extends HttpServlet {
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

    private void bookAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Book book = new Book();
        String uploadFileName = ""; //上传的文件名
        String fieldName = ""; //表单字段元素的name属性值
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //上传文件的存储路径
        String uploadFilePath = request.getSession().getServletContext().getRealPath("upload") + "\\";
        //创建临时文件目录路径
        File tempPatchFile = new File("e:\\upload\\");
        if (!tempPatchFile.exists()) {  //判断文件或目录是否存在
            tempPatchFile.mkdirs();   //创建指定的目录，
        }
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(4096); //设置缓冲区大小4Kb
            factory.setRepository(tempPatchFile);  //设置上传文件的临时文件存放路径
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {  //依次处理每个表单元素
                    FileItem item = iter.next();
                    if (item.isFormField()) { //普通表单字段
                        fieldName = item.getFieldName();//表单字段的name属性
                        if (fieldName.equals("className")) {
                            book.setClassName(item.getString("utf-8"));
                        } else if (fieldName.equals("bookname")) {
                            book.setName(item.getString("utf-8"));
                        } else if (fieldName.equals("money")) {
                            book.setPrice(Float.parseFloat(item.getString("utf-8")));
                        } else if (fieldName.equals("num")) {
                            book.setInventory(Integer.parseInt(item.getString("utf-8")));
                        } else if (fieldName.equals("author")) {
                            book.setAuthor(item.getString("utf-8"));
                        } else if (fieldName.equals("address")) {
                            book.setPress(item.getString("utf-8"));
                        } else if (fieldName.equals("information")) {
                            book.setExpress(item.getString("utf-8"));
                        }
                    } else {  //文件表单字段
                        String fileName = item.getName();
                        List<String> filType = Arrays.asList("gif", "bmp", "jpg", "png");
                        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                        if (!filType.contains(ext)) {
                            request.setAttribute("info", "上传失败，文件类型只能是gif,bmp,jpg");
                        } else {
                            if (fileName != null && !fileName.equals("")) {
                                File fullFile = new File(item.getName());
                                File saveFile = new File(uploadFilePath, fullFile.getName());
                                item.write(saveFile);
                                uploadFileName = fullFile.getName();
                                book.setImagePath("/upload/" + uploadFileName);
                                request.setAttribute("uploadFileName", book.getImagePath());
                            }
                        }
                    }
                }
            } catch (FileUploadBase.SizeLimitExceededException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        BookService bookService = new BookServiceImpl();//service 实例化
        BookClassService bookClassService = new BookClassServiceImpl();
        try {
            book.setClassId(bookClassService.selectBookClassByName(book.getClassName()).getId());
            if (bookService.insertBook(book) == 1) {//把数据存进去 判断是否成功
                request.setAttribute("info", "添加成功");
                request.getRequestDispatcher("addBook.html").forward(request, response);
            } else {
                request.setAttribute("info", "添加失败");
                request.getRequestDispatcher("addBook.html").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void selectBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();//service 实例化
        try {
            List<Book> bookList = bookService.listAllBook();
            request.getSession().setAttribute("bookList", bookList);
            response.sendRedirect("test.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bookInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();//service 实例化
        try {
            Book book = bookService.getBookById(Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("book", book);
            request.getRequestDispatcher("updateBook.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bookUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Book book = new Book();
        book = (Book) request.getSession().getAttribute("book");
        String uploadFileName = ""; //上传的文件名
        String fieldName = ""; //表单字段元素的name属性值
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //上传文件的存储路径
        String uploadFilePath = request.getSession().getServletContext().getRealPath("upload") + "\\";
        //创建临时文件目录路径
        File tempPatchFile = new File("e:\\upload\\");
        if (!tempPatchFile.exists()) {  //判断文件或目录是否存在
            tempPatchFile.mkdirs();   //创建指定的目录，
        }
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(4096); //设置缓冲区大小4Kb
            factory.setRepository(tempPatchFile);  //设置上传文件的临时文件存放路径
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {  //依次处理每个表单元素
                    FileItem item = iter.next();
                    if (item.isFormField()) { //普通表单字段
                        fieldName = item.getFieldName();//表单字段的name属性
                        if (fieldName.equals("className")) {
                            book.setClassName(item.getString("utf-8"));
                        } else if (fieldName.equals("bookname")) {
                            book.setName(item.getString("utf-8"));
                        } else if (fieldName.equals("money")) {
                            book.setPrice(Float.parseFloat(item.getString("utf-8")));
                        } else if (fieldName.equals("num")) {
                            book.setInventory(Integer.parseInt(item.getString("utf-8")));
                        } else if (fieldName.equals("author")) {
                            book.setAuthor(item.getString("utf-8"));
                        } else if (fieldName.equals("address")) {
                            book.setPress(item.getString("utf-8"));
                        } else if (fieldName.equals("information")) {
                            book.setExpress(item.getString("utf-8"));
                        }
                    } else {  //文件表单字段
                        String fileName = item.getName();
                        List<String> filType = Arrays.asList("gif", "bmp", "jpg", "png");
                        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                        if (!filType.contains(ext)) {
                            request.setAttribute("info", "上传失败，文件类型只能是gif,bmp,jpg");
                        } else {
                            if (fileName != null && !fileName.equals("")) {
                                File fullFile = new File(item.getName());
                                File saveFile = new File(uploadFilePath, fullFile.getName());
                                item.write(saveFile);
                                uploadFileName = fullFile.getName();
                                String oldImgPath = book.getImagePath().substring(book.getImagePath().lastIndexOf("/") + 1, book.getImagePath().length());
                                if (uploadFileName != "" || !uploadFileName.equals(null))
                                    if (!uploadFileName.equals(book.getImagePath()))
                                        book.setImagePath("/upload/" + uploadFileName);
                            }
                        }
                    }
                }
            } catch (FileUploadBase.SizeLimitExceededException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BookService bookService = new BookServiceImpl();//service 实例化
        BookClassService bookClassService = new BookClassServiceImpl();
        try {
            book.setClassId(bookClassService.selectBookClassByName(book.getClassName()).getId());
            if (bookService.updateBookInformation(book) == 1) {//把数据存进去 判断是否成功
                request.setAttribute("info", "更新成功");
                request.getRequestDispatcher("aaa.jsp").forward(request, response);
            } else {
                request.setAttribute("info", "更新");
                request.getRequestDispatcher("aaa.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bookDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookService bookService = new BookServiceImpl();//service 实例化
        try {
            bookService.deleteBookById(Integer.parseInt(request.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
