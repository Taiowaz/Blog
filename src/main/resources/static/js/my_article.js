$(function () {
    $(".present_page").text("我的文章");
    getSession(sessionCallback);

})

function sessionCallback(res) {
    var userId = res.id;
    var userType = res.userType;

    getArticleByUserIdAndUserType(userId, userType);
}

//根据当前用户获取文章列表
function getArticleByUserIdAndUserType(userId, userType) {
    console.log(userType)
    console.log(userId)
    var data = {
        "userId": userId,
        "userType": userType,
    };

    $.ajax({
        url: "/getArticleByUserIdAndUserType",
        type: "post",
        data: data,
        success: function (res) {
            bindArticleData(res);
            $(".article_list_title").click(btnArticleListTitleEvent);
            $(".btn_delete_my_article").click(btnDeleteArticleEvent);
        }
    })
}

//绑定数据并加载视图
function bindArticleData(res) {
    console.log(res)
    var html = "";

    for (var i = 0; i < res.length; i++) {
        html += "<div class=\" row text-bg-dark mb-3 border mx-auto article\">\n" +
            "<input type=\"text\" name=\"article_id\" disabled hidden=\"hidden\" value=" + res[i].articleId + ">" +
            "                        <div class=\"article_list_user_avatar_index border col-sm-2 col-md-2 col-lg-2 col-xl-2\">\n" +
            "                            <img src=\"" + res[i].avatarUrl +
            "\" alt=\"这是头像\" class=\"rounded\" style=\"height: 100px;width: 100px\">\n" +
            "                        </div>\n" +
            "                        <div class=\"border col-sm-5 col-md-5 col-lg-5 col-xl-5\">\n" +
            "                           <div>标 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 题：\n" +
            "                                <button class=\"btn btn-white article_list_title\">\n" +
            "                                    " + res[i].articleTitle +
            "                                </button>\n" +
            "                            </div>" +
            "                            <div class=\"article_list_last_modify_time_index\">上次修改时间：" + res[i].lastModifyTime.substring(0, 16) + "</div>\n" +
            "                            <div class=\"article_list_release_time_index\">发 &nbsp; 布&nbsp; 时&nbsp; 间：" + res[i].releaseTime.substring(0, 16) + "</div>\n" +
            "                        </div>\n" +
            "                        <div class=\"border col-sm-5 col-md-5 col-lg-5 col-xl-5 d-flex justify-content-around align-items-center\">" +
            "                             <button class=\" btn_save_my_article btn btn-outline-success w-50\">保存</button>" +
            "                             <button class=\"btn_delete_my_article btn btn-outline-danger w-50\">删除</button>" +
            "                        </div>\n" +
            "                    </div>"
    }

    $(".main").html(html);
}

//文章点击事件
function btnArticleListTitleEvent() {
    var articleRowDiv = $(this).parent().parent().parent();

    var articleId = articleRowDiv.find("input[name='article_id']").val();
    window.location.href = "/article?articleId=" + articleId;
}

//删除文章按钮事件
function btnDeleteArticleEvent() {
    var articleRowDiv = $(this).parent().parent();

    var articleId = articleRowDiv.find("input[name='article_id']").val();

    $.ajax({
        url: "/deleteArticle",
        type: "post",
        data: {
            "articleId": articleId,
        }
    });

    window.location.href = "/myArticle";
}

