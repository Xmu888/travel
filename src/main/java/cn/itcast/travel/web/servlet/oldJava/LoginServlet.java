package cn.itcast.travel.web.servlet.oldJava;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ServiceInter;
import cn.itcast.travel.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ServiceInter service = new ServiceImpl();
        ObjectMapper obj = new ObjectMapper();
        String us = req.getParameter("username");
        String ps = req.getParameter("password");
        String codes = req.getParameter("code");
        HttpSession session = req.getSession();
        ResultInfo resultInfo = new ResultInfo();
        String code = (String)session.getAttribute("CHECKCODE_SERVER");

        if("".equals(code)||!code.equalsIgnoreCase(codes)||null==codes){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码输入有误!");
        }else{
            session.removeAttribute("CHECKCODE_SERVER");
            User user = service.Login(us,ps);
            if(user==null){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号或密码错误!");
            }else if(user!=null&&"1".equals(user.getStatus())){//登陆成功
                resultInfo.setFlag(true);
                session.setAttribute("LoginUser",user);
            }else{
                resultInfo.setErrorMsg("未通过邮箱验证！");
                resultInfo.setFlag(false);
            }
        }
        String s = obj.writeValueAsString(resultInfo);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(s);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
