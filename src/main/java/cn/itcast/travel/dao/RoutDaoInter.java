package cn.itcast.travel.dao;
import cn.itcast.travel.domain.Routes;

import java.util.List;

public interface RoutDaoInter {
    //查到该条数据旅游线路的内容   需要线路id去线路表查
    //查到该条旅游线路对应的图片   需要线路id去图片表查
    //查到该条旅游线路对应的商家的信息   需要商家线路中id去商家表查
    List<Routes> getRout(String id);
}
