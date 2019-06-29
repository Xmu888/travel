package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ServiceInter;
import cn.itcast.travel.service.impl.ServiceImpl;
import cn.itcast.travel.util.MailUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@WebServlet("/user/*")
public class BaseUserServlet extends UserServlet {
    private ServiceInter service = new ServiceImpl();

    public void LoginOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("删除session");
        req.getSession().invalidate();//删除session
    }

    public void userEmptyServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("content-type", "text/plain;charset=utf-8");
        boolean b = service.userEmpty(req.getParameter("username"));
        System.out.println(req.getParameter("username"));
        resp.getWriter().print(b);
    }

    public void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        ObjectMapper obj = new ObjectMapper();
        String us = req.getParameter("username");
        String ps = req.getParameter("password");
        String codes = req.getParameter("code");
        HttpSession session = req.getSession();
        ResultInfo resultInfo = new ResultInfo();
        String code = (String) session.getAttribute("CHECKCODE_SERVER");

        if ("".equals(code) || !code.equalsIgnoreCase(codes) || null == codes) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码输入有误!");
        } else {
            session.removeAttribute("CHECKCODE_SERVER");
            User user = service.Login(us, ps);
            if (user == null) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号或密码错误!");
            } else if (user != null && "1".equals(user.getStatus())) {//登陆成功
                resultInfo.setFlag(true);
                session.setAttribute("LoginUser", user);
            } else {
                resultInfo.setErrorMsg("未通过邮箱验证！");
                resultInfo.setFlag(false);
            }
        }
        String s = obj.writeValueAsString(resultInfo);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(s);
    }

    public void InserUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("utf-8");
        resp.setHeader("content-type", "text/plain;charset=utf-8");
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute("CHECKCODE_SERVER");
        String type = req.getParameter("type");//1为判断验证码 2为注册
        if (null != type && "1".equals(type)) {
            try {
                String clienCode = req.getParameter("code");//验证码
                System.out.println(code);
                System.out.println(clienCode);
                if (null != clienCode && !"".equals(clienCode) && code.equalsIgnoreCase(clienCode)) {
                    resp.getWriter().print("true");
                } else {
                    resp.getWriter().print("请检查验证码");
                    return;
                }
            } catch (Exception e) {
                resp.getWriter().print("请检查信息！");
                return;
            }
        }
        if (null != type && "2".equals(type)) {
            resp.setHeader("content-type", "text/html;charset=utf-8");
            User u = new User();
            Map<String, String[]> userMap = req.getParameterMap();
            Set<Map.Entry<String, String[]>> entry = userMap.entrySet();
            for (Map.Entry<String, String[]> Entry : entry) {
                System.out.println(Entry.getKey() + "----" + Entry.getValue()[0]);
            }
            try {
                BeanUtils.populate(u, userMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            session.removeAttribute("CHECKCODE_SERVER");
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString();
            service.saveUser(u, s);
            resp.getWriter().print("<script>location.href='http://localhost/travel/register_ok.html'</script>");
            String email = req.getParameter("email");
            MailUtils.sendMail(email, "<a href='http://localhost/travel/user/Checkcode?code=" + s + "'>[激活邮件]黑马旅游网</a>", "黑马旅游网激活邮件");
        }
    }

    public void Checkcode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "text/html;charset=utf-8");
        String code = req.getParameter("code");
        boolean bool = false;
        if (!"".equals(code) && null != code) {
            System.out.println(bool + "flag");
            bool = service.modifyStatus(code);
        } else {
            resp.getWriter().print("<script>alert('激活失败')</script>");
            return;
        }
        if (bool) {
            resp.getWriter().print("<script>location.href='http://localhost/travel/login.html'</script>");
        } else {
            resp.getWriter().print("<script>alert('激活失败')</script>");
            return;
        }
    }


    public void GoOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("LoginUser");
        resp.sendRedirect("http://localhost/travel/index.html");
    }

    public void GetSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object Lu = req.getSession().getAttribute("LoginUser");
        ObjectMapper op = new ObjectMapper();
        String s = op.writeValueAsString(Lu);
        resp.getWriter().print(s);
        System.out.println(s + "我是GetSession");
    }
}
