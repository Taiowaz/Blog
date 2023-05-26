$(function () {
    $(".present_page").text("编辑文章");

    getSession(getArticleTypeCallback);
    tinymce.init({
        selector: 'textarea#articleContent',
    });

    $(".btn_save_article").click(btnSaveArticleEvent);
});


//获取文章类型回调函数(在getSession后调用)
function getArticleTypeCallback(res) {
    id = res.id;
    $("input[name='userId']").val(res.id);
    $("input[name='userType']").val(res.userType);
    $.ajax({
        url: "/listArticleTypeByUserId",
        data: {"userId": id},
        type: "post",
        success: function (res) {
            bindArticleTypeData(res);
        }
    })
}

//绑定文章类型回调函数
function bindArticleTypeData(res) {
    var html = "";
    for (var i = 0; i < res.length; i++) {
        html += "<option value=" + res[i].articleTypeId + ">" + res[i].articleTypeName + "</option>"
    }
    $(".sel_article_Type").html(html);
}

//保存按钮事件
function btnSaveArticleEvent() {
    var userId = $("input[name='userId']").val();
    var userType = $("input[name='userType']").val();
    var articleId = $("input[name='articleId']").val();
    if (articleId === "" || articleId == null) {
        articleId = 0;
    }
    var articleTitle = $("input[name='articleTitle']").val();
    var articleContent = tinymce.activeEditor.getContent();
    var articleTypeId = $("select.sel_article_Type").val();


    if (!vertifyArticle(articleTitle, articleContent, articleTypeId)) {
        return;
    }

    $.ajax({
        method: 'post',
        url: "/saveArticle",
        data: {
            "userId": userId,
            "userType": userType,
            "articleId": articleId,
            "articleTitle": articleTitle,
            "articleContent": articleContent,
            "articleTypeId": articleTypeId
        }
    }).then((res) => {
        console.log(res)
        if (res === "" || res === null) {
            toast("保存失败", "类型已被使用");
            return;
        }
        window.location.href = "/myArticle";
    })
}

function vertifyArticle(articleTitle, articleContent, articleTypeId) {
    if (articleTitle.length === 0 || articleTitle.length >= 20 || articleContent.length === 0 || articleTypeId == null || articleTypeId == "") {
        toast("保存失败", "标题或内容不合理，或未选择文章类型")
        return false;
    }
    return true;
}