//提示
function toast(header, body) {
    $('.toast-header:first strong').text(header);
    $('.toast-body:first').text(body);
    console.log(header);
    console.log(body);
    const toastLiveExample = $('#liveToast');
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
    toastBootstrap.show();
}

//初始化加载已登录账户数据
function initUserInfoBtn() {
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            console.log(res);
            if (res != null && res != '') {
                if (res.avatarType !== null && res.avatarType !== ""
                    && res.avatarType !== undefined && res.avatarType != "null") {
                    console.log(true)
                    $(".avatar").attr("src", 'data:' + res.avatarType + ";base64," + avatarBase64);
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
            console.log(res);
            if (res == null || res == '') {
                window.location.href = "/loginRegister";
            } else {
                if (res.avatarType !== null && res.avatarType !== ""
                    && res.avatarType !== undefined && res.avatarType !== "null") {
                    console.log(true)
                    $(".avatar").attr("src", 'data:' + res.avatarType + ";base64," + avatarBase64);
                }
                $(".account").text(res.account);
                window.location.href = "/userInfo";
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

    $(".user_list").click(function () {
        window.location.href = "/userList";
    });
    $(".log_list").click(function () {
        window.location.href = "/logList";
    })
})
