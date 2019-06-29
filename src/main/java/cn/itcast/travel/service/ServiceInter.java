package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface ServiceInter {
    boolean userEmpty(String username);//判断用户是否存在
    boolean saveUser(User u,String code);//注册成功在数据库插入
    boolean modifyStatus(String code);//如果存在就改变Status
    User Login(String us, String ps);//登录
}
