package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.DaoInterface;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class DaoImpl implements DaoInterface {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User FindUserAndPass(String us, String ps) {
        User user = null;
        try {
            user = jt.queryForObject("select * from tab_user where username = ? and password = ?", new BeanPropertyRowMapper<User>(User.class), us, ps);
        } catch (DataAccessException e) {
        }
        return user;
    }


    @Override
    public boolean modifyStatus(String code) {
        User user = null;
        try {
            user = jt.queryForObject("select * from tab_user where code = ?", new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            return false;
        }
        if(null!=user){
            int update = jt.update("UPDATE tab_user SET STATUS = '1' WHERE uid = ?", user.getUid());
            if(update==1){
                System.out.println(user.getUid());
                jt.update("UPDATE tab_user SET code = null WHERE uid = ?", user.getUid());//清空激活码
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean userEmpty(String username) {
            Long aLong = jt.queryForObject("select count(*) from tab_user WHERE username = ?", Long.class, username);
            if(aLong==0){
                return true;
            }else{
                return false;
            }
    }

    @Override
    public boolean saveUser(User u,String code) {
        String sql = "insert into tab_user values(null,?,?,?,?,?,?,?,'0',?)";
        int update = jt.update(sql, u.getUsername(), u.getPassword(), u.getName(), u.getBirthday(), u.getSex(), u.getTelephone(), u.getEmail(),code);
        if(update==1){
            return true;
        }else{
            return false;
        }
    }
}
