/**
 * Created by dzw on 2019/1/9.
 */
$(function () {
    $("#addToShoppingCar").click(function () {
        var color = $("#onlickColor").attr("title");
        var size = $("#onlickSelSize p").html();
        var Pronum = $("#input-num").val();
        alert("/addToShoppingCar?color="+color+"&size="+size+"&num="+Pronum);
        location.href="/addToShoppingCar?color="+color+"&size="+size+"&Pronum="+Pronum;

        })
});