// var lsr = localStorage;
a = function(id,pageNum){
    los.removeItem("serchName");//清空搜索关键字缓存
    los.removeItem("pageNum");//清空搜索分页缓存
    $("#BigUl").html("");
    $(".pagination").html("");
    if(id==undefined||pageNum==undefined){
        id=los.res;
        pageNum=1;
    }
    los.setPageNum = pageNum;
    $.post("/travel/Page/GetPageData",{id:id,pageNum:pageNum},function(res){
        console.log(res);
        $(".search_input").val("");//清空地址栏位置信息
        $.each(res.list,function(i,data){
            var html = '<li>\n' +
                '                            <div class="img"><img src="'+data.rimage+'" alt=""></div>\n' +
                '                            <div class="text1">\n' +
                '                                <p>'+data.rname+'</p>\n' +
                '                                <br>\n' +
                '                                <p>'+data.routeIntroduce+'</p>\n' +
                '                            </div>\n' +
                '                            <div class="price">\n' +
                '                                <p class="price_num">\n' +
                '                                    <span>¥</span>\n' +
                '                                    <span>'+data.price+'</span>\n' +
                '                                    <span>起</span>\n' +
                '                                </p>\n' +
                '                                <p><a href="javascript:serchImg('+data.rid+","+data.sid+');">查看详情</a></p>\n' +
                '                            </div>\n' +
                '                        </li>';
            $("#BigUl").append(html);
        });
        $(".page_num_inf span:eq(0)").html(res.totalPageNum);
        $(".page_num_inf span:eq(1)").html(res.totalRecords);
        $(".pagination").append('<li><a href="javascript:a('+res.list[0].cid+','+res.prePageNum+');" aria-label="Previous"><span aria-hidden="true">«</span></a></li>');
        for(var i = res.startPage;i<=res.endPage;i++){
            if(i==res.currentPageNum){
                $(".pagination").append('<li class="active"><a href="javascript:a('+res.list[0].cid+','+i+');" >'+i+'</a></li>');
            }else{
                $(".pagination").append('<li><a href="javascript:a('+res.list[0].cid+','+i+');">'+i+'</a></li>');
            }
        }
        $(".pagination").append('<li><a href="javascript:a('+res.list[0].cid+','+res.nextPageNum+');" aria-label="Next"><span aria-hidden="true">»</span></a></li>');
    },"json");
};
if(null!=los.res){
    a(los.res,los.setPageNum);
}
if(location.href!="http://localhost/travel/route_list.html"){
    los.removeItem("setPageNum");
    los.removeItem("res");
}
serchImg = function(cid,sid){//线路id   商家id   查询该条内容
    console.log(cid+"---"+sid);
    los.setItem("cid",cid);
    location.href = "http://localhost/travel/route_detail.html";
}





















