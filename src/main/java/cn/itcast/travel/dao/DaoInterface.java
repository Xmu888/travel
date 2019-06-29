package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface DaoInterface {
    boolean userEmpty(String username);//判断用户是否存在Dao

    boolean saveUser(User u,String code);//用户注册插入数据

    boolean modifyStatus(String code);//判断是否存在该code

    User FindUserAndPass(String us, String ps);//登录

}
