package cn.itcast.travel.web.servlet;
import cn.itcast.travel.domain.Routes;
import cn.itcast.travel.service.RoutServiceInter;
import cn.itcast.travel.service.impl.RoutServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/routes/*")
public class RoutesServlet extends UserServlet {
    private RoutServiceInter service = new RoutServiceImpl();
    public void getRouseById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        Routes routes = service.getRoutes(id);
        ObjectMapper oMaper = new ObjectMapper();
        String s = oMaper.writeValueAsString(routes);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(s);
    }
}
