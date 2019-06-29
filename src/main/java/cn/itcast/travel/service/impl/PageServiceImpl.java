package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.PageDaoInter;
import cn.itcast.travel.dao.impl.PageDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.PageServiceInter;
import cn.itcast.travel.util.PageBean;

import javax.sound.midi.Soundbank;
import java.util.List;

public class PageServiceImpl implements PageServiceInter {
    private PageDaoInter dao = new PageDaoImpl();
    @Override
    public PageBean<Route> getPageData(String id, int pageNum,String test) {
        //总页数有多少条
        int i = dao.pageSize(id,test);
        if(1==-1){
            return null;
        }
        PageBean<Route> page = new PageBean<>(pageNum, i, 8);
        //当前id   当前页数    总页数
        List<Route> list = dao.getPageData(id, pageNum, page.getPageSize(),test);
        page.setList(list);
        System.out.println(i);
        System.out.println("我是数据库查出来的集合"+list);
        System.out.println(page);
        return page;
    }
}
