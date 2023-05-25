$(function () {
    var articleId = getUrlParam("articleId");

    $("input[name='article_id']").val(articleId);

    getArticleDetail(articleId);

    getSessionInArticlePage();

    $(".btn_add_comment").click(btnAddCommentEvent);
})


//添加评论事件
function btnAddCommentEvent() {
    var commentContent = $("input[name='comment_content']").val();
    var articleId = $("input[name='article_id']").val();
    var userId = $("input[name='user_id']").val();
    var userType = $("input[name='user_type']").val();

    var data = {
        "userId": userId,
        "userType": userType,
        "commentContent": commentContent,
        "articleId": articleId,
    };

    console.log(data)

    if (userId === "" || userId == null) {
        window.location.href = "/loginRegister";
    }

    if (commentContent.length === 0) {
        toast("发布失败", "不能发表空评论");
        return;
    }

    $.ajax({
        url: "/addComment",
        type: "post",
        data: data,
        success: function (res) {
            if (res === "") {
                toast("发布失败", "评论存在问题");
                return;
            }
            window.location.href = "/article?articleId=" + articleId;
        }
    })
}

//从后端获取文章信息
function getArticleDetail(articleId) {
    $.ajax({
        url: "/getArticleDetail",
        type: "post",
        data: {
            "articleId": articleId,
        },
        success: function (res) {
            bindArticleDetailData(res);
        }
    })
}

//绑定数据到视图上
function bindArticleDetailData(res) {
    $(".article_title").text(res.articleTitle);
    $(".article_account").append(res.account);
    console.log(res)
    $(".article_detail_type").append(res.articleTypeName);
    $(".article_content").html(res.articleContent);

    var commentList = res.commentList;
    var html = "";
    for (var i = 0; i < commentList.length; i++) {
        html += "<div class=\"row mb-2 border\">\n" +
            "                            <div class=\"col-2 comment_avatar\">\n" +
            "                                <img src=\"" + commentList[i].avatarUrl + "\" alt=\"\" style=\"width: 100px; height: 100px;\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-10\">\n" +
            "                                <div class=\"fs-4 border mb-1 comment_account\">" + commentList[i].account + "</div>\n" +
            "                                <div class=\"fs-5 border comment_content\">" + commentList[i].commentContent + "</div>\n" +
            "                            </div>\n" +
            "                        </div>"
    }
    $(".comment_list").html(html);
}

//获取session
function getSessionInArticlePage() {
    $.ajax({
        url: '/getSession',
        type: 'get',
        success: function (res) {
            $("input[name='user_id']").val(res.id);
            $("input[name='user_type']").val(res.userType);
        }
    })

}
