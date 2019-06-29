package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryIDaoInter {
    List<Category> GetListForNav();//查询所有分类
}
