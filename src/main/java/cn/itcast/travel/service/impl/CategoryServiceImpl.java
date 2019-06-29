package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryIDaoInter;
import cn.itcast.travel.dao.impl.CategoryIDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryServiceInter;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryServiceImpl implements CategoryServiceInter {
    private CategoryIDaoInter dao = new CategoryIDaoImpl();
    @Override
    public List<Category> GetListForNav() {
            return dao.GetListForNav();
    }
}
