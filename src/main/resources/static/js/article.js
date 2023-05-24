$(function () {

})

//todo 文章详细
function btnAddCommentEvent() {
    var account = $(".account").val();
    var commentContent = $("input[name='comment_content']").val();
    var articleId = $("input[name='article_id']").val();


    if (account === "" || account == null) {
        window.location.href = "/loginRegister";
    }

    if (commentContent.length === 0) {
        toast("发布失败", "不能发表空评论");
        return;
    }

    $.ajax({
        url: "/addComment",
        type: "post",
        data: {
            "account": account,
            "commentContent": commentContent,
            "articleId": articleId
        },
        success: function (res) {
            if (res === "") {
                toast("发布失败", "评论存在问题");
                return;
            }
            window.location.href = "/article?articleId=" + articleId;
        }
    })

}