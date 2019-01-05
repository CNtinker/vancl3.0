/**
 * Created by dzw on 2019/1/3.
 */

$(function(){
    /*分页下拉框脚本*/
    $("#index_category_list li").mouseover(function(){
        $(this).children(".subNav").slideDown("fast")
    });
    $("#index_category_list li").mouseleave(function(){
        $(this).children(".subNav").slideUp("fast")
    });

    /*广告轮播脚本*/
    /*指示器点击事件*/
    var list=$('.rslides1_tabs li').length;
    var timer='';
    var num=0;
    $(".rslides1_tabs li").click(function () {
        $(this).addClass("rslides_here").siblings("li").removeClass("rslides_here")
        num=$(this).index()
        $("#slider li").eq(num).addClass("rslides1_on").siblings("li").removeClass("rslides1_on")
        $("#slider li").eq(num).css({"float":"left","position":"relative","opacity": "1","z-index": "2"}).siblings("li")
            .removeClass({"float":"left","position":"relative","opacity": "1","z-index": "2"})
            .css({"float": "none", "position": "absolute", "opacity": "0", "z-index": "1"})
    });
    /*自动播放*/


        timer=setInterval(function () {
            num++;
            if(num>parseFloat(list)-1){
                num=0;
                $(".rslides1_tabs li").eq(num).addClass("rslides_here").siblings("li").removeClass("rslides_here")
                $("#slider li").eq(num).addClass("rslides1_on").siblings("li").removeClass("rslides1_on")
                $("#slider li").eq(num).css({"float":"left","position":"relative","opacity": "1","z-index": "2"}).siblings("li")
                    .removeClass({"float":"left","position":"relative","opacity": "1","z-index": "2"})
                    .css({"float": "none", "position": "absolute", "opacity": "0", "z-index": "1"})
            }else{
                $(".rslides1_tabs li").eq(num).addClass("rslides_here").siblings("li").removeClass("rslides_here")
                $("#slider li").eq(num).addClass("rslides1_on").siblings("li").removeClass("rslides1_on")
                $("#slider li").eq(num).css({"float":"left","position":"relative","opacity": "1","z-index": "2"}).siblings("li")
                    .removeClass({"float":"left","position":"relative","opacity": "1","z-index": "2"})
                    .css({"float": "none", "position": "absolute", "opacity": "0", "z-index": "1"})
            }
    },2000);

});


/*
$(function () {
    $("#addOption").click(function () {
        $("#voteoptions").append("<P><INPUT name=\"options\" class=\"input-text\" type=\"text\"></P>");
    });

    $("input[name='options']").live("blur",function () {
        var $options =  $("input[name='options']");
        for(var i=0;i<$options.length;i++){
            for(var j=0;j<$options.length;j++){
                if(i!=j){
                    if($options.eq(i).val()==$options.eq(j).val()){
                        $options.eq(i).css("border-color","red");
                        $options.eq(i).siblings().css("border-color","#79a4cf");
                        $options.eq(j).css("border-color","red");
                        break;
                    }else{
                        $options.eq(i).css("border-color","#79a4cf");
                    }
                }
            }
        }
    });
});*/
