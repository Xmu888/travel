package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryIDaoInter;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryIDaoImpl implements CategoryIDaoInter {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> GetListForNav() {
        String sql = "select * from tab_category";
        List<Category> list = jt.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return list;
    }
}
