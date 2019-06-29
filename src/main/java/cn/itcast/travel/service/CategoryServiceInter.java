package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryServiceInter {
    List<Category> GetListForNav();//查询所有分类
}
