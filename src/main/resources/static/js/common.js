//提示
function toast(header, body) {
    $('.toast-header:first strong').text(header);
    $('.toast-body:first').text(body);
    const toastLiveExample = $('#liveToast');
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
    toastBootstrap.show();
}

function getSession(callbackFunction) {
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            if (res === "" || res === null || res === undefined) {
                window.location.href = "/loginRegister";
            } else {
                callbackFunction(res);
            }
        }
    })
}

//获取链接所带的参数
function getUrlParam(paramName) {
    var url = window.location.href;
    var params = new URL(url).searchParams;
    var paramValue = params.get(paramName);
    return paramValue;
}

//初始化加载已登录账户数据
function initUserInfoBtn() {
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            if (res != null && res != '') {
                if (res.avatar !== null && res.avatarUrl !== ""
                    && res.avatarUrl !== undefined && res.avatarUrl != "null") {
                    $(".avatar").attr("src", res.avatarUrl);
                }
                $(".account").text(res.account);
            }
        }
    })
}

//userInfo按钮点击事件
function btnUserInfoClickEvent() {
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            if (res == null || res == '') {
                window.location.href = "/loginRegister";
            } else {
                window.location.href = "/userInfo?id=" + res.id + "&userType=" + res.userType;
            }
        }
    })
}

//用户列表或日志列表点击事件
function btnUserListOrLogListClickEvent() {
    var url;
    if (this.className === "user_list") {
        url = "/userList";
    } else {
        url = "/logList";
    }
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            if (res == null || res == '') {
                window.location.href = url;
            } else {
                if (res.userType === 'admin') {
                    window.location.href = "/userList";
                } else {
                    toast("警告", "您没有权限访问该页面！");
                }
            }
        }
    })
}

$(function () {

    initUserInfoBtn();

    $(".index").click(function () {
        window.location.href = "/";
    });
    $(".my_article").click(function () {
        window.location.href = "/myArticle";
    });
    $(".article_type").click(function () {
        window.location.href = "/articleType";
    });
    $(".edit_article").click(function () {
        window.location.href = "/editArticle";
    });
    $(".user_info").click(btnUserInfoClickEvent);

    $(".user_list").click(btnUserListOrLogListClickEvent);

    $(".log_list").click(btnUserListOrLogListClickEvent)
})
