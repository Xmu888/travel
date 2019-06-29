package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface PageDaoInter {
    //类型id    当前页码   显示多少页  查询的关键字
    List<Route> getPageData(String id, int pageNum, int showNumPage,String test);
    //当前类一共有多少条数据  使用类型id和关键字查找
    int pageSize(String id,String test);
}
