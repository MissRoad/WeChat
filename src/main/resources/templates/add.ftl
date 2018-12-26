<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<form id="aou" method="post">
    姓名:<input name="name" id="name" "><br/>
    性别:
    <select id="sex" name="sex">
        <option value="0">男</option>
        <option value="1">女</option>
    </select><br/>
    <button id="au">提交</button>
</form>
<script>

$("#au").click(function () {
    var url = "/student/save"
    $("#aou").attr("action", url);
    $("#aou").submit();
})
</script>