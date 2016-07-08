<#assign header>
    <link href="res/jquery-easyui-1.4.5/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <script src="res/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script src="res/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
    
</#assign>

<#assign content>
    
    <div class="content">
        <table id="grid" title="用户列表" class="easyui-datagrid" style=""
            url="json.do" method="get"
            toolbar="#toolbar"
            pagination="true"
            pageSize : 10, pageList : [10, 20, 50, 1000],
            rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="userId" width="50">ID</th>
                    <th field="firstName" width="50">First Name</th>
                    <th field="lastName" width="50">Last Name</th>
                    <th field="phone1" width="50">Phone</th>
                    <th field="email" width="50">Email</th>
                </tr>
            </thead>
        </table>
    </div>
</#assign>
<#include "public/layout.ftl"/> 