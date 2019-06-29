package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDaoInter;
import cn.itcast.travel.domain.Favorites;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FavoriteDaoImpl implements FavoriteDaoInter {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Favorites> getFavorites(String uid, int howPage, int showPage) {
        String sql = "SELECT r.rid,rname,price,rimage FROM tab_route r,(SELECT * FROM `tab_favorite` WHERE uid = ?) u WHERE r.rid = u.rid LIMIT ?,?;";
        List<Favorites> list = jt.query(sql, new BeanPropertyRowMapper<Favorites>(Favorites.class), uid, howPage, showPage);
        return list;
    }

    @Override
    public int getFavoriteSize(String uid) {
        String sql = "SELECT COUNT(uid) FROM `tab_favorite` WHERE uid = ?";
        Integer aInt = null;
        try {
            aInt = jt.queryForObject(sql, Integer.class, uid);
        } catch (DataAccessException e) {
        }
        return aInt;
    }

    @Override
    public Integer ridNotNULL(String rid, int uid) {
        String sql = "SELECT * FROM tab_favorite WHERE rid = ? AND uid = ?";

        List<Map<String, Object>> list = null;
        try {
            list = jt.queryForList(sql, rid, uid);
            if(list.size()==1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public int insertFavForUid(String uid, String rid) {
        //插入指定的rid和uid到收藏表
        String countSql = "UPDATE `tab_route` SET COUNT = COUNT+1 WHERE rid = ?";
        String sql = "INSERT INTO tab_favorite VALUES(?,NOW(),?)";
        int update = 0;
        try {
            jt.execute("start transaction");//开启事务
            update = jt.update(sql, rid, uid);//执行收藏插入语句
            int ups = jt.update(countSql, rid);//让该线路的count收藏次数+1
            if(ups==1){
                jt.execute("commit");//如果返回值为1并且没有抛异常就提交事务
            }else{
                jt.execute("rollback");//如果不等于1就回滚
            }
        } catch (DataAccessException e) {
            //如果该方法报错，直接返回0  并且回滚
            jt.execute("rollback");
        }
        return update;
    }

    @Override
    public int getAllFavoritesSize(String name,String numOne,String numTwo) {//查询有多少条数据
        String sql = "select count(rid) from tab_route WHERE 1 = 1 ";
        ArrayList<Object> arr = new ArrayList<>();
        if(null!=numOne && null != numTwo){
            sql += "AND price BETWEEN ? AND ?";
            arr.add(numOne);
            arr.add(numTwo);
        }
        if(null!=name){
            sql += " AND rname LIKE ?";
            arr.add("%"+name+"%");
        }
        Integer integer = jt.queryForObject(sql, Integer.class, arr.toArray());
        System.out.println(integer);
        return integer;
    }

    @Override
    public List<Favorites> getAllFavorites(String name,String numOne,String numTwo,int howPage,int showPage) {
        String sql = "SELECT rid,rname,rimage,price,COUNT FROM tab_route WHERE 1 = 1 ";
        ArrayList<Object> arr = new ArrayList<>();
        if(null!=numOne && null != numTwo){
            sql += "AND price BETWEEN ? AND ?";
            arr.add(numOne);
            arr.add(numTwo);
        }
        if(null!=name){
           sql += "and rname LIKE  ?";
           arr.add("%"+name+"%");
        }
        sql += " ORDER BY COUNT DESC LIMIT ?,?";
        arr.add(howPage);
        arr.add(showPage);
        List<Favorites> query = null;
        try {
            System.out.println(arr);
            query = jt.query(sql, new BeanPropertyRowMapper<Favorites>(Favorites.class),arr.toArray());
        } catch (DataAccessException e) {
            System.out.println(sql);
            System.out.println("我报错了");
        }

//        LIMIT 0 , 8
//        WHERE price BETWEEN 500 AND 1000 LIKE rname="%春节%" ORDER BY COUNT DESC

        return query;
    }
//    public static void main(String[] args) {
//        List<Map<String, Object>> list = null;
//        System.out.println(list.size());
//    }
}
