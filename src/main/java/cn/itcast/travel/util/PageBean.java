package cn.itcast.travel.util;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean<T> {
    //分析    prePageNum;//上一页	  nextPageNum;//下一页	 totalPageNum; //总页数，计算出来的			currentPageNum;//当前页数，由用户指定


    //基本属性
    private int currentPageNum;//当前页数，由用户指定				*
    private int pageSize;//每页显示的条数，固定的				*
    private int totalRecords;//总记录条数，数据库查出来的			*
    private int totalPageNum;//总页数，计算出来的					*
    private int startIndex;//每页开始记录的索引，计算出来的	limit后的第1个参数		    *
    private int prePageNum;//上一页							    *
    private int nextPageNum;//下一页							    *

    private List<T> list;//已经分好页的结果集,该list中只有10条记录



    //扩展属性
    //一共每页显示9个页码按钮
    private int startPage;//开始页码
    private int endPage;//结束页码

    //完善属性
    private String url;

                      //当前页数  总条数  每页显示条数
    public PageBean(int currentPageNum,int totalRecords,int pageSize) {
            this.currentPageNum = currentPageNum;//当前页数
            this.totalRecords=totalRecords;//当前总条数
            this.pageSize=pageSize;//每页显示的条数

            //24  4  6
            //25  4  7
            /*if(totalRecords%pageSize==0){
                totalPageNum=totalRecords/pageSize;
            }else{
                totalPageNum=totalRecords/pageSize+1;
            }*/
            //总页数
            totalPageNum=(totalRecords%pageSize==0)?(totalRecords/pageSize):(totalRecords/pageSize+1);
            startIndex=(currentPageNum-1)*pageSize;
        System.out.println(startIndex);
        System.out.println(currentPageNum);
        System.out.println(pageSize);
            if(currentPageNum==1){
                prePageNum=1;
            }else{
                prePageNum=currentPageNum-1;
            }

            if(currentPageNum==totalPageNum){
                nextPageNum=totalPageNum;
            }else{
                nextPageNum=currentPageNum+1;
            }

            ///计算起始和结束页码
        startPage = currentPageNum - 4;
        endPage = currentPageNum + 4;
        //看看总页数够不够9页
        if(totalPageNum>9){
            //超过了9页
            if(startPage < 1){
                startPage = 1;
                endPage = startPage+8;
            }
            if(endPage>totalPageNum){
                endPage = totalPageNum;
                startPage = endPage-8;
            }
        }else{
            //不够9页
            startPage = 1;
            endPage = totalPageNum;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageBean{");
        sb.append("currentPageNum=").append(currentPageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", totalRecords=").append(totalRecords);
        sb.append(", totalPageNum=").append(totalPageNum);
        sb.append(", startIndex=").append(startIndex);
        sb.append(", prePageNum=").append(prePageNum);
        sb.append(", nextPageNum=").append(nextPageNum);
        sb.append(", list=").append(list);
        sb.append(", startPage=").append(startPage);
        sb.append(", endPage=").append(endPage);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }


    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPrePageNum() {
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
