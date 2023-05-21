$(function () {
    // //获取用户登录消息
    // $.ajax({
    //     type: 'post',
    //     url: '/'
    //
    // })

    //发送请求获取博客列表
    $.ajax({
        type: 'post',
        url: '/listAllArticle',
        success: function (res) {
            formatArticleListRes(res);
            bindArticleData(avatarBase64, avatarType, account, releaseTime, title);
        }
    })

    //对返回结果进行处理并绑定数据到视图上
    function formatArticleListRes(res) {
        if (res == null) return;
        res.forEach(function (article) {
            let avatarBase64;
            let avatarType;
            let account;
            let releaseTime;
            let title;

            releaseTime = article.releaseTime;
            title = article.articleTitle;

            if (article.admin != null) {
                account = article.admin.account;
                avatarBase64 = article.admin.avatarBase64;
                avatarType = article.admin.avatarType;
            } else if (article.regularUser != null) {
                account = article.regularUser.account;
                avatarBase64 = article.regularUser.avatarBase64;
                avatarType = article.regularUser.avatarType;
            }
        })
    }

    //绑定数据并加载视图
    function bindArticleData(avatarBase64, avatarType, account, releaseTime, title) {
        let avatarSrc = 'data:' + avatarType + ";base64," + avatarBase64;
        let articleHtml = "<div class=\"row text-bg-dark border mx-auto\">\n" +
            "                        <div class=\"article_list_user_avatar_index border col-sm-3 col-md-3 col-lg-3 col-xl-3\">\n" +
            "                            <img src=\"" + avatarSrc + "\" alt=\"这是头像\">\n" +
            "                        </div>\n" +
            "                        <div class=\"border col-sm-3 col-md-3 col-lg-3 col-xl-3\">\n" +
            "                            <div class=\"article_list_user_account_index\">" + account + "</div>\n" +
            "                            <div class=\"article_list_release_time_index\">" + releaseTime + "</div>\n" +
            "                        </div>\n" +
            "                        <div class=\"article_list_title border col-sm-6 col-md-6 col-lg-6 col-xl-6\">\n" +
            title +
            "                        </div>\n" +
            "                    </div>"

        $("#main").append(articleHtml);
    }

    //头像点击事件
    $("#avatar").click(function () {

    })
})