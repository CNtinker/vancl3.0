/**
 * Created by dzw on 2019/1/10.
 */
$(function () {
    $(".decrease").click(function () {
        var pid=$(this).children("span:eq(0)").attr("value")
        var color=$(this).children("span:eq(1)").attr("value")
        var size=$(this).children("span:eq(2)").attr("value")

        var is= $(this).parent()
        $.post("/ShopController/ShopAddProductNum",{"pid":pid,"color":color,"size":size},function (json) {
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
        /*function  districtChange(){
         var did=$("#district>option:selected").val();
         $.post("./StreetServlet",{"did":did},function(data){
         for(var i=0;i<data.length;i++){
         if(data[i].sid=="${map['sid']}"){
         html+="<option value='"+data[i].sid+"'selected>"+data[i].sname+"</option>";
         }else{
         html+="<option value='"+data[i].sid+"'>"+data[i].sname+"</option>";
         }
         }
         $("#street").html(html);
         },"json");
         }*/

    });
});