package com.thirdgroup.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetEncodingFilter implements Filter {
    private static String encoding; // 定义变量接收初始化的值

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("CharsetEncoding");
    }

}
