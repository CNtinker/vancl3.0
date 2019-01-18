﻿function SaveAddressOperation(){}SaveAddressOperation.SaveSaveAddressToDB=function(b,c){var d="";var a="Edit";if(typeof(b)=="undefined"||b==""||b=="-1"){b="-1";a="Add"}if(c=="Inland"){d=SaveAddressOperation.CreatInlandUrl(b,a)}if(c=="Foreign"){d=SaveAddressOperation.CreatForeignUrl(b,a)}$.ajax({type:"POST",dataType:"json",url:"/DeliveryAddress/Internal_SaveAddress",data:d,success:function(f){if(f.Result!="Success"){if(a=="Add"){$.fn.alert("地址添加失败")}else{$.fn.alert("修改地址失败")}}else{var e=$("#currentPageNum").val();if(a=="Add"){ToogleAddAddressDiv();e=1}else{CloseEditDiv()}AddressPagingForEdit(e,c)}}})};SaveAddressOperation.CreatInlandUrl=function(c,a){var k=$("#currentUserID").val();var g=$("#receiverName").val()!="请准确填写真实姓名"?$("#receiverName").val():"";var b=$("#detailAddress").val()!="请填写详细路名及门牌号"?$("#detailAddress").val():"";var l=$("#postalCode").val();var j=$("#phone").val()!="手机和座机至少填写一个"?$("#phone").val():"";var f=$("#mobile").val()!="手机和座机至少填写一个"?$("#mobile").val():"";var i=$("#ProvinceID").val();var e=$("#CityID").val();var d=$("#AreaID").val();$("#InlandProvName").val($("#ProvinceID").find("option:selected").text());var h="userName="+encodeURIComponent(g)+"&address="+encodeURIComponent(b)+"&postCode="+l+"&phone="+j+"&mobile="+f+"&province="+i+"&city="+e+"&area="+d+"&addressID="+c+"&userID="+k+"&actionType="+a+"&addressType=Inland&r="+Math.random();if($("#isDefaultAddress").length>0){if($("#isDefaultAddress").is(":checked")){h+="&isDefaultAddress=true"}else{h+="&isDefaultAddress=false"}}return h};SaveAddressOperation.CreatForeignUrl=function(b,a){var m=$("#currentUserID").val();var h=$("#firstName").val();var i=$("#lastName").val();var c=$("#addressLineOne").val();var d=$("#addressLineTwo").val();var e=$("#city").val();var l=$("#state").val();var n=$("#zip").val();var f=$("#country").find("option:selected").text();var g=$("#country").val();var k=$("#phoneNum").val();$("#InlandProvName").val();var j="firstName="+encodeURIComponent(h)+"&lastName="+encodeURIComponent(i)+"&addressLineOne="+encodeURIComponent(c)+"&addressLineTwo="+encodeURIComponent(d)+"&city="+encodeURIComponent(e)+"&state="+encodeURIComponent(l)+"&zip="+encodeURIComponent(n)+"&country="+f+"&countryId="+g+"&phoneNum="+encodeURIComponent(k)+"&addressID="+b+"&userID="+m+"&actionType="+a+"&addressType=Foreign&r="+Math.random();return j};function ShowEditDiv(c,a,b){ShowEditDiv(c,a,b,false)}function ShowEditDiv(d,a,b,c){$(".AddressDetail").html("");if(a>0&&c==true){$("#editorAdress .editorAdressTitle").html("编辑默认收货地址")}else{$("#editorAdress .editorAdressTitle").html("编辑收货地址")}ShowAddOrEditDiv(a,b);DisplayAddAddressDiv(false);var e=$("#editorAdressPos0").offset().top;var f=$(d).offset().top;var g=-39+f-e-138;$("#editorAdress").css("top",g);$("#editorAdress").show()}function ShowAddOrEditDiv(b,c){var d=$("#modelCount").val();var a="";if(c=="Inland"){$.get("/DeliveryAddress/Internal_AddOrEditAddress?addressID="+b+"&actionType=Edit&modelCount="+d+"&r="+Math.random(),function(e){$("#AddressDetail").html("").append(e);$("#radioClickAddressID").val(b);$("#isEditOrAdd").val("Showed");RegionLinkage.BindArea();RegionLinkage.BindAddressHead()})}}function CloseEditDiv(){$("#editorAdress").hide();$("#editorAdress .AddressDetail").html("")}function ConfirmChoosedAddress(b){var a=0;if(b=="Inland"){a=$("#radioClickAddressID").val()}else{a=$("#radioClickAddressIDForeign").val()}var d=$("#currentUserID").val();var c=true;if($("#isEditOrAdd").val()=="Showed"){c=false}if(b=="Inland"){if(!Check_Consignee()){return false}}else{if(!Check_Consignee_Forgign()){return false}}a=SaveAddressOperation.SaveSaveAddressToDB(a,b)};function ChangeAddressType(c){var b=$("#addressType").val();var a=0;if(b=="Inland"){window.location="/DeliveryAddress/Index"}else{window.location="/DeliveryAddress/Foreign_Index"}}function ChangAddressTypeInEditDiv(b){var c=$("#currentUserID").val();var a=0;if(b=="Inland"){window.location="/DeliveryAddress/Index"}else{window.location="/DeliveryAddress/Foreign_Index"}}function AddressPaging(c,a){var b=$("#radioClickAddressID").val();var d="/DeliveryAddress/Internal_AddressListPaging";$.get(d+"?userID="+$("#currentUserID").val()+"&pageNum="+c+"&choosedID="+b+"&addressType="+a+"&r="+Math.random(),function(e){$("#dizhi_list").html("").append(($("#dizhi_list",e).html()))})}function AddressPagingForEdit(c,a){var b=$("#radioClickAddressID").val();var d="/DeliveryAddress/Internal_AddressListPaging";$.get(d+"?userID="+$("#currentUserID").val()+"&pageNum="+c+"&choosedID="+b+"&addressType="+a+"&getnewdefault=true&r="+Math.random(),function(e){$("#dizhi_list").html("").append(($("#dizhi_list",e).html()))})}function ShowDeleteConfirmDiv(a){$(a).prev().show().bgiframe()}function HidenDeleteConfirmDiv(a){$(".dialog-container").hide();if(window.event){window.event.cancelBubble=true}else{a.stopPropagation()}}$(function(){$("html").bind("click",function(){$(".dialog-container").hide()});$("#AddAddressDiv").hide();$("#btnToogleAddAddressDiv").removeClass("AddNewAdressArrowUp").addClass("AddNewAdressArrowDown")});function DeleAddress(a,b){var e=$("#currentUserID").val();var c=$("#radioClickAddressID").val();var d=$("#currentPageNum").val();$.get("/DeliveryAddress/Internal_DelAddress?addressID="+a+"&choosedID="+c+"&userID="+e+"&pageNum="+d+"&addressType="+b+"&r="+Math.random(),function(f){$("#ShowAddress").html("").append(f);$("#AddAddressDiv").hide();$("#btnToogleAddAddressDiv").removeClass("AddNewAdressArrowUp").addClass("AddNewAdressArrowDown")})}function SetDefaultAddress(a){var d=$("#currentUserID").val();var b=$("#radioClickAddressID").val();var c=$("#currentPageNum").val();$.get("/DeliveryAddress/Internal_SetDefaultAddress?addressID="+a+"&r="+Math.random(),function(f){var e="Inland";if(f.Result=="Success"){AddressPagingForEdit(c,e)}else{$.fn.alert("默认地址设置失败")}})}function ToogleAddAddressDiv(){var a=$("#AddAddressDiv").is(":hidden");if(a){CloseEditDiv()}DisplayAddAddressDiv(a)}function DisplayAddAddressDiv(a){if(a){$.get("/DeliveryAddress/Internal_AddOrEditAddress?addressID=0&actionType=Add&r="+Math.random(),function(b){$("#AddAddressDiv").html("").append(b);RegionLinkage.BindArea();RegionLinkage.BindAddressHead()});$("#AddAddressDiv").show();$("#btnToogleAddAddressDiv").removeClass("AddNewAdressArrowDown").addClass("AddNewAdressArrowUp")}else{$("#AddAddressDiv").html("");$("#AddAddressDiv").hide();$("#btnToogleAddAddressDiv").removeClass("AddNewAdressArrowUp").addClass("AddNewAdressArrowDown")}};var focusID="";function CheckFocus(c){if(focusID==""){focusID=c;var b=document.getElementById(c);b.focus()}}function ChecktheForm_Name(){var a=document.getElementById("receiverName");if($.trim(a.value)==""||$.trim(a.value)=="请准确填写真实姓名"){$("#receiverNameError").show();$("#receiverNameError").html("请填写姓名");return false}else{if(a.value.length<2){$("#receiverNameError").show();$("#receiverNameError").html("收货人姓名长度不得小于2，请重新填写。");return false}else{if(cnLength(a.value)>20){$("#receiverNameError").show();$("#receiverNameError").html("收货人姓名过长，请您最多输入10个汉字。");return false}else{$("#receiverNameError").hide();focusID=""}}}return true}function ChecktheForm_Address(){var a=document.getElementById("detailAddress");if($.trim(a.value)==""||$.trim(a.value)=="请填写详细路名及门牌号"){$("#detailAddressError").show();$("#detailAddressError").html("").html("请填写详细地址");return false}else{if(a.value.length<5){$("#detailAddressError").show();$("#detailAddressError").html("详细地址不得小于5，请重新填写。");return false}else{if(cnLength(a.value)>160){$("#detailAddressError").show();$("#detailAddressError").html("详细地址过长，请您最多输入80个汉字。");return false}else{$("#detailAddressError").hide();focusID=""}}}return true}function ChecktheForm_Zip(){var a=document.getElementById("postalCode");if($.trim(a.value)==""||$.trim(a.value)=="请填写邮政编码"){$("#postalCodeError").show();$("#postalCodeError").html("邮政编码不能为空");return false}else{if(!Check_Meth($.trim(a.value))){$("#postalCodeError").show();$("#postalCodeError").html("邮政编码只能为数字");return false}else{if($.trim(a.value).length!=6){$("#postalCodeError").show();$("#postalCodeError").html("邮政编码必须为6位");return false}else{$("#postalCodeError").hide();focusID=""}}}return true}function ChecktheForm_Tel(){var c=$("#ProvinceID").find("option:selected").text();var a=false;if(c.indexOf("香港")>-1||c.indexOf("澳门")>-1||c.indexOf("台湾")>-1){a=true}var d=document.getElementById("phone");var b=document.getElementById("mobile");if(!a){if($("#mobile").val()!=$("#mobileNumHid").val()){if($.trim(b.value)!=""&&$.trim(b.value)!="手机和座机至少填写一个"&&$.trim(b.value).length!=11){$("#mobileError").show();$("#mobileError").html("手机号码必须11位");return false}else{if($.trim(b.value)!=""&&$.trim(b.value)!="手机和座机至少填写一个"&&Check_Mobiles($.trim(b.value))==false){$("#mobileError").show();$("#mobileError").html("请输入正确的手机号码");return false}else{$("#mobileError").hide();focusID=""}}if($.trim(d.value).length>20){$("#phoneError").show();$("#phoneError").html("电话号码过长");return false}else{if($.trim(d.value)!=""&&$.trim(d.value)!="手机和座机至少填写一个"&&Check_Tel($.trim(d.value))==false){$("#phoneError").show();$("#phoneError").html("请输入正确的电话号码");return false}else{$("#phoneError").hide();focusID=""}}}}else{if($.trim(b.value)!=""&&$.trim(b.value)!="手机和座机至少填写一个"&&$.trim(b.value).length>11){$("#mobileError").show();$("#mobileError").html("手机号码过长");return false}if($.trim(d.value).length>20){$("#phoneError").show();$("#phoneError").html("电话号码过长");return false}}if(($.trim(d.value)==""&&$.trim(b.value)=="")||($.trim(d.value)==""&&$.trim(b.value)=="手机和座机至少填写一个")||($.trim(d.value)=="手机和座机至少填写一个"&&$.trim(b.value)=="")||($.trim(d.value)=="手机和座机至少填写一个"&&$.trim(b.value)=="手机和座机至少填写一个")){$("#mobileError").show();$("#mobileError").html("手机和电话至少有一项必填");return false}else{$("#mobileError").hide();$("#phoneError").hide()}return true}function Check_Tel(a){return/^((\d{3,4})\-{0,1}){0,1}(\d{7,8})(\-{0,1}\d{1,6}){0,1}$/.test(a)}function Check_Tel_HongKong(a){return/^(0\d{2,3}-)?[2,3]{1}\d{7}(-\d{1,6})?$/.test(a)}function Check_Mobiles(a){return/^((13|11|12|16|17|19|15|18|14)+\d{9})$/.test(a)}function Check_Mobiles_HongKong(a){return/^((5|6|9)+\d{7})$/.test(a)}function Check_Meth(d){var b="0123456789";var c=$.trim(d);var a=true;var e=true;for(i=0;i<c.length;i++){ch=c.charAt(i);for(j=0;j<b.length;j++){if(ch==b.charAt(j)){break}if(j==(b.length-1)){a=false;break}}}return a}function Form_Check_ProductArea(){if(!CheckProvince()){return false}if(!CheckCity()){return false}if(!CheckArea()){return false}return true}function Check_Consignee(){if(!ChecktheForm_Name()){return false}if(!Form_Check_ProductArea()){return false}if(!ChecktheForm_Address()){return false}if(!ChecktheForm_Zip()){return false}if(!ChecktheForm_Tel()){return false}return true}function ChangeAddressInputCss(c,b,d){if(d==1){var a=$("#"+c).val();if(a==b){$("#"+c).val("");$("#"+c).css("color","#000000")}}if(d==2){var a=$("#"+c).val();if(a==""){$("#"+c).val(b);$("#"+c).css("color","#999999")}else{$("#"+c).css("color","#000000")}}}function cnLength(d){var a=escape(d);var c=0;var b=a.length;for(i=0;i<b;i++){if(a.charAt(i)=="%"){if(a.charAt(++i)=="u"){c++}}}return d.length+c}function checkMobileNum(){if($("#mobile").val()==$("#mobileNumHid").val()){$("#mobile").val("")}};function RegionLinkage(){}RegionLinkage.pChoose="--省份/直辖市--";RegionLinkage.cityChoose="--市--";RegionLinkage.areaChoose="--县/区--";RegionLinkage.provinceCurrent="ProvinceID";RegionLinkage.cityIdV="CityID";RegionLinkage.areaIdV="AreaID";var provinceV="";RegionLinkage.BindArea=function(){var e="";var h=$("#SelectValue").val();var j=$("#Text").val();var g=$("#ProvinceText").val();var d=$("#CityText").val();var b=$("#AreaText").val();var f=$("#Province").val();var c=$("#City").val();var a=$("#Area").val();if(h==",,"){RegionLinkage.add_form("ProvinceID",j,"")}else{if(g!=""){RegionLinkage.add_form("ProvinceID",g,f)}if(d!=""){RegionLinkage.add_form("CityID",d,c)}if(b!=""){RegionLinkage.add_form("AreaID",b,a)}}};RegionLinkage.BindAddressHead=function(){var c=$("#Province").val();var b=$("#City").val();var a=$("#Area").val();if(c!=""){RegionLinkage.showAddressHead("AddressHeadProvince",c+",")}if(b!=""){RegionLinkage.showAddressHead("AddressHeadCity",b+",")}if(a!=""){RegionLinkage.showAddressHead("AddressHeadArea",a+",")}};RegionLinkage.showAddressHead=function(a,b){$("#"+a).html("").append(b)};RegionLinkage.add_form=function(c,d,b){var c=document.getElementById(c);if(c==undefined){return}d=d||"";if(d==""){return}var a=d.split("$");for(i=0;i<a.length;i++){v=a[i].split(",");c.length=c.length+1;c[i+1].value=v[0];c[i+1].text=v[1];if(c[i+1].text==b){c[i+1].selected=true}}};RegionLinkage.rtID=function(a){return document.getElementById(a)};RegionLinkage.ShowCity=function(b){provinceV=b;RegionLinkage.rtID("provincev").value=b;var a="";provinceV=b;RegionLinkage.rtID(RegionLinkage.cityIdV).options.length=0;RegionLinkage.rtID(RegionLinkage.cityIdV).options.add(new Option(RegionLinkage.cityChoose,0));RegionLinkage.rtID(RegionLinkage.areaIdV).options.length=0;RegionLinkage.rtID(RegionLinkage.areaIdV).options.add(new Option(RegionLinkage.areaChoose,0));var c="/DeliveryAddress/GetCityData?povinceId="+escape(b)+"&r="+Math.random();$.ajax({url:c,cache:false,datatype:"text",contentType:"text/xml",error:function(d,e){$.fn.alert("没有加载到数据")},success:function(d){RegionLinkage.LoadData(RegionLinkage.cityIdV,d)}})};RegionLinkage.ShowArea=function(a){RegionLinkage.rtID("cityv").value=a;if(provinceV==""){provinceV=RegionLinkage.rtID("provincev").value}RegionLinkage.rtID(RegionLinkage.areaIdV).options.length=0;RegionLinkage.rtID(RegionLinkage.areaIdV).options.add(new Option(RegionLinkage.areaChoose,0));var b="/DeliveryAddress/GetAreaData?cityId="+escape(a)+"&povinceId="+escape(provinceV)+"&r="+Math.random();$.ajax({url:b,cache:false,datatype:"text",contentType:"text/xml",error:function(){$.fn.alert("没有加载到数据")},success:function(c){RegionLinkage.LoadData(RegionLinkage.areaIdV,c)}})};RegionLinkage.LoadData=function(b,d){if(d!="undefined"&&d!=""){var c=document.getElementById(b);var a=d.split("$");for(i=0;i<a.length;i++){v=a[i].split(",");c.length=c.length+1;c[i+1].value=v[0];c[i+1].text=v[1]}}};$(document).ready(function(){RegionLinkage.BindArea();RegionLinkage.BindAddressHead()});function ShowCity(a){RegionLinkage.ShowCity(a);if(CheckProvince()){RegionLinkage.showAddressHead("AddressHeadProvince",a+",");RegionLinkage.showAddressHead("AddressHeadCity","");RegionLinkage.showAddressHead("AddressHeadArea","")}}function ShowArea(a){RegionLinkage.ShowArea(a);if(CheckCity()){RegionLinkage.showAddressHead("AddressHeadCity",a+",");RegionLinkage.showAddressHead("AddressHeadArea","")}}function ShowV(a){RegionLinkage.rtID("areav").value=a;if(CheckArea()){RegionLinkage.showAddressHead("AddressHeadArea",a+",")}}function CheckProvince(){var a=$("#ProvinceID").val();if(a=="0"||a==""){$("#provinceError").show();RegionLinkage.showAddressHead("AddressHeadProvince","");RegionLinkage.showAddressHead("AddressHeadCity","");RegionLinkage.showAddressHead("AddressHeadArea","");return(false)}else{$("#provinceError").hide()}return(true)}function CheckCity(){var a=$("#CityID").val();if(a=="0"||a==""){$("#cityError").show();RegionLinkage.showAddressHead("AddressHeadCity","");RegionLinkage.showAddressHead("AddressHeadArea","");return(false)}else{$("#cityError").hide()}return(true)}function CheckArea(){var a=$("#AreaID").val();if(a=="0"||a==""){$("#areaError").show();RegionLinkage.showAddressHead("AddressHeadArea","");return(false)}else{$("#areaError").hide()}return(true)}function GetPostalCodeNew(a,d,h){var b=a;var e=document.getElementById(d);var f=document.getElementById(h);var c=e.options[e.selectedIndex].text;var g=f.options[f.selectedIndex].text;if(g!=""&&c!=""&&b!=""){LoadGetPostalCode(g,c,b)}}function LoadGetPostalCode(c,b,a){var d="/DeliveryAddress/GetPostalCode?povince="+encodeURI(c)+"&city="+encodeURI(b)+"&area="+encodeURI(a)+"&r="+Math.random();get_PostalCode=createXMLHTTPRequest(get_PostalCode);get_PostalCode.open("GET",d,true);get_PostalCode.onreadystatechange=LoadedComment;get_PostalCode.send(null)}function createXMLHTTPRequest(a){if(window.ActiveXObject){a=new ActiveXObject("Microsoft.XMLHTTP")}else{if(window.XMLHttpRequest){a=new XMLHttpRequest()}}return a}var get_PostalCode;function LoadedComment(){if(get_PostalCode.readyState==4){if(get_PostalCode.status==200){var b=get_PostalCode.responseText;if(b!=null&&b!=""){var d=b.split("$");if(d.length>4){var f=d[0];var c=d[1];var a=d[2];var e=d[3];var h=d[4];var g='&nbsp;参考邮编： <span class="reusableColor4">'+e+"</span>&nbsp;<span class=\"reusableColor1\"><a style='text-decoration:none' href=\"javascript:SetCode('"+e+"')\" > 使用此邮编 </a></span>";$("#SetPostalCode").html(g);$("#PhoneCode").html('参考区号：<span class="reusableColor4">'+h+"</span><a style='text-decoration:none' href=\"javascript:SetPhoneCode('"+h+"-')\" >  使用此区号  </a>")}}}}}function SetCode(a){document.getElementById("postalCode").value=a;$("#postalCode").css("color","#000000");ChecktheForm_Zip()}function SetPhoneCode(a){ChangeAddressInputCss("phone","手机和座机至少填写一个",1);document.getElementById("phone").value=a};document.domain="vancl.com";$(function(){var a=$('<div id="imdiv"><img hspace="6" src="http://i.vanclimg.com/others/2011/11/9/icon.gif" align="absmiddle" /><a style="padding-right:8px;" href="http://imweb.vancl.com" id="impage" target="_blank">在线客服</a><a href="#"><img src="http://i.vanclimg.com/others/2011/11/9/top.gif" hspace="3" align="absmiddle" /></a></div>').attr("style","width:112px; height:25px; padding-top:3px;float:right; background:url(http://i.vanclimg.com/others/2011/11/9/bg.gif) center no-repeat; position:fixed;_position:absolute;_bottom:auto; right:0; bottom:0;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)))");$("body").append(a);$("#im").live("click",function(){$(this).prev("img").attr("http://i.vanclimg.com/others/2011/11/9/icon.gif");var b=$("#imiframe");if(b.length==0){b=$("<iframe />");b.attr("id","imiframe").attr("src",$(this).attr("href")).attr("frameborder","0").attr("scrolling","no").css({width:641,height:480,bottom:0,right:0,position:"fixed","z-index":9999}).attr("style","width:641px; height:480px;z-index:9999;position:fixed; _position:absolute;_bottom:auto; right:0; bottom:0;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)))").appendTo("body")}b.show();return false});window.hidePanel=function(){$("#im").prev("img").attr("src","http://i.vanclimg.com/others/2011/11/9/icon.gif");$("#imiframe").hide()};window.closePanel=function(){$("#im").prev("img").attr("src","http://i.vanclimg.com/others/2011/11/9/icon.gif");$("#imiframe").remove()};window.toTop=function(){window.scrollTo(0,0)};window.notifyNewMessage=function(){$("#im").prev("img").attr("src","http://i.vanclimg.com/others/2011/11/9/gif.gif")}});