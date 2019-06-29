package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.PageBean;
public interface PageServiceInter {
    //类型id   当前页码
    PageBean<Route> getPageData(String id,int pageNum,String test);
}
