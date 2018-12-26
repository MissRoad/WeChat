<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<div align="center" style="margin-top: 25em">
    <span style="width: 20em">用户名:</span>
    <input id="name" name="name" placeholder="请输入用户名"><br>
     <span style="width: 20em">密&nbsp;码：</span><input id="password" name="password" placeholder="请输入密码"><br>
    <button id="login">登录</button>
</div>
<script>
    $("#login").click(function () {
        var name = $("#name").val();
        var password = $("#password").val();
        if (name == '') {
            alert("请输入用户名");
            return false;
        } else if (password == "") {
            alert("请输入密码")
            return false;
        }
        $.ajax({
                   async: false,
                   url: "/student/login",
                   dataType: "JSON",
                   type: "post",
                   data: {user: name, password: password},
                   success: function (res) {
                       if (res.code == 12000) {
                           location.href = "/student/page";
                       } else {
                           alert(res.msg);
                       }
                   }
               })
    })

</script>