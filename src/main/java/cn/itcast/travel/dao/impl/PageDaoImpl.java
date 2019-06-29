package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.PageDaoInter;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.SocketTimeoutException;
import java.util.List;

public class PageDaoImpl implements PageDaoInter {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Route> getPageData(String id, int pageNum, int showNumPage,String test) {
        String sql = "SELECT * FROM tab_route WHERE 1 = 1";
//        SELECT * FROM tab_route WHERE cid = 5 AND rname LIKE '%新疆%' LIMIT 0 , 8;
        if(!"0".equals(id)&&!"".equals(id)&&null!=id){
            sql += " and cid = "+id;
        }
        if(null!=test&&!"".equals(test)){
            sql += " AND rname LIKE '%"+test+"%'";
        }
        sql += " LIMIT "+((pageNum-1)*showNumPage)+" , "+showNumPage;
        List<Route> list = null;
        try {
            list = jt.query(sql, new BeanPropertyRowMapper<Route>(Route.class));

            System.out.println((pageNum-1)*showNumPage+"我是差值 ");
            System.out.println(showNumPage+"我是显示的页数");
            System.out.println(id+"我是id");
        } catch (DataAccessException e) {
            System.out.println(sql);
        }
        return list;
    }

    @Override
    public int pageSize(String id,String test) {//返回查询的总条数
        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1 = 1";
        if(!"0".equals(id)&&!"".equals(id)&&null!=id){
            sql += " AND cid = "+id;//类型id
        }
        if(!"".equals(test)&&null!=test){
            sql += " AND rname LIKE '%"+test+"%'";
        }
        Integer integer = -1;
        System.out.println(sql);
        try {
            integer = jt.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
        }
        return integer;
    }
}
