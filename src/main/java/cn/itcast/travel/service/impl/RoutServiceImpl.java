package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RoutDaoInter;
import cn.itcast.travel.dao.impl.RoutDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Routes;
import cn.itcast.travel.service.RoutServiceInter;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutServiceImpl implements RoutServiceInter {
    private RoutDaoInter dao = new RoutDaoImpl();
    @Override
    public Routes getRoutes(String id){
        Routes routs = new Routes();
        try {
            List<RouteImg> routeImgs = new ArrayList<>();
            Category category = new Category();
            List<Routes> rout = new RoutDaoImpl().getRout(id);
            for (Routes routes : rout) {
                Map<String, String> map = BeanUtils.describe(routes);
                BeanUtils.populate(routs,map);
                BeanUtils.populate(category,map);
                routeImgs.add(new RouteImg(Integer.parseInt(map.get("rgid")),Integer.parseInt(map.get("rid")),map.get("bigPic"),map.get("smallPic")));
            }
            routs.setCategory(category);
            routs.setRouteImgList(routeImgs);
        } catch (Exception e) {}
        return routs;
    }
}
