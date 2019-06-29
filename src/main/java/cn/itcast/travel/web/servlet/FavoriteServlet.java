package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Favorites;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteServiceInter;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.util.PageBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favoritd/*")
public class FavoriteServlet extends UserServlet {
    private FavoriteServiceInter service = new FavoriteServiceImpl();

    public void getAllFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String numOne = req.getParameter("numOne");
        String numTwo = req.getParameter("numTwo");
        String howPage = req.getParameter("howPage");
        PageBean<Favorites> list = service.getAllFavorites(name, numOne, numTwo, Integer.parseInt(howPage));
        ObjectMapper oMaper = new ObjectMapper();
        String s = oMaper.writeValueAsString(list);
        resp.setContentType("application/json;charset=utf-8");
        System.out.println(s);
        resp.getWriter().print(s);
    }
    public void getFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("LoginUser");
        ObjectMapper oMaper = new ObjectMapper();
        if(null==user){
            ResultInfo resultInfo = new ResultInfo();
            resp.setContentType("application/json;charset=utf-8");
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("请登录!");
            String s = oMaper.writeValueAsString(resultInfo);
            resp.getWriter().print(s);
            return;
        }
        String howPage = req.getParameter("howPage");
        PageBean<Favorites> pageBean = service.getFavorites(String.valueOf(user.getUid()), Integer.parseInt(howPage));
        resp.setContentType("application/json;charset=utf-8");
        String s = oMaper.writeValueAsString(pageBean);
        resp.getWriter().print(s);
    }

    public void ridNotNULL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        System.out.println("我是rid" + rid);
        User user = (User) req.getSession().getAttribute("LoginUser");
        boolean b = service.ridNotNULL(rid, user.getUid());
        resp.setContentType("application/json;charset=utf-8");
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(b);
        ObjectMapper oMaper = new ObjectMapper();
        String s = oMaper.writeValueAsString(resultInfo);
        System.out.println(s);
        resp.getWriter().print(s);//如果不存在返回true，如果存在返回false
    }

    public void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        User user = (User) req.getSession().getAttribute("LoginUser");
        resp.setContentType("application/json;charset=utf-8");
        ResultInfo resultInfo = new ResultInfo();
        //插入一条收藏数据，如果成功就返回true
        boolean b = false;
        if (null != user && !"".equals(user)) {
            b = service.insertFavForUid(String.valueOf(user.getUid()), rid);
        } else {
            resultInfo.setErrorMsg("请登录！");
        }
        resultInfo.setFlag(b);
        ObjectMapper oMaper = new ObjectMapper();
        String s = oMaper.writeValueAsString(resultInfo);
        System.out.println(resultInfo);
        System.out.println(s);
        resp.getWriter().print(s);
    }

}
