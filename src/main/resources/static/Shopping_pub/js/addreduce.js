/**
 * Created by dzw on 2019/1/10.
 */
$(function () {
    $(".decrease").click(function () {
        var pid=$(this).children("span").attr("value")
        alert(pid);
        var is= $(this).parent()
        $.post("/ShopController/ShopAddProductNum",{"pid":pid},function () {
            $(is).html("")
            $(is).html("<botten class=\"track decrease\"  name=\"sp_cart_mycart_decrease\"><span hidden th:value=\"${list}\"></span>减少</botten>\n<input name=\"qty\" type=\"text\" class=\"modify-product-qty\"  th:value=\"${list.getNumber()}\" maxlength=\"3\">\n<botten class=\"increase track\"  name=\"sp_cart_mycart_increase\">增加</botten>")

        });
    })


});