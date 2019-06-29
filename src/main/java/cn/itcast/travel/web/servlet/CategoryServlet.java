package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryServiceInter;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.JedisUtil;
import cn.itcast.travel.web.servlet.UserServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends UserServlet {
    private CategoryServiceInter service = new CategoryServiceImpl();

    public void test(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("comming");
    }

    public void GetListForNav(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        Jedis jedis = JedisUtil.getJedis();
        String list = jedis.get("List");
        if(null==list){
            List<Category> category = service.GetListForNav();
            String s = obj.writeValueAsString(category);
            jedis.set("List",s);
            resp.getWriter().print(s);
            System.out.println("设置缓存");
        }else{
            resp.getWriter().print(jedis.get("List"));
            System.out.println("使用缓存");
        }
        jedis.close();
    }
}
