$(function () {
        function init() {
            loadArticleType();
        }

        init();

        //加载文章类型
        function loadArticleType() {
            $.ajax({
                url: "/listAllArticleType",
                type: "GET",
                success: function (data) {
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        html += "<div class=\"row text-center border mx-2 mb-2\">\n" +
                            "                        <div class=\"col-2 border\">\n" +
                            "                            <input type=\"text\" name=\"articleTypeId\" class=\"w-100 form-control\"" +
                            "                                   disabled value=" + data[i].articleTypeId + ">\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-4 border\">\n" +
                            "                            <input type=\"text\" name=\"articleTypeName\" class=\"w-100 form-control\"" +
                            "                                   value=" + data[i].articleTypeName + " >\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-6 border\">\n" +
                            "                            <button class=\"btn btn-outline-success btn_save_article_type\">保存</button>\n" +
                            "                            <button class=\"btn btn-outline-danger btn_delete_article_type\">删除</button>\n" +
                            "                        </div>\n" +
                            "                    </div>"
                    }
                    $("#article_type_index").after(html);
                    //对加载视图绑定button事件
                    $(".btn_save_article_type").click(btnSaveClickEvent);
                    $(".btn_delete_article_type").click(btnDeleteClickEvent);
                }
            })
        }

        //添加文章类型按钮点击事件
        $("#btn_summit_article_type").click(function () {
            var articleTypeName = $("#input_articleType").val();
            if (vertifyArticleTypeNameLength(articleTypeName) == false) {
                toast('添加失败', '文章类型长度不合理');
                return;
            }

            sendUpdateOrAddArticleTypeRequest(0, articleTypeName, '添加失败', '文章类型已存在');
        })


        //保存文章类型按钮点击事件
        function btnSaveClickEvent() {
            //找到每行的起始div
            var norDiv = $(this).parent().parent();

            var articleTypeId = norDiv.find("input[name='articleTypeId']").val();
            var articleTypeName = norDiv.find("input[name='articleTypeName']").val();


            if (vertifyArticleTypeNameLength(articleTypeName) == false) {
                toast('保存失败', '文章类型长度不合理');
                return;
            }

            sendUpdateOrAddArticleTypeRequest(articleTypeId, articleTypeName, '保存失败', '文章类型已存在');
        }

        //删除文章类型按钮点击事件
        function btnDeleteClickEvent() {
            //找到每行的起始div
            var norDiv = $(this).parent().parent();

            var articleTypeId = norDiv.find("input[name='articleTypeId']").val();

            $.ajax({
                    url: "/deleteArticleTypeById",
                    type: "POST",
                    data: {"articleTypeId": articleTypeId},
                    success: function (res) {
                        window.location.href = "/articleType";
                    }
                }
            )


        }

        //发送更新或添加文章类型请求
        function sendUpdateOrAddArticleTypeRequest(articleTypeId, articleTypeName, header, body) {
            $.ajax({
                url: "/addArticleType",
                type: "POST",
                data: {"articleTypeId": articleTypeId, "articleTypeName": articleTypeName},
                success: function (res) {
                    if (res == null || res == "") {
                        toast(header, body);
                        return;
                    }
                    window.location.href = "/articleType";

                }
            })
        }

        //验证文章类型长度是否合理
        function vertifyArticleTypeNameLength(articleTypeName) {
            if (articleTypeName.length >= 2 && articleTypeName.length <= 10) {
                return true;
            } else {
                return false;
            }
        }

    }
)