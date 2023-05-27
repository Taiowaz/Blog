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
                $(".account").show();
                $(".avatar").show();
                $(".tip").hide();

                if (res.avatar !== null && res.avatarUrl !== ""
                    && res.avatarUrl !== undefined && res.avatarUrl != "null") {
                    $(".avatar").attr("src", res.avatarUrl);
                }
                $(".account").text(res.account);
            } else {
                $(".tip").show();
                $(".account").hide();
                $(".avatar").hide();
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
    var btnText = $(this).text();
    if (btnText === "用户列表") {
        url = "/userList";
    } else {
        url = "/logList";
    }
    console.log(url)
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            console.log("res " + res.userType);
            if (res == null || res == '') {
                window.location.href = "/loginRegister";
            } else {
                if (res.userType === 'admin') {
                    window.location.href = url;
                } else {
                    toast("警告", "您没有权限访问该页面！");
                }
            }
        }
    })
}

//搜索按钮点击事件
function btnSearchEvent() {
    var keyword = $("#search_input").val();
    if (keyword.trim() !== "") {
        window.location.href = "/search?keyword=" + keyword;
    }
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

    $(".log_list").click(btnUserListOrLogListClickEvent);

    $(".btn_search").click(btnSearchEvent);
})
