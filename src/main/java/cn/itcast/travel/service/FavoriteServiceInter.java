package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorites;
import cn.itcast.travel.util.PageBean;

public interface FavoriteServiceInter {//插入收藏数据
    boolean insertFavForUid(String uid,String rid);
    boolean ridNotNULL(String rid, int uid);//查询该条是否存在
    PageBean<Favorites> getFavorites(String uid,int howPage);//查询该用户下面的所有收藏路线并且带分页
    PageBean<Favorites> getAllFavorites(String name,String numOne,String numTwo,int howPage);//查询该用户下面的所有收藏路线并且带分页
}
