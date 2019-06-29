package cn.itcast.travel.web.servlet.oldJava;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ServiceInter;
import cn.itcast.travel.service.impl.ServiceImpl;
import cn.itcast.travel.util.MailUtils;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

//@WebServlet("/insertInto")
public class InserUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
       resp.setContentType("utf-8");
           resp.setHeader("content-type","text/plain;charset=utf-8");
        HttpSession session = req.getSession();
        String code = (String)session.getAttribute("CHECKCODE_SERVER");
        String type = req.getParameter("type");//1为判断验证码 2为注册
        if(null != type && "1".equals(type)){
            try {
                String clienCode = req.getParameter("code");//验证码
                if(null!=clienCode&&!"".equals(clienCode)&&code.equalsIgnoreCase(clienCode)){
                    session.removeAttribute("CHECKCODE_SERVER");
                    resp.getWriter().print("true");
                }else{
                    resp.getWriter().print("请检查验证码");
                    return;
                }
            } catch (Exception e) {
                resp.getWriter().print("验证码为空");
                return;
            }
        }
        if(null != type && "2".equals(type)){
            resp.setHeader("content-type","text/html;charset=utf-8");
            User u = new User();
            Map<String, String[]> userMap = req.getParameterMap();
                Set<Map.Entry<String, String[]>> entry = userMap.entrySet();
                for (Map.Entry<String, String[]> Entry : entry) {
                    System.out.println(Entry.getKey()+"----"+Entry.getValue()[0]);
                    //new String()
                }
            try {
                BeanUtils.populate(u,userMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            ServiceInter service = new ServiceImpl();
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString();
            service.saveUser(u,s);
            resp.getWriter().print("<script>location.href='./register_ok.html'</script>");
            String email = req.getParameter("email");
            MailUtils.sendMail(email,"<a href='http://localhost/travel/Checkcode?code="+s+"'>[激活邮件]黑马旅游网</a>","黑马旅游网激活邮件");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
