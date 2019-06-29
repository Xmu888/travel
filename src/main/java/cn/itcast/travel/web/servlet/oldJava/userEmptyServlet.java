package cn.itcast.travel.web.servlet.oldJava;


import cn.itcast.travel.service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断用户是否存在Servlet
 */
//@WebServlet("/userEmptyServlet")
public class userEmptyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/plain;charset=utf-8");
        ServiceImpl service = new ServiceImpl();
        boolean b = service.userEmpty(request.getParameter("username"));
        System.out.println(request.getParameter("username"));
        response.getWriter().print(b);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}