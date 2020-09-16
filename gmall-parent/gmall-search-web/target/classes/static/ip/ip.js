/*获取当前ip申请，返回对应ip地址*/
$(function ip() {
  //获取当前页面所有的href
    $('a').each(function () {
        var ipHref=$(this).prop("href");
        if(ipHref=='passprot'){
            $(this).prop("href","http://192.168.0.113:8086/index");
        }
    });

})