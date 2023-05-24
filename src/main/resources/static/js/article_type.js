$(function () {

        getSession(callbackFunction);

        //设置获取Session后的回调函数
        function callbackFunction(res) {
            id = res.id;
            console.log("userId  " + id);
            $("input[name='userId']").val(id);
            console.log("userId  " + $("input[name='userId']").val());
            loadArticleType(id);

        }

        //加载文章类型
        function loadArticleType(id) {
            $.ajax({
                url: "/listArticleTypeByUserId",
                type: "post",
                data: {"userId": id},
                success: function (res) {
                    if (res !== "") {
                        bindArticleTypeData(res);
                    }
                }
            })
        }

        //绑定数据到视图中
        function bindArticleTypeData(res) {
            var html = "";
            for (var i = 0; i < res.length; i++) {
                html += "<div class=\"row text-center border mx-2 mb-2\">\n" +
                    "                        <div class=\"col-2 border\">\n" +
                    "                            <input type=\"text\" name=\"articleTypeId\" class=\"w-100 form-control\"" +
                    "                                   disabled value=" + res[i].articleTypeId + ">\n" +
                    "                        </div>\n" +
                    "                        <div class=\"col-4 border\">\n" +
                    "                            <input type=\"text\" name=\"articleTypeName\" class=\"w-100 form-control\"" +
                    "                                   value=" + res[i].articleTypeName + " >\n" +
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

        //添加文章类型按钮点击事件
        $("#btn_summit_article_type").click(function () {
            var articleTypeName = $("#input_articleType").val();
            if (vertifyArticleTypeNameLength(articleTypeName) == false) {
                toast('添加失败', '文章类型长度不合理');
                return;
            }
            var userId = $("input[name='userId']").val();

            sendUpdateOrAddArticleTypeRequest(userId, 0, articleTypeName, '添加失败', '文章类型已存在');
        })


        //保存文章类型按钮点击事件
        function btnSaveClickEvent() {
            //找到每行的起始div
            var norDiv = $(this).parent().parent();
            var userId = $("input[name='userId']").val();
            var articleTypeId = norDiv.find("input[name='articleTypeId']").val();
            var articleTypeName = norDiv.find("input[name='articleTypeName']").val();


            if (vertifyArticleTypeNameLength(articleTypeName) == false) {
                toast('保存失败', '文章类型长度不合理');
                return;
            }

            sendUpdateOrAddArticleTypeRequest(userId, articleTypeId, articleTypeName, '保存失败', '文章类型已存在');
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
                        if (res===null||res===""){
                            toast("删除失败","文章类型使用中")
                        }
                        window.location.href = "/articleType";
                    }
                }
            )
        }

        //发送更新或添加文章类型请求
        function sendUpdateOrAddArticleTypeRequest(userId, articleTypeId, articleTypeName, header, body) {
            console.log(userId)
            $.ajax({
                url: "/addArticleType",
                type: "POST",
                data: {"userId": userId, "articleTypeId": articleTypeId, "articleTypeName": articleTypeName},
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