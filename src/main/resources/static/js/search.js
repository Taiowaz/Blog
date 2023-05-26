$(function () {
    $(".present_page").text("搜索");
    var keyword = getUrlParam("keyword");
    console.log(keyword);
    getAboutUserAndArticle(keyword);
})

//获取匹配用户与文章
function getAboutUserAndArticle(keyword) {
    $.ajax({
        url: "/searchContent",
        type: "post",
        data: {
            "keyword": keyword,
        },
        success: function (res) {
            console.log(res);
            bindData(res);
            $(".article_list_title").click(btnArticleListTitleEvent);
        }

    })
}

function bindData(res) {
    var userList = res.userList;
    var articleList = res.articleList;

    console.log(userList);
    console.log(articleList)

    var userListHtml = "";
    var articleListHtml = "";
    if (userList !== null) {
        for (var i = 0; i < userList.length; i++) {
            userListHtml += "<div class=\"row user border mx-2 mb-2\">\n" +
                "                            <div class=\"col-3 border\">\n" +
                "                                <img src=\"" + userList[i].avatarUrl + "\" alt=\"555\" style=\"height: 100px;width: 100px\">\n" +
                "                            </div>\n" +
                "                            <div class=\"col-9 border\">\n" +
                "                                <div class=\"\">账 户：" + userList[i].account + "</div>\n" +
                "                                <div class=\"\">性 别：" + userList[i].gender + "</div>\n" +
                "                                <div class=\"\">描述信息：" + userList[i].detail + "</div>\n" +
                "                            </div>\n" +
                "                        </div>";
        }
        $(".user_list_search").append(userListHtml);
    }
    if (articleList.length !== 0) {
        for (var i = 0; i < articleList.length; i++) {
            articleListHtml += "<div class=\" row text-bg-dark mx-2 mb-2 border article\">\n" +
                "<input type=\"text\" name=\"article_id\" disabled hidden=\"hidden\" value=" + articleList[i].articleId + ">" +
                "                        <div class=\"article_list_user_avatar_index border col-sm-2 col-md-2 col-lg-2 col-xl-2\">\n" +
                "                            <img src=\"" + articleList[i].avatarUrl +
                "\" alt=\"这是头像\" class=\"rounded\" style=\"height: 100px;width: 100px\">\n" +
                "                        </div>\n" +
                "                        <div class=\"border col-sm-5 col-md-5 col-lg-5 col-xl-5\">\n" +
                "                            <div class=\"article_list_user_account_index\">账 &nbsp; &nbsp; 户  &nbsp; &nbsp; 名：" + articleList[i].account + "</div>\n" +
                "                            <div class=\"article_list_last_modify_time_index\">上次修改时间：" + articleList[i].lastModifyTime.substring(0, 16) + "</div>\n" +
                "                            <div class=\"article_list_release_time_index\">发 &nbsp; 布&nbsp; 时&nbsp; 间：" + articleList[i].releaseTime.substring(0, 16) + "</div>\n" +
                "                        </div>\n" +
                "                        <div class=\"border col-sm-5 col-md-5 col-lg-5 col-xl-5\">\n" +
                "                            <button class=\"btn btn-white h-100 w-100 article_list_title\">\n" +
                "                                " + articleList[i].articleTitle + "\n" +
                "                            </button>\n" +
                "                        </div>\n" +
                "                    </div>";
        }
        $(".article_list_search").append(articleListHtml);
    }
}

//文章点击事件
function btnArticleListTitleEvent() {
    var articleRowDiv = $(this).parent().parent();

    var articleId = articleRowDiv.find("input[name='article_id']").val();
    window.location.href = "/article?articleId=" + articleId;
}