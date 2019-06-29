package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.DaoInterface;
import cn.itcast.travel.dao.impl.DaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ServiceInter;

import java.util.List;

public class ServiceImpl implements ServiceInter {
    private DaoInterface dao = new DaoImpl();

    @Override
    public User Login(String us, String ps) {
        return dao.FindUserAndPass(us,ps);
    }



    @Override
    public boolean modifyStatus(String code) {
        return dao.modifyStatus(code);
    }

    @Override
    public boolean saveUser(User u,String code) {
        return dao.saveUser(u,code);
    }
    @Override
    public boolean userEmpty(String username) {
        return dao.userEmpty(username);
    }

}
