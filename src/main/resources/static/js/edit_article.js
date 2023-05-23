$(function () {
    tinymce.init({
        selector: 'textarea#articleContent',
    });
    $("#submit").click(function () {
        console.log(tinymce.activeEditor.getContent());
        $.ajax({
            method: 'post',
            url: "/post",
            data: {
                "context": tinymce.activeEditor.getContent()
            }
        }).then((res) => {
            $("#h").html(tinymce.activeEditor.getContent());
            // console.log(res.data);
        })
    })
});