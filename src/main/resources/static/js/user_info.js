$(function () {

    // type = $.session.get('type');
    // if (type == 'admin') {
    //     $('.regular').hide();
    // }

    $('#save').bind('click', function () {
        data = getUserInfo();
        console.log(data);
        
    });

    function getUserInfo() {
        id = $("input[name='id']").val();
        avatar = $("input[name='avatar']").val();
        account = $("input[name='account']").val();
        password = $("input[name='password']").val();

        name = $("input[name='name']").val();
        gender = $("input[name='gender']").val();
        birthday = $("input[name='birthday']").val();
        phoneNumber = $("input[name='phoneNumber']").val();
        email = $("input[name='email']").val();
        detail = $("input[name='detail']").val();
        registerTime = $("input[name='registerTime']").val();
        lastModifyTime = $("input[name='lastModifyTime']").val();

        data = {"id": id, "avatar": avatar, "account": account, "password": password}

        // if (type == 'regular') {
            data.name = name;
            data.gender = gender;
            data.birthday = birthday;
            data.phoneNumber = phoneNumber;
            data.email = email;
            data.detail = detail;
            data.registerTime = registerTime;
            data.lastModifyTime = lastModifyTime;
        // }
        return data;
    }

})