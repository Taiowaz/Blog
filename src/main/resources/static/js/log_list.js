$(function () {
    $(".present_page").text("登录日志");

    getLogData("/log/listAllLoginLog");

    $("#btn_login_log_list").click(btnLoginLogListEvent);
    $("#btn_operate_log_list").click(btnOperateLogListEvent);
})

function getLogData(url) {
    $.ajax({
        url: url,
        type: "post",
        success: function (res) {
            bindLogData(res);
        }
    })
}

//绑定日志数据
function bindLogData(res) {
    if (res === "") return;
    var html = "";
    for (var i = 1; i < res.length; i++) {
        html += "                        <div class=\"row text-center border mx-2 mb-2\">\n" +
            "                            <div class=\"col-4 border\">\n" + res[i].account +
            "                            </div>\n" +
            "                            <div class=\"col-4 border\">\n" + res[i].operation +
            "                            </div>\n" +
            "                            <div class=\"col-4 border\">\n" + res[i].time +
            "                            </div>\n" +
            "                        </div>";
    }
    $("#log_list").html(html);
}

function btnLoginLogListEvent() {
    $(".present_page").text("登录日志");
    getLogData("/log/listAllLoginLog");
}

function btnOperateLogListEvent() {
    $(".present_page").text("操作日志");
    getLogData("/log/listAllOperateLog");
}