<html>
<head>
    <title>Mybatis分页插件 - 测试页面</title>
    <script src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
    <link href="${request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .pageDetail {
            display: none;
        }

        .show {
            display: table-row;
        }
    </style>
    <script>
        $(function () {
            $('#list').click(function () {
                $('.pageDetail').toggleClass('show');
            });
        });

    </script>
</head>
<body>
<div class="wrapper">
    <div class="middle">
        <h1 style="padding: 50px 0 20px;">用户列表</h1>

        <form action="${request.contextPath}/users" method="post">
            <table class="gridtable" style="width:100%;">
                <tr>
                    <th>用户名称：</th>
                    <td><input type="text" name="name"
                               value="<#if queryParam.name??>${queryParam.name}</#if>"/></td>
                    <th>用户年龄：</th>
                    <td><input type="text" name="age"
                               value="<#if queryParam.age??>${queryParam.age}</#if>"/></td>
                    <td rowspan="2"><input type="submit" value="查询"/></td>
                </tr>
                <tr>
                    <th>页码：</th>
                    <td><input type="text" name="page" value="${page}"/></td>
                    <th>页面大小：</th>
                    <td><input type="text" name="rows" value="${rows}"/></td>
                </tr>
            </table>
        </form>
    <#if pageInfo??>
        <table class="gridtable" style="width:100%;">
            <tr>
                <th colspan="2">分页信息 - [<a href="javascript:;" id="list">展开/收缩</a>]</th>
            </tr>
            <tr class="pageDetail">
                <th style="width: 300px;">当前页号</th>
                <td>${pageInfo.pageNum}</td>
            </tr>
            <tr class="pageDetail">
                <th>页面大小</th>
                <td>${pageInfo.pageSize}</td>
            </tr>
            <tr class="pageDetail">
                <th>起始行号(>=)</th>
                <td>${pageInfo.startRow}</td>
            </tr>
            <tr class="pageDetail">
                <th>终止行号(<=)</th>
                <td>${pageInfo.endRow}</td>
            </tr>
            <tr class="pageDetail">
                <th>总结果数</th>
                <td>${pageInfo.total}</td>
            </tr>
            <tr class="pageDetail">
                <th>总页数</th>
                <td>${pageInfo.pages}</td>
            </tr>
            <tr class="pageDetail">
                <th>第一页</th>
                <td>${pageInfo.navigateFirstPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>前一页</th>
                <td>${pageInfo.prePage}</td>
            </tr>
            <tr class="pageDetail">
                <th>下一页</th>
                <td>${pageInfo.nextPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>最后一页</th>
                <td>${pageInfo.navigateLastPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否为第一页</th>
                <td>${pageInfo.isFirstPage?c}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否为最后一页</th>
                <td>${pageInfo.isLastPage?c}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否有前一页</th>
                <td>${pageInfo.hasPreviousPage?c}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否有下一页</th>
                <td>${pageInfo.hasNextPage?c}</td>
            </tr>
        </table>
        <table class="gridtable" style="width:100%;">
            <#if msg??>
                <tr style="color:red;">
                    <th colspan="5">${msg}</th>
                </tr>
            </#if>
        </table>
        <table class="gridtable" style="width:100%;">
            <thead>
            <tr>
                <th colspan="4">查询结果 - [<a href="${request.contextPath}/users/add">新增用户</a>]</th>
            </tr>
            <tr>
                <th>ID</th>
                <th>用户名称</th>
                <th>用户邮箱</th>
                 <th>用户年龄</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#list pageInfo.list as user>
                <tr>
                    <td>${user.id?c}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.age}</td>
                    <td style="text-align:center;">[<a
                            href="${request.contextPath}/users/view/${user.id?c}">修改</a>] -
                        [<a href="${request.contextPath}/users/delete/${user.id?c}">删除</a>]
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
        <table class="gridtable" style="width:100%;text-align: center;">
            <tr>
                <#if pageInfo.hasPreviousPage>
                    <td>
                       <a href="${request.contextPath}/users?page=1&rows=${pageInfo.pageSize}&name=<#if queryParam.name??>${queryParam.name}</#if>&age=<#if queryParam.age??>${queryParam.age}</#if>">首页</a>
                    	
                    </td>
                    <td>
                        <a href="${request.contextPath}/users?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}&name=<#if queryParam.name??>${queryParam.name}</#if>&age=<#if queryParam.age??>${queryParam.age}</#if>">前一页</a>
                    </td>
                </#if>
                <#list pageInfo.navigatepageNums as nav>
                    <#if nav == pageInfo.pageNum>
                        <td style="font-weight: bold;">${nav}</td>
                    </#if>
                    <#if nav != pageInfo.pageNum>
                        <td>
                            <a href="${request.contextPath}/users?page=${nav}&rows=${pageInfo.pageSize}&name=<#if queryParam.name??>${queryParam.name}</#if>&age=<#if queryParam.age??>${queryParam.age}</#if>">${nav}</a>
                        </td>
                    </#if>
                </#list>
                <#if pageInfo.hasNextPage>
                    <td>
                        <a href="${request.contextPath}/users?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}&name=<#if queryParam.name??>${queryParam.name}</#if>&age=<#if queryParam.age??>${queryParam.age}</#if>">下一页</a>
                    </td>
                    <td>
                        <a href="${request.contextPath}/users?page=${pageInfo.pages}&rows=${pageInfo.pageSize}&name=<#if queryParam.name??>${queryParam.name}</#if>&age=<#if queryParam.age??>${queryParam.age}</#if>">尾页</a>
                    </td>
                </#if>
            </tr>
        </table>
    </#if>
    </div>
    <div class="push"></div>
</div>
</body>
</html>