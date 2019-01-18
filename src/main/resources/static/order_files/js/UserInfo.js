/**
 * Created by dzw on 2019/1/17.
 */
$(function () {
    $(".save").click(function () {
        if (confirm("您确认要删除？")){
            var TrueName=$("#TrueName").attr("value")
            var sex=$(".sex:checked").attr("value")
            var bYear=$("#bYear").attr("value")
            var bMonth=$("#bMonth").attr("value")
            var bDay=$("#bDay").attr("value")
            var Email=$("#Email").attr("value")
            var Province=$("#Province").attr("value")
            var City=$("#City").attr("value")
            var Area=$("#Area").attr("value")
            var Address=$("#Address").attr("value")
            var PostalCode=$("#PostalCode").attr("value")
            var Mobile=$("#Mobile").attr("value")
            var Phone=$("#Phone").attr("value")

            location.href="/updataMyData?TrueName="+TrueName+"&sex="+sex+"&bYear="+bYear+"&bMonth="+bMonth+"&bDay="+bDay+"&Email="+Email+"&Province="+Province+"&City="+City+"&Area="+Area+"&Address="+Address+"&Mobile="+Mobile+"&Phone="+Phone
            alert(Mobile)
        }



    })
});