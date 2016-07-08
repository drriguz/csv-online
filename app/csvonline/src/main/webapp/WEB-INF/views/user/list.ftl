<#assign header>
    <link href="res/css/table.css" rel="stylesheet" type="text/css"/> 
</#assign>

<#assign content>
    <#include "public/menu.ftl"/>
    <div class="content">
        <div class="query">
            <form action="list.do" method="GET">
            <table>
                <tr>
                    <td><label>用户名</label><input name="name" value="${RequestParameters.name}"></td>
                    <td><label>邮箱</label><input name="email" value="${RequestParameters.email}"></td>
                    <td><input type="submit" value="查询"></td>
                </tr>
            </table>
            </form>
        </div>
        <p>查询到结果${users?size}条</p>
        <table class="hovertable">
            <tr>
                <th style="width:100px">ID</th>
                <th style="width:200px">姓名</th>
                <th style="width:150px">邮箱</th>
                <th style="width:200px">所在地址</th>
                <th>操作</th>
            </tr>
        <#list users as u>  
            <tr>                    
                <td><a href="view.do?id=${u.userId}">${u.userId}</a></td>
                <td>${u.firstName} ${u.lastName}</td>
                <td>${u.email}</td>
                <td>${u.address}</td>
                <td><a href="edit.do?id=${u.userId}">修改</a> <a href="delete.do?id=${u.userId}">删除</a></td>
            </tr>
        </#list> 
        </table>
    </div>
</#assign>
<#include "public/layout.ftl"/> 