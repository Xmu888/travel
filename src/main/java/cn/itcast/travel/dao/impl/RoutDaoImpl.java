package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RoutDaoInter;
import cn.itcast.travel.domain.Routes;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class RoutDaoImpl implements RoutDaoInter {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Routes> getRout(String id) {//我用id成就你的梦想
        String sql = "SELECT * FROM (SELECT rid,rgid,rname,price,routeIntroduce,rflag,rdate,isThemeTour,COUNT,rimage,p.sid sid,sourceId,cid,cname,bigPic,smallPic,sname,consphone,address FROM (SELECT rid,rgid,rname,price,routeIntroduce,rflag,rdate,isThemeTour,COUNT,rimage,sid,sourceId,c.cid cid,cname,bigPic,smallPic FROM ( SELECT r.rid rid,rgid,rname,price,routeIntroduce,rflag,rdate,isThemeTour,COUNT,cid,rimage,sid,sourceId,bigPic,smallPic FROM `tab_route` r , `tab_route_img` ri WHERE r.rid = ri.rid ) o , `tab_category` c WHERE o.`cid` = c.`cid`) p LEFT JOIN tab_seller t ON  p.sid = t.sid ) tb WHERE tb.rid = ?";
        return jt.query(sql, new BeanPropertyRowMapper<>(Routes.class),id);
    }
}
