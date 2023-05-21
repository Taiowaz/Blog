$(function () {
    function init() {
        loadArticleType();
    }

    function loadArticleType() {
        $.ajax({
            url: "/listAllArticleType",
            type: "GET",
            success: function (data) {
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += "<div class=\"border w-25 mb-3\">"
                        + data[0].articleTypeName
                    "</div>"
                }
                $("#articleTypeList").append(html);
            }
        })
    }

    $("#btn_summit_article_type").click(function () {
        var articleTypeName = $("input[name='articleTypeName']").val();
        console.log(articleTypeName);
        $.ajax({
            url: "/addArticleType",
            data: articleTypeName,
            success: function (res) {
                if (res == null || res == "") {
                    toast('添加失败', '文章类型已存在');
                } else {
                    toast('添加成功', '文章类型添加成功');
                }
                window.location.href = "/articleType";
            }
        })
    })
})