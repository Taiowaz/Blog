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

//todo session 之间的信息传递
//session
function getSession() {
    $.ajax({
        url: '/getSession',
        type: 'post',
        success: function (res) {
            if (res == null || res == '') {
                window.location.href = "/loginRegister";
            }
            esle
            {
                $(".avatar").attr("src", 'data:' + res.avatarType + ";base64," + avatarBase64);
                $(".account").text(res.account);
                window.location.href = "/userInfo";
            }
        }
    })
}

$(function () {
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
    $(".user_info").click(function () {

        window.location.href = "/userInfo";
    });
    $(".user_list").click(function () {
        window.location.href = "/userList";
    });
    $(".log_list").click(function () {
        window.location.href = "/logList";
    })
})
