$(function () {
        $(".present_page").text("我的信息");

        var id;
        var userType;

        var avatarUrl;

        idEle = $("input[name='id']");
        avatarEle = $("input[name='avatar']");
        accountEle = $("input[name='account']");
        passwordEle = $("input[name='password']");

        nameEle = $("input[name='name']");
        birthdayEle = $("input[name='birthday']");
        phoneNumberEle = $("input[name='phoneNumber']");
        emailEle = $("input[name='email']");
        detailEle = $("input[name='detail']");
        registerTimeEle = $("input[name='registerTime']");
        lastModifyTimeEle = $("input[name='lastModifyTime']");

        initUserInfo();
        initView();


        //绑定监听事件，获取头像Base64编码构成的dataUrl
        avatarEle.bind('change', function () {
            var file = this.files[0];
            var reader = new FileReader();

            reader.onload = function (res) {
                avatarUrl = res.target.result;
            }
            reader.readAsDataURL(file);
        })

        function initUserInfo() {
            id = getUrlParam("id");
            userType = getUrlParam("userType");
            getUserInfoFormServer();
        }

        function initView() {
            console.log("userType  " + userType);
            if (userType === 'admin') {
                $('.regular').hide();
            }
        }

        //获取当前用户信息
        function getUserInfoFormServer() {
            var url;
            if (userType === 'regularUser') {
                url = '/findRegularUserById';
            } else {
                url = '/findAdminById';
            }
            $.ajax({
                url: url,
                type: 'post',
                data: {"id": id},
                success: function (res) {
                    if (res !== null && res !== undefined && res !== "" && res !== "null") {
                        bindUserInfo(res);
                    }
                }
            })
        }

        //将用户信息绑定到页面
        function bindUserInfo(res) {
            idEle.val(res.id);
            avatarEle.val(res.avatar);
            accountEle.val(res.account);
            passwordEle.val(res.password);
            nameEle.val(res.name);
            console.log("res  " + res.gender)
            if (res.gender == 'male') {
                $('#male').attr('checked', 'checked');
            }
            if (res.gender == 'female') {
                $('#female').attr('checked', 'checked');
            }
            birthdayEle.val(res.birthday);
            phoneNumberEle.val(res.phoneNumber);
            emailEle.val(res.email);
            detailEle.val(res.detail);
            registerTimeEle.val(res.registerTime);
            lastModifyTimeEle.val(res.lastModifyTime);
        }


        //保存个人信息事件
        $('#save').bind('click', function () {
            data = getUserInfoFromForm();
            console.log("data   " + data.gender);
            if (password.length < 6 || password.length > 20) {
                toast("保存失败", "密码长度小于6或大于20")
                return;
            }
            var url;
            if (userType === 'regularUser') {
                url = '/updateRegularUser';
            } else {
                url = '/updateAdmin';
            }

            $.ajax({
                url: url,
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (res) {
                    if (res !== null && res !== undefined && res !== "" && res !== "null") {
                        btnUserInfoClickEvent();
                    }
                }
            })
        });

        //注销
        $("#logout").bind('click', function () {
            $.ajax({
                url: "/logout",
                type: "get",
                success: function () {
                    window.location.href = "/";
                }
            })
        })

        //从表单中获取数据
        function getUserInfoFromForm() {
            //todo 存疑 不能去除，只用链接获取的id来修改用户信息
            id = idEle.val();
            avatar = avatarUrl;
            account = accountEle.val();
            password = passwordEle.val();

            name = nameEle.val();
            //由于会改变状态，所以需要随用随取
            genderEle = $("input[name='gender']:checked");
            gender = genderEle.val();
            birthday = birthdayEle.val();
            phoneNumber = phoneNumberEle.val();
            email = emailEle.val();
            detail = detailEle.val();

            data = {
                "id": id,
                "avatarUrl": avatar,
                "account": account,
                "password": password,
                "registerTime": "",
                "lastModifyTime": ""
            }

            if (userType === 'regularUser') {
                data.name = name;
                data.gender = gender;
                data.birthday = birthday;
                data.phoneNumber = phoneNumber;
                data.email = email;
                data.detail = detail;
            }
            return data;
        }


    }
)