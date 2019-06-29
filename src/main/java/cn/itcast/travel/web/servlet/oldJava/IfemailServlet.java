package cn.itcast.travel.web.servlet.oldJava;
import cn.itcast.travel.service.impl.ServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebServlet("/Checkcode")
public class IfemailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setHeader("content-type","text/html;charset=utf-8");
        String code = req.getParameter("code");
        ServiceImpl service = new ServiceImpl();
        boolean bool = false;
        System.out.println(code);
        if(!"".equals(code)&&null!=code){
            bool = service.modifyStatus(code);
        }else{
            resp.getWriter().print("<script>alert('激活失败')</script>");
            return;
        }
        if(bool){
            resp.getWriter().print("<script>location.href='./login.html'</script>");
        }else{
            resp.getWriter().print("<script>alert('激活失败')</script>");
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
