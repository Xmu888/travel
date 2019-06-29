package cn.itcast.travel.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 旅游线路商品实体类
 */
public class Routes implements Serializable {

    private int rid;//线路id
    private int rgid;//图片id
    private String rname;//路线名称
    private String price;//价格
    private String routeIntroduce;//介绍文字
    private int rflag;//是否下架
    private String rdata;//上架时间
    private String isThemeTour;//是否主题旅游，必输，0代表不是，1代表是
    private int count;//收藏数量
    private String rimage;//缩略图
    private int sid;//所属商家
    private String sourceId;//抓取数据的来源id
    private int cid;//所属分类，必输
    private String cname;//类型名称
    private String bigPic;//大图片
    private String smallPic;//小图片
    private String sname;//旅行社名称
    private String consphone;//旅行社电话
    private String address;//旅行社地址

    //--------------------------------------------

    private Category category;//所属分类
    private Seller seller;//所属商家
    private List<RouteImg> routeImgList;//商品详情图片列表
    public Routes() {
    }

    public Routes(int rid, int rgid, String rname, String price, String routeIntroduce, int rflag, String rdata, String isThemeTour, int count, String rimage, int sid, String sourceId, int cid, String cname, String bigPic, String smallPic, String sname, String consphone, String address, Category category, Seller seller, List<RouteImg> routeImgList) {
        this.rid = rid;
        this.rgid = rgid;
        this.rname = rname;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.rflag = rflag;
        this.rdata = rdata;
        this.isThemeTour = isThemeTour;
        this.count = count;
        this.rimage = rimage;
        this.sid = sid;
        this.sourceId = sourceId;
        this.cid = cid;
        this.cname = cname;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
        this.category = category;
        this.seller = seller;
        this.routeImgList = routeImgList;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
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

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public int getRflag() {
        return rflag;
    }

    public void setRflag(int rflag) {
        this.rflag = rflag;
    }

    public String getRdata() {
        return rdata;
    }

    public void setRdata(String rdata) {
        this.rdata = rdata;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "rid=" + rid +
                ", rgid=" + rgid +
                ", rname='" + rname + '\'' +
                ", price='" + price + '\'' +
                ", routeIntroduce='" + routeIntroduce + '\'' +
                ", rflag=" + rflag +
                ", rdata='" + rdata + '\'' +
                ", isThemeTour='" + isThemeTour + '\'' +
                ", count=" + count +
                ", rimage='" + rimage + '\'' +
                ", sid=" + sid +
                ", sourceId='" + sourceId + '\'' +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", bigPic='" + bigPic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                ", sname='" + sname + '\'' +
                ", consphone='" + consphone + '\'' +
                ", address='" + address + '\'' +
                ", category=" + category +
                ", seller=" + seller +
                ", routeImgList=" + routeImgList +
                '}';
    }
}
