<#include "inc/header.ftl">
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<table width="60%" align="center" border="1" style="margin-top: 30px">
    <tr>
        <td colspan="5">
            <form id="cha" method="post">
                <input type="text" name="id" id="id" placeholder="输入id进行查询"/>
                <button id="search">搜索</button>
                <a href="/student/page"><input type="button" value="刷新"></a>
                <a href="/student/add"><input type="button" value="添加"></a>
            </form>
        </td>
    </tr>
    <tr>
        <td width="1">id</td>
        <td width="3">姓名</td>
        <td width="2">性别</td>
        <td width="5">爱好</td>
        <td width="3">操作</td>
    </tr>
<#list student.list as s>
    <tr>
        <td width="1">${s.id!}</td>
        <td width="3">${s.name!}</td>
        <td width="2">${(s.sex =='0' )? string('男','女')?html}</td>
        <td width="5">${s.hobbies!}</td>
        <td width="3">
            <a href="/student/updatepage/${s.id}">修改</a>
            <a href="/student/del/${s.id}">删除</a>
        </td>
    </tr>
</#list>
    <tr align="right">
        <td colspan="5">
        <#--pageShow 总页数, 当前页, 请求服务地址, 页面大小默认15, 显示最多分页个数, 请求服务地址参数默认为p, 分页样式（可选：gray（默认）、orange、blue）-->
       <#import "/inc/macros.ftl" as macro/>
        <@macro.page pageInfo=student url="${root}/student/page" parameter=""/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    $("#search").click(function () {
        var id = $("#id").val();
        if (id == '' || id == null) {
            alert("请输入id");
            return false;
        }
        var url = "/student/page/";
        $("#cha").attr("action", url);
    })
</script>