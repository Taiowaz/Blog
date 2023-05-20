$(function () {
    //todo 登录测试
    //设置管理员/用户标题切换提示
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

    //错误提示
    function toast(header, body) {
        $('.toast-header:first strong').text(header);
        $('.toast-body:first').text(body);

        const toastLiveExample = $('#liveToast');
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        toastBootstrap.show();
    }


    //点击切换管理员或用户模式
    $("#mode_shift_admin_regular_user").click(function () {
        button = $("#mode_shift_admin_regular_user");
        if (button.text() == '用户登录/注册') {
            button.text('管理员登录');
            $("#register").hide();
            $('#login').text('管理员登录');
        } else {
            button.text('用户登录/注册');
            $("#register").show();
            $('#login').text('用户登录');
        }
    })


    //登录按钮点击事件
    $("#login").click(function () {
        let form = $('#form_login_register');
        let account = $('#input_account').val();
        let password = $('#input_password').val();

        if (vertify(account, password) == false) {
            toast('登录失败', '账号或密码长度不合理');
            return;
        }

        data = {"account": account, "password": password};
        console.log(data);

        let url;
        //判断是否是用户还是管理员
        if ($('#mode_shift_admin_regular_user').text().trim() == '用户登录/注册') {
            url = '/loginRegularUser';
        } else {
            url = '/loginAdmin';
        }
        console.log('url' + url);

        $.ajax({
                type: 'post',
                url: url,
                data: data,
                success: function (res) {
                    console.log(res);
                    loginRequestSuccess(res);
                    // getSession();
                }
            }
        )
    })

    //todo session保存登录信息
    //登录请求成功函数
    function loginRequestSuccess(res) {
        if (res == null || res == '') {
            toast('登录失败', '账号或密码错误');
        } else {
            toast('登录成功', '欢迎进入博客网站');

            // formatLoginRes(res);
            //todo 跳转页面
            // $(location).attr('href', '/index.html');
        }
    }

    //处理登录请求成功结果函数
    // function formatLoginRes(res) {
    //     res.forEach(function (user) {
    //         $.session.set('avatarBase64', user.avatarBase64);
    //         $.session.set('account', user.account);
    //         $.session.set('avatarType', user.avatarType);
    //     })
    // }

    //获取session
    function getSession() {
        $.ajax({
            type: 'get',
            url: '/getSession',
            success: function (res) {
                console.log(res);
            }
        })
    }

    $("#register").click(function () {
        let account = $('#input_account').val();
        let password = $('#input_password').val();

        if (vertify(account, password) == false) {
            toast('注册失败', '账号或密码长度不合理');
            return;
        }

        data = {"account": account, "password": password};
        console.log(data);

        $.ajax({
                type: 'post',
                url: '/registerOrUpdateRegularUser',
                data: data,
                success: function (res) {
                    console.log(res);
                    registerRequestSuccess(res)
                }
            }
        )

    })

    //注册请求成功函数
    function registerRequestSuccess(res) {
        if (res == null || res == '') {
            toast('注册失败', '用户已存在');
            return;
        }
        toast('注册成功', '欢迎加入博客');
    }


    //验证账号与密码是否合理
    function vertify(account, password) {
        if (account.length >= 2 && account.length <= 8 && password.length >= 6 && password.length <= 20) {
            return true;
        } else {
            return false;
        }
    }

})