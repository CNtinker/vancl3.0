/**
 * Created by dzw on 2019/1/10.
 */
$(function () {
    /*减少购物车商品数量的点击事件*/
    $(".decrease").click(function () {
        var pid=$(this).children("span:eq(0)").attr("value")
        var color=$(this).children("span:eq(1)").attr("value")
        var size=$(this).children("span:eq(2)").attr("value")

        var is= $(this).parent()
        $.post("/ShopController/ShopReduceProductNum",{"pid":pid,"color":color,"size":size},function (json) {
            for(var i=0;i<json.length;i++){
                if(json.length==4){

                    if(i==0){
                        $(is).children("input").attr("value",json[i])
                    }else if(i==1){
                        $(is).next("td").next("td").html("￥"+json[i])
                    }
                    else if(i==2){
                        $("#listcarnum").html(json[i])
                    }
                    else if(i==3){
                        $("#listcarsumMoney").html(json[i])
                    }
                }else{
                    $(is).parent().html("")
                    if(i==0){
                        $("#listcarnum").html(json[i])
                    }else if(i==1){
                        $("#listcarsumMoney").html(json[i])
                    }

                }
            }
        },"json")


    });
    /*增加购物车商品数量的点击事件*/
    $(".increase").click(function () {
        var pid=$(this).children("span:eq(0)").attr("value")
        var color=$(this).children("span:eq(1)").attr("value")
        var size=$(this).children("span:eq(2)").attr("value")

        var is= $(this).parent()

        $.post("/ShopController/ShopAddProductNum",{"pid":pid,"color":color,"size":size},function (json) {
            for(var i=0;i<json.length;i++){
                    if(i==0){
                        $(is).children("input").attr("value",json[i])
                    }else if(i==1){
                        $(is).next("td").next("td").html("￥"+json[i])
                    }
                    else if(i==2){
                        $("#listcarsumMoney").html(json[i])
                    }
            }
        },"json")

    });
    /*删除购物车单个商品的方法*/
    $(".del").click(function () {
        var pid=$(this).children("span:eq(0)").attr("value")
        var color=$(this).children("span:eq(1)").attr("value")
        var size=$(this).children("span:eq(2)").attr("value")
        confirm("是否删除？")
        var is= $(this).parent().parent()
        $.post("/ShopController/ShopDelProductNum",{"pid":pid,"color":color,"size":size},function (json) {
            for(var i=0;i<json.length;i++){
                $(is).html("");
                if(i==0){
                    $("#listcarnum").html(json[i])
                }else if(i==1){
                    $("#listcarsumMoney").html(json[i])
                }
            }
        },"json")

    })


    $()




});