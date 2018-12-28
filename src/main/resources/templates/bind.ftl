<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>绑定用户信息</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.3/style/weui.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.1/css/jquery-weui.min.css">
</head>
<body>

<form id="bind">
    <input name="openid" type="hidden" value="${(user.openId)!}">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">微信昵称</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" name="nickName" readonly value="${(user.nickname)!}">
            </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd">
                <label class="weui-label">员工ID</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" id="flUserCode" name="flUserCode" type="text" placeholder="请输入员工ID">
            </div>
        </div>

        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd">
                <label class="weui-label">FL密码</label>
            </div>
            <div class="weui-cell__bd">
                <input type="password" id="passWord" name="passWord" class="weui-input" placeholder="请输入FL密码">
            </div>
        </div>
    </div>
</form>

<a href="javascript:;" class="weui-btn weui-btn_primary" onclick="bind()">绑定用户信息</a>

</body>
<!-- body 最后 -->
<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.2.1/js/jquery-weui.min.js"></script>

<script>
    function bind(){
        var passWord = $('#passWord').val();
        var flUserCode=$('#flUserCode').val();

        if(flUserCode==''){
            $.toptip('请输入员工ID', 'error');
            return false;
        }
        if (passWord==''){
            $.toptip('请输入密码', 'error');
            return false;
        }
        $.confirm("是否绑定", function() {
            //点击确认后的回调函数
            var data=$('#bind').serialize();
            $.ajax({
                async: false,
                url: "/bind/validate",
                dataType: "JSON",
                type: "post",
                data: data,
                success: function (res) {
                    if (res.code == 12000) {
                        $.ajax({
                            async: false,
                            url: "/bind/bindUser",
                            dataType: "JSON",
                            type: "post",
                            data: data,
                            success: function (res) {
                                if (res.code == 12000) {
                                    $.toast("绑定成功");
                                } else {
                                    $.toptip('绑定失败', 'error');
                                }
                            }
                        })
                    } else {
                        $.toptip('员工ID或密码错误', 'error');
                    }
                }
            })
        }, function() {
            //点击取消后的回调函数
        });
    }

</script>
</html>