<#macro page pageInfo url parameter="" anchor="">
<#if pageInfo.list?size gt 0>
    <div class="head-to-pager">
        <div class="pagination">
            <div class="pager clearfix">
        <#--pageInfo.page-->
        <#--pageInfo.pages-->
                        <#--<!--如果只有一页&ndash;&gt;-->
                         <#--<a class="item " href="javascript:;" onclick="page(1)"> 首页 </a>-->
                        <#if (pageInfo.pages) == 1>
                            <a class="item active" href="javascript:" onclick="page(1)"> 1 </a>
                        </#if>
                <!--页数大于1-->
                        <#if (pageInfo.pages) gt 1>
                            <!--当前页不在1上-->
                            <#if (pageInfo.pageNum) gt 1>
                                <a class="item " href="javascript:;" onclick="page(1)">首页</a>
                                  <a class="item " href="javascript:;" onclick="page(${pageInfo.pageNum-1})">上一页 </a>
                            </#if>

                            <#assign beforeNum=(((pageInfo.pageNum-5) gt 0)?then(5,pageInfo.pageNum))>
                            <#assign endNum=(((pageInfo.pageNum+3) lt (pageInfo.pages+1))?then(3,pageInfo.pages-pageInfo.pageNum))>
                            <#assign ber=(5-beforeNum)>
                            <#assign end=(3-endNum)>
                            <#if pageInfo.pages gt 8>
                                <#assign before=((pageInfo.pageNum-5+end) gt 0)?then((5+end),pageInfo.pageNum)>
                                <#else>
                                <#assign before=pageInfo.pageNum>
                            </#if>
                            <#assign endPage=((pageInfo.pageNum+3+ber) lte pageInfo.pages)?then((3+ber),(pageInfo.pages-pageInfo.pageNum))>
                            <!--判断当前页往前的页数-->
                                <#list (before-1)..0 as t>
                                                <a class=" item ${(pageInfo.pageNum-t == pageInfo.pageNum)?then("active","")} " href="javascript:" onclick="page(${pageInfo.pageNum-t})"> ${pageInfo.pageNum-t} </a>
                                </#list>
                            <!--展示当前页数往后的页-->
                            <#if endPage!=0>
                                    <#list 1..endPage as t>
                                            <a class="item " href="javascript:" onclick="page(${pageInfo.pageNum+t})"> ${pageInfo.pageNum+t} </a>
                                    </#list>
                            </#if>

                            <#if (pageInfo.pageNum) lt (pageInfo.pages) >
                                <a class="item " href="javascript:" onclick="page(${pageInfo.pageNum+1})"> 下一页 </a>
                                 <a class="item " href="javascript:" onclick="page(${pageInfo.pages})">尾页</a>
                            </#if>
                        </#if>

            </div>
        </div>
    </div>
    <script>
        function page(pageNum) {
            window.location.href = '${base.contextPath}${url}<#if parameter != ''>?${parameter!}&<#else>?</#if>pageNum=' + pageNum+'${anchor!}${(uploadingShow!="")?then("&uploadingShow=1","")}';
        }
    </script>
</#if>
</#macro>