function toTrade(){
        window.location.href="http://192.168.0.113:8085/toTrade";
    }
function toItem(skuId) {
    window.location.href="http://item.gmall.com:8084/"+skuId+".html";
}

function login(){
    var s = encodeURIComponent("http://cart.gmall.com:8087/cartList");
    window.location.href="http://passport.atguigu.com/index?originUrl="+s;
}

function checkSku(chkbox){
    var skuId= $(chkbox).attr("value");
    // alert(skuId);
    var checked=$(chkbox).prop("checked");
    //获取数量用于更新
    var quantity = $(chkbox).parent().parent().children().find("span#span-quantitys").text();
    console.log("====="+quantity);
    //alert(quantity);
    var isCheckedFlag="0";
    if(checked){
        isCheckedFlag="1";
    }
    var param="isChecked="+isCheckedFlag+"&"+"skuId="+skuId+"&"+"quantity="+quantity;
    $.post("checkCart",param,function (data) {
        // 服务会返回一个inner内嵌页面给ajax，我们返回的新的页面刷新替换掉原来的老的页面
        //alert(data);
        $("#cartListInner").html(data);
        //sumSumPrice();
    });

}

//封装总价钱函数
//     function sumSumPrice(){
//         var zzj=0;
//         $(".check").each(function () {
//             if($(this).prop("checked")){
//                 var zj = $(this).parents("ol").find(".zj").html().substring(1);
//                 zzj=zzj+parseFloat(zj);
//             }
//             $(".fnt").html("￥"+zzj+".00")
//         });
//     }

/*    //购物车结算固定定位
    $(document).scroll(function(){
        if($(window).scrollTop()>50){
            $(".fix").hide();
        }else{
            $(".fix").show();
        }
    })*/


//购物车顶端tab
$(".One_Topleft span:last-child").mouseover(function(){
    $(".One_Topleft span:first-child").css({"color":"black","border-bottom":"none"})
    $(this).css({"cursor":"pointer","color":"#E4393C","border-bottom":"2px solid red"})
}).mouseout(function(){
    $(this).css({"color":"black","border-bottom":"none"});
    $(".One_Topleft span:first-child").css({"cursor":"pointer","color":"#E4393C","border-bottom":"2px solid red"})
})
//立即登录
$(".one_search_load input:button").click(function(){
    $(".One_mb").show();
    $(".One_DengLu").show();
})
//去结算
$(".One_ShopFootBuy>div:last-child button").mouseover(function(){
    $(this).css("cursor","pointer");
})
$(".One_ShopFootBuy>div:last-child button").click(function(){
    $(".One_mb").show();
    $(".One_DengLu").show();
})
//buyNum
$(".One_ShopFootBuy>div:nth-child(2)").mouseover(function(){
    $(this).css("cursor","pointer")
})
$(".One_ShopFootBuy>div:nth-child(2)").click(function(){
    $(this).children("ol").toggle();
    $(this).children("ul").toggle();
    var dis=$(".One_ShopFootBuy>div:nth-child(2) ol").css("display");
    if(dis=="none"){
        $(".One_ShopFootBuy>div:nth-child(2) img").css("transform","rotateX(0)")
    }else if(dis=="block"){
        $(".One_ShopFootBuy>div:nth-child(2) img").css("transform","rotateX(180deg)")
    }
})
//右侧固定定位
$(".One_RightFix ul>li:not(:first-child)").mouseover(function(){
    $(this).css({"cursor":"pointer","background":"#C91423"})
    $(this).children("ol").stop().animate({"left":"-75px"},200)
}).mouseout(function(){
    $(this).css("background","#7A6E6E");
    $(this).children("ol").stop().animate({"left":"75px"},200)
})
//右侧底部固定定位
$(".One_RightbtmFix ul>li").mouseover(function(){
    $(this).css({"cursor":"pointer","background":"#C91423"})
    $(this).children("ol").stop().animate({"left":"-55px"},200)
}).mouseout(function(){
    $(this).css("background","#7A6E6E");
    $(this).children("ol").stop().animate({"left":"55px"},200)
})
//登录弹框tab切换
$(".One_DengLu>div:nth-child(3) ul li").mouseover(function(){
    $(this).css("cursor","pointer")
})
$(".One_DengLu>div:nth-child(3) ul li").click(function(){
    var i=$(this).index();
    $(this).css({"color":"#E64346","font-weight":"bold"})
        .siblings("li").css({"color":"black","font-weight":"normal"})
    $(".One_DengLu>div:nth-child(3) ol li").eq(i).show().siblings().hide()
})
//优惠券
$(".One_ShopBottom>div:nth-child(2) img").click(function(){
    $(".One_mb").show();
    $(".One_DengLu").show();
})
//配送地址hover效果
$(".One_Topright span:last-child").mouseover(function(){
    $(this).children(".One_Local").css("display","block")
}).mouseout(function(){
    $(".One_Local>ul>li").eq(0).children("ol").css("display","block")
    $(this).children(".One_Local").css("display","none")
})

$(".One_Local>ul>li").eq(0).children("ol").css("display","block")
$(".One_Local>ul>li").mouseover(function(){
    var i=$(this).index();
    $(this).css("cursor","pointer");
    $(this).children("ol").css("display","block")
}).mouseout(function(){
    $(".One_Local>ul>li").eq(0).children("ol").css("display","block")
    $(this).children("ol").css("display","none")
})

$(".One_Local>ul>li>ol li").mouseover(function(){
    $(this).css({"cursor":"pointer","color":"#e64346"})
}).mouseout(function(){
    $(this).css("color","#005AA0")
})

$(".One_Local>ul>li>ol li").click(function(){
    $(this).parent().parent().children("label").html($(this).html())
})
//购物车全选反选
$(".One_ShopTop ul li:first-child .allCheck").click(function(){
    if($(".One_ShopTop ul li:first-child .allCheck").is(":checked")){
        // $(".check").each(function(index){
        $(".check").prop("checked",true);
        $(".check").parent().parent().parent().css("background","#fff4e8");
        $(".One_ShopBottom>div:first-child span:first-child .allCheck").prop("checked",true)
        // })
    }else{
        // $(".check").each(function(){
        $(".check").parent().parent().parent().css("background","none");
        $(".check").prop("checked",false);
        $(".One_ShopBottom>div:first-child span:first-child .allCheck").prop("checked",false)
        // })
    }
})
$(".One_ShopFootBuy>div:first-child ul li:first-child .allCheck").click(function(){
    if($(".One_ShopFootBuy>div:first-child ul li:first-child .allCheck").is(":checked")){
        $(".check").prop("checked",true);
        $(".check").parent().parent().parent().css("background","#fff4e8");
        $(".One_ShopBottom>div:first-child span:first-child .allCheck").prop("checked",true)
    }else{
        $(".check").parent().parent().parent().css("background","none");
        $(".check").prop("checked",false);
        $(".One_ShopBottom>div:first-child span:first-child .allCheck").prop("checked",false)
    }
})
$(".One_ShopBottom>div:first-child span:first-child .allCheck").click(function(){
    if($(".One_ShopBottom>div:first-child span:first-child .allCheck").is(":checked")){
        $(".check").prop("checked",true);
        $(".check").parent().parent().parent().css("background","#fff4e8");
    }else{
        $(".check").parent().parent().parent().css("background","none");
        $(".check").prop("checked",false);
    }
})
//如果子选择框没有选中则父选框取消全选
$(".check").click(function(){
    $(".check").each(function(){
        if($(this).prop("checked")==true){
            $(".allCheck").prop("checked",false);
        }
    })
})
//子选择框全部选中复选框也选中
$(".check").click(function(){
    if($(".check[class='check']:checked").length==$(".check[class='check']").length){
        $(".allCheck").prop("checked",true);
    }else{
        $(".allCheck").prop("checked",false)
    }
})
//删除已选的商品
$(".One_ShopFootBuy>div:first-child ul li:nth-child(2)").click(function(){
    // $(".check").each(function(){
    if($(".check").is(":checked")==false){
        $(".One_mb").show();
        $(".One_moveGzIfNull").show();
    }else{
        $(".One_mb").show();     //蒙版显示
        $(".One_isDel").show();  //删除弹框显示
        $(".One_moveGzIfNull").hide();
    }
    //移除结账固定定位
    // if($(".check").length==1){
    // 	$(".fix").remove();
    // }
    // })
    //var that=$(this);
    //确定删除
    /*      $(".One_isDel>div:last-child button:first-child").click(function(){
              $(".One_mb").hide();    //蒙版隐藏
              $(".One_isDel").hide(); //删除弹框隐藏
              $(".check:checked").parent().parent().parent().parent().remove();  //删除选中商品
              if($(".fix").offset().top>$(".fix1").offset().top){
                  $(".fix").hide();
              }
              if($(".check").length==0){
                  $(".allCheck").prop("checked",false);
                  $(".sumNum").html("0");
                  $(".fnt").html("￥ 0.00");
              }
          })*/
})
//确定删除弹框移入我的关注
$(".One_isDel>div:last-child button:last-child").click(function(){
    $(".One_isDel").hide();
    $(".One_mb").show();
    $(".One_DengLu").show();
})
//移到我的关注
$(".One_ShopFootBuy>div:first-child ul li:nth-child(3)").click(function(){
    $(".check").each(function(index){
        if($(this).is(":checked")==false){
            $(".One_mb").show();
            $(".One_moveGzIfNull").show();
        }else{
            $(".One_mb").show();
            $(".One_moveMyGz").show();
            $(".One_moveGzIfNull").hide();
        }
    })
})
//点击×号关闭
$(".One_moveGzIfNull>p span:last-child img").click(function(){
    $(".One_mb").hide();
    $(".One_moveGzIfNull").hide();
})
$(".One_moveMyGz>p span:last-child img").click(function(){
    $(".One_mb").hide();
    $(".One_moveMyGz").hide();
    $(".One_moveGzIfNull").hide();
})
$(".One_clearShop>p span:last-child img").click(function(){
    $(".One_mb").hide();
    $(".One_clearShop").hide();
})
//移到我的关注取消按钮点击关闭
$(".One_moveMyGz>div:last-child button:last-child").click(function(){
    $(".One_mb").hide();
    $(".One_moveMyGz").hide();
})
//移到我的关注确定按钮点击登录弹框弹出
$(".One_moveMyGz>div:last-child button:first-child").click(function(){
    $(".One_moveMyGz").hide();
    $(".One_mb").show();
    $(".One_DengLu").show();
})

$(".One_DengLu>p:first-child span:last-child img").click(function(){
    $(".One_mb").hide();
    $(".One_DengLu").hide();
})
//清除下柜商品
$(".One_ShopFootBuy>div:first-child ul li:nth-child(4)").click(function(){
    $(".One_mb").show();
    $(".One_clearShop").show()
})
//购物车+ -
//鼠标移入变小手
$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(4) p:first-child span").mouseover(function(){
    $(this).css("cursor","pointer")
})
//+
$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(4) p:first-child span:last-child").click(function(){

    var add=$(this).prev("span").html();
    add++;
    $(this).prev("span").html(add);
    //总价
    var dj=$(this).parent().parent().prev().children(".dj").html().substring(1);
    var sl=$(this).prev("span").html();
    $(this).parent().parent().parent().children("li:nth-child(5)").children(".zj").html("￥"+dj*sl+".00")
    sumSumPrice();
    //新增需求，当点击加减号的时候获取选中input元素，调用checkSku更新数据
    var checkObject=$(this).parent().parent().siblings().find(".check");
    console(checkObject);
    checkSku(checkObject);

})
//-
$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(4) p:first-child span:first-child").click(function(){
    var jian=$(this).next("span").html();
    jian--;
    if(jian<=0){
        jian=0;
    }
    $(this).next("span").html(jian);
    //总价
    var dj=$(this).parent().parent().prev().children(".dj").html().substring(1);
    var sl=$(this).next("span").html();
    $(this).parent().parent().parent().children("li:nth-child(5)").children(".zj").html("￥"+dj*sl+".00")
    sumSumPrice();
    //新增需求，当点击加减号的时候获取选中input元素，调用checkSku更新数据
    var checkObject=$(this).parent().parent().siblings().find(".check");
    checkSku(checkObject);

})
//选中当前商品背景变色
$(".check").each(function(index){
    $(".check").eq(index).click(function(){
        if($(this).is(":checked")){
            $(this).parent().parent().parent().css("background","#fff4e8");
            $(this).parent().parent().parent().parent().children("div:last-child").css("background","#fff4e8")
        }else{
            $(this).parent().parent().parent().parent().children("div:last-child").css("background","none")
            $(this).parent().parent().parent().css("background","none")
        }
    })
})
//商品删除鼠标移入变色
$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(6) p").mouseover(function(){
    $(this).css({"cursor":"pointer","color":"#e64346"});
}).mouseout(function(){
    $(this).css({"cursor":"pointer","color":"gray"});
})
$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(6) p:nth-child(2)").click(function(){
    $(".One_mb").show();
    $(".One_moveMyGz").show();
})
$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(6) p:last-child").click(function(){
    $(".One_mb").show();
    $(".One_DengLu").show();
    var skuId= $(this).attr("value");
$(".One_isDel>div:last-child button:first-child").attr("skuId",skuId);
var skuIds= $(".One_isDel>div:last-child button:first-child").attr("skuId");
//alert(skuIds);
})
//点击删除
//点击删除出现弹框
$(".One_isDel>p span:last-child img").click(function(){
    $(".One_mb").hide();
    $(".One_isDel").hide();

})
//点击按钮删除
 $(".One_isDel>div:last-child button:first-child").click(function(){
    var skuId= $(this).attr("skuId");
    alert("要删除的sku:"+skuId);
    //确定删除

 })

$(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(6) p:first-child").click(function(){
    $(".One_mb").show();
    $(".One_isDel").show();
    //alert("hahahha");
    var that=$(this);
    //确定删除
    /*        $(".One_isDel>div:last-child button:first-child").click(function(){
                $(".One_mb").hide();
                $(".One_isDel").hide();
                that.parent().parent().parent().parent().remove();
                // 移除结账固定定位
                // if($(".check").length==1){
                // 	$(".fix").remove();
                // }
                if($(".fix").offset().top>$(".fix1").offset().top){
                    $(".fix").hide();
                }
                if($(".check").length==0){
                    $(".allCheck").prop("checked",false);
                    $(".sumNum").html("0");
                    $(".fnt").html("￥ 0.00");
                }
            })*/
})
//点击按钮删除
// $(".One_isDel>p img").click(function(){
//     $(".One_mb").hide();
//     $(".One_isDel").hide();
//     //确定删除

//})
/*    //页面滚动删除去结算固定定位隐藏
    $(document).scroll(function(){
        if($(".fix").offset().top>$(".fix1").offset().top){
            $(".fix").hide();
        }
    })*/
$(".One_isDel>div:last-child button").mouseover(function(){
    $(this).css("cursor","pointer");
})

$(".One_ShopFootBuy>div:first-child ul li:not(:first-child)").mouseover(function(){
    $(this).css({"cursor":"pointer","color":"#e64346"})
}).mouseout(function(){
    $(this).css("color","black")
})

//封装总价钱函数fnt
function sumSumPrice(){
    console.log("计算总价");
    var zzj=0;
    $(".check").each(function () {


        if($(this).prop("checked")){
            console.log("check!!"+ $(this).parents("ol").find(".zj").html());
            var zj = $(this).parents("ol").find(".zj").html().substring(1);
            console.log(" 价格："+zj);
            zzj=zzj+parseFloat(zj);
        }
        $(".fnt").html("￥"+zzj+".00")
    })

    /*        $(".One_ShopCon ul li>div:nth-child(2) ol li:nth-child(5) .zj").each(function(){
                kong+=parseFloat($(this).html().substring(1))
            })
            $(".fnt").html("￥"+kong+".00")*/
}
// 封装总数量函数
function sumSumNum(){
    var kong=0;
    $(".One_ShopCon ul li>div:nth-child(2) ol>li:nth-child(4) p:first-child span:nth-child(2)").each(function(){
        kong+=parseFloat($(this).html())
        alert(kong);
    })
    $(".sumNum").html(kong);
}

$(".One_ShopCon ul li>div:nth-child(2)>ol>li:nth-child(2)>dd").mouseover(function(){
    $(this).css({"cursor":"pointer","color":"#e64346"})
}).mouseout(function(){
    $(this).css("color","black")
})
//更多促销下拉
$(".One_ShopCon ul li>div:nth-child(2) ol li:nth-child(3) p:nth-child(2)").click(function(){
    $(this).parent().children(".nmbd").slideToggle(300);
    var dis=$(this).parent().children(".nmbd").css("display");
    console.log(dis);
    // if(dis=="block"){
    // 	$(".hahaha").css("transform","rotateX(-180deg)")
    // }else{
    // 	$(".hahaha").css("transform","rotateX(360deg)")
    // }
})
$(".nmbd dd:last-child button:first-child").click(function(){
    $(this).parent().parent().slideUp(100)
})
$(".nmbd dd:last-child button:last-child").click(function(){
    $(this).parent().parent().slideUp(100)
})










//active
$(".one_main_div1 .one_main_ul>li").mouseover(function(){
    $(".one_main_div1 .one_main_ul>li").removeClass("active");
    $(this).addClass("active");
})
//选项卡
$(".one_main_div1 .one_main_ul li").mouseover(function() {
    $(".one_main_div1_1").eq($(this).index()).stop(true).show().siblings().stop(true).hide()
})

$(function() {
    //添加总金额
    sumSumPrice();
    //声明变量记录索引
    var index = 0;
    //点击右边按钮
    //忽略重复点击开关
    var toggle = true
    $(".arrow-right").click(function() {

        if (toggle == false) {
            return
        }
        toggle = false
        index++;
        if (index > $('.fade li').length - 1) {
            index = 0;
        }

    });

    //点击左边按钮
    $(".arrow-left").click(function() {
        if (toggle == false) {
            return
        }
        toggle = false
        index--;
        if (index < 0) {
            index = $('.fade li').length - 1;
        }
        $('.pagination ul li').eq(index).addClass('active').siblings().removeClass('active')
        $(".slider>ul>li").eq(index).fadeIn(500, function() {
            toggle = true
        }).siblings("li").fadeOut(500);
    });
    //点击分页器
    $('.pagination ul li').mouseover(function() {
        var paging = $(this).index()
        //变颜色
        $(this).addClass('active').siblings().removeClass('active')
        //与图片关联
        $(".slider>ul>li").eq(paging).fadeIn(1000).siblings("li").fadeOut(1000);

    })
});
$(".fade li>div a").mouseover(function(){
    $(this).children("p").children("img").attr("src","img/one_mian_gwc2.png")
}).mouseout(function(){
    $(this).children("p").children("img").attr("src","img/one_mian_gwc1.png")
})