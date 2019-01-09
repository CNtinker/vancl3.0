/**
 * Created by dzw on 2019/1/8.
 */
$(function(){
    $(".selColor ul li").click(function () {
        $(this).attr("id","onlickColor").children("div").after("<span class=colorHoverok></span>");
        $(this).siblings("li").attr("id","").children("span").after().remove()

    })



    $(".selSize ul li").click(function () {
        $(this).attr("id","onlickSelSize").children("p").after("<span></span>");
        $(this).siblings("li").attr("id","").children("span").after().remove()
    })

    /*购物车按钮脚本*/
    var num_jia = document.getElementById("num-jia");
    var num_jian = document.getElementById("num-jian");
    var input_num = document.getElementById("input-num");


    num_jia.onclick = function() {

        input_num.value = parseInt(input_num.value) + 1;
    }

    num_jian.onclick = function() {

        if(input_num.value <= 1) {
            input_num.value = 1;
        } else {

            input_num.value = parseInt(input_num.value) - 1;
        }

    }
});
