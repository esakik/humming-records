/** 初めてアクセスされた際にitemを表示する */
$(function() {
    $.ajax({
        type : "POST",
        url : "/displayInitialItemList",
        dataType : "json",
        success : function(data) {
            for (var i=0; i < data.length; i++) {
                $("#item").append(
                      "<div class='single-item col-lg-3 col-md-3 col-sm-4 col-xs-6'>"
                    + "<img src='data:jpg;base64," + data[i].image + "' class='img-thumbnail'>"
                    + "<div class='singer'>" + "<a href='/detail?itemId=" + data[i].id + "' style='text-decoration: none'>" + data[i].singer + "</a></div>"
                    + "<div class='song'>" + data[i].song + "</div>"
                    + "<div class='price'>" + data[i].price + "円</div>"
                    + "</div>"
                )
            }
        }
    });
});