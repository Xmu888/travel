package cn.itcast.travel.service;

import cn.itcast.travel.domain.Routes;

public interface RoutServiceInter {
    Routes getRoutes(String id);//使用id查询这条线路的所有信息
}
