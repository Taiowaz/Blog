$(function () {
    //设置管理员/用户标题切换提示
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

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


    $("#login").click(function () {
        let form = $('#form_login_register');
        let account = $('#input_account').text();
        let password = $('#input_password').text();

        $('.toast-header:first strong').text('登录失败');


        if (vertify(account, password) == false) {
            console.log(account);
            console.log(password);
            $('.toast-body:first').text('账号或密码长度不合理');
            toast();
        }

        //todo json字符串的构成
        //todo 登录注册的完善
        data = '{account:' + account + ', password:' + password + '}';
        data1 = JSON.parse(data);

        console.log(data1)
        
        if ($('#form_login_register').text() == '用户登录') {
            $.ajax({
                    type: 'post',
                    url: '/loginRegularUser',
                    contentType: 'json',
                    data: data,
                    success: function (res) {
                        if (res == 'null') {
                            $('.toast-body:first').text('账号或密码错误');
                            toast();
                        }
                    }
                }
            )
        } else {

        }
    })

    //错误提示
    function toast() {
        const toastLiveExample = $('#liveToast');
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        toastBootstrap.show();
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