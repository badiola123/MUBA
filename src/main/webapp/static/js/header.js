$(document).ready(function(){
    var nav1 = $("#nav1");
    var nav2 = $("#nav2");
    var nav3 = $("#nav3");
    nav1.addClass("btn btn-success");
    nav2.addClass("btn btn-success");
    nav3.addClass("btn btn-success");
    var current =$("#current");
    if(current.val()=="1") nav1.addClass("btn btn-success active");
    if(current.val()=="2") nav2.addClass("btn btn-success active");
    if(current.val()=="3") nav3.addClass("btn btn-success active");
});
