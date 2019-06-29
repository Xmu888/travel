package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorites;

import java.util.List;

public interface FavoriteDaoInter {
    int insertFavForUid(String uid,String rid);//根据用户id和线路id插入一条收藏数据
    Integer ridNotNULL(String rid, int uid);//根据id查询该收藏是否存在
    //   uid 用户id   howPage 差值   showPage每页显示多少条
    List<Favorites> getFavorites(String uid,int howPage,int showPage);//使用用户id查询对应页的数据
    //SELECT r.rid,rname,price,rimage FROM tab_route r,(SELECT * FROM `tab_favorite` WHERE uid = 29) u WHERE r.rid = u.rid LIMIT 0,8;
    int getFavoriteSize(String uid);//查询有多少条收藏数据
    //SELECT COUNT(uid) FROM `tab_favorite` WHERE uid = 29
    //搜索关键字   两个价格的范围   页码(被计算之后的差值)   每页显示的数量
    List<Favorites> getAllFavorites(String name,String numOne,String numTwo,int howPage,int showPage);//查询所有收藏的数据
    int getAllFavoritesSize(String name,String numOne,String numTwo);//查询所有线路一共有多少条
}
