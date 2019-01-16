/**
 * Created by dzw on 2019/1/15.
 */
$(function () {
    $(".delivery-time-items div").click(function () {
        $(this).attr("class","choose-delivery-selected").siblings("div").attr("class","");
    })

    $("#add_addr").click(function () {
        $(".shade").css("display","block")
        $(".addr-form").css("display","block")
    })
});
