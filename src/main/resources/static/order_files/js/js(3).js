﻿$(function(){var b,c,d,a;b=(function(){window.VANCL=window.VANCL||{};window.VANCL.Global=window.VANCL.Global||{};return window.VANCL.Global}());c=document.location.hash;d=c.indexOf("@");a=c.length;if(document.all&&c.length>-1&&document.title.split("#").length>1){document.title=document.title.split("#")[0]}if(d>-1){document.location.hash=c.substr(d+1)}if(c.substr(0,5)==="#ref="){if(d>-1){a=d}b.ref=c.substring(5,a)}$("body").mousedown(function(e){function k(p){var o=b.hasOwnProperty("ref")?b.ref:null;return p.attr("location")===undefined?o:p.attr("location")}function h(o){return o===undefined||o===""||o===null}function n(p,r){var s=k(r),q,o;if(h(s)===true){return h(p)?null:p}if(p===""){return s}q=p+"|"+s;o=q.split("|");if(o.length>1&&o[0]===o[1]){return s}if(o.length>=10){o.splice(8,o.length-9)}return o.join("|")}function i(p){var o=p.attr("href");if(h(o)===true){return null}o=o.replace(" ","");if(o.indexOf("javascript")>-1){return null}if(o.indexOf("#")>-1&&p.attr("target")===undefined){return null}return o}function f(o){if(o===undefined){return false}if(o===""){return true}if(o.indexOf("_")===-1&&o.indexOf("-")===-1){return false}return true}function j(r,u){var o="",t=r,p,q,s;p=r.lastIndexOf("#");q=r.indexOf("@");if(p>-1){t=r.substr(0,p);if(q===-1){q=p}o="@"+r.substr(q+1)}if(r.indexOf("vjia.com")===-1){return t+"#ref="+u+o}if(r.indexOf(u)>-1){return r}s=r.indexOf("?")===-1?"?":"&";return t+s+"ref="+u}var m,l,g;m=$(e.target).closest("a");if(m.length===0){return}l=m.attr("rel");g=i(m);if(g===null){return}if(h(l)&&m.hasClass("track")){l=m.attr("name")}if(f(l)===false){return}l=n(l,m);if(l){m.attr("href",j(g,l))}})});