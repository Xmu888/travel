package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.PageServiceInter;
import cn.itcast.travel.service.impl.PageServiceImpl;
import cn.itcast.travel.util.PageBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Page/*")
public class PageBeanServlet extends UserServlet {
    private PageServiceInter service = new PageServiceImpl();
    public void GetPageData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String test = req.getParameter("test");
        String id = req.getParameter("id");
        System.out.println("我是id"+id);
        int pageNum = Integer.parseInt(req.getParameter("pageNum"));
        System.out.println(pageNum+"我是pageNum");
        PageBean<Route> pageData = service.getPageData(id, pageNum,test);
        ObjectMapper oMaper = new ObjectMapper();
        System.out.println("我是pageData"+pageData);
        String s = oMaper.writeValueAsString(pageData);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(s);
    }
}