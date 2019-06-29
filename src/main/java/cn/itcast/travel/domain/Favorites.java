package cn.itcast.travel.domain;

import java.io.Serializable;

/**
 * 收藏实体类
 */
public class Favorites implements Serializable {
    private String rid;//收藏线路的id
    private String rname;//收藏线路的名字
    private String price;//对应线路的价格
    private String rimage;//收藏线路的图片地址
    private String count;//被收藏次数
    public Favorites() {
    }

    public Favorites(String rid, String rname, String price, String rimage, String count) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.rimage = rimage;
        this.count = count;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "rid='" + rid + '\'' +
                ", rname='" + rname + '\'' +
                ", price='" + price + '\'' +
                ", rimage='" + rimage + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
