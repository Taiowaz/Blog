$(function () {
    //发送请求获取博客列表
    $.ajax({
        type: 'post',
        url: '/listAllArticle',
        success: function (res) {
            if (res !== "" && res !== null) {
                bindArticleData(res);
            }
        }
    })
})

//绑定数据并加载视图
function bindArticleData(res) {
    var html = "";

    for (var i = 0; i < res.length; i++) {
        html += "<div class=\" row text-bg-dark mb-3 border mx-auto article\">\n" +
            "<input type=\"text\" name=\"articleId\" disabled hidden=\"hidden\"" + res.articleId + ">" +
            "                        <div class=\"article_list_user_avatar_index border col-sm-2 col-md-2 col-lg-2 col-xl-2\">\n" +
            "                            <img src=\"" + res[i].avatarUrl +
            "\" alt=\"这是头像\" class=\"rounded\" style=\"height: 100px;width: 100px\">\n" +
            "                        </div>\n" +
            "                        <div class=\"border col-sm-5 col-md-5 col-lg-5 col-xl-5\">\n" +
            "                            <div class=\"article_list_user_account_index\">账 &nbsp; &nbsp; 户  &nbsp; &nbsp; 名：" + res[i].account + "</div>\n" +
            "                            <div class=\"article_list_last_modify_time_index\">上次修改时间：" + res[i].lastModifyTime.substring(0,16) + "</div>\n" +
            "                            <div class=\"article_list_release_time_index\">发 &nbsp; 布&nbsp; 时&nbsp; 间：" + res[i].releaseTime.substring(0,16) + "</div>\n" +
            "                        </div>\n" +
            "                        <div class=\"border col-sm-5 col-md-5 col-lg-5 col-xl-5\">\n" +
            "                            <button class=\"btn btn-white h-100 w-100 article_list_title\">\n" +
            "                                " + res[i].articleTitle + "\n" +
            "                            </button>\n" +
            "                        </div>\n" +
            "                    </div>"
    }

    $(".main").html(html);
}