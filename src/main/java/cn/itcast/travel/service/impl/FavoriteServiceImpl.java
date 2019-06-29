package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDaoInter;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorites;
import cn.itcast.travel.service.FavoriteServiceInter;
import cn.itcast.travel.util.PageBean;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteServiceInter {
    private FavoriteDaoInter dao = new FavoriteDaoImpl();
    @Override
    public PageBean<Favorites> getAllFavorites(String name, String numOne, String numTwo, int howPage) {
        int allSize = dao.getAllFavoritesSize(name,numOne,numTwo);
        PageBean<Favorites> pageBean = new PageBean<>(howPage,allSize,8);
        List<Favorites> list = dao.getAllFavorites(name, numOne, numTwo, pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setList(list);
        System.out.println(list);
        return pageBean;
    }

    @Override
    public PageBean<Favorites> getFavorites(String uid, int howPage) {
        int fsize = dao.getFavoriteSize(uid);//用户收藏对应的总数
        //Page设置每页的显示数量
        PageBean pageBean = new PageBean(howPage, fsize, 12);
        List<Favorites> list = dao.getFavorites(uid, pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public boolean ridNotNULL(String rid, int uid) {
        Integer ints = dao.ridNotNULL(rid,uid);
        System.out.println("我是ints"+ints);
        if(null != ints && ints != 1){
            return true;//不存在返回true
        }else{
            return false;//存在返回false
        }
    }

    @Override
    public boolean insertFavForUid(String uid, String rid) {
        int i =  dao.insertFavForUid(uid, rid);
        if(i==1){
            return true;
        }else{
            return false;
        }
    }
}
