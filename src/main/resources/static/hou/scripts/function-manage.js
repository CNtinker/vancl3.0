// JavaScript Document



function Delete()
{
        confirm("确定要删除吗？")
       $(".del").click(function () {
           var id=$("#cc_id").html();
           alert(id);
       })

        $.ajax({
            type: "POST",
            url: "delProductClass",
            data: {"cc_id": id},
            dataType: "text",
            success: function (result) {
                if (result=="true") {
                    alert("删除成功");
                } else {
                    alert("删除失败");
                }
            }
        })
    location.href="/productClass"
}








