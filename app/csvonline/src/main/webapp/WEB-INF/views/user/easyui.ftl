<#assign header>
    <link href="res/jquery-easyui-1.4.5/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <script src="res/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script src="res/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
    
</#assign>

<#assign content>
    
    <div class="content">
        <table id="grid" title="用户列表" class="easyui-datagrid" style=""
            url="get_users.php"
            toolbar="#toolbar"
            rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="firstname" width="50">First Name</th>
                    <th field="lastname" width="50">Last Name</th>
                    <th field="phone" width="50">Phone</th>
                    <th field="email" width="50">Email</th>
                </tr>
            </thead>
        </table>
    </div>
</#assign>
<#include "public/layout.ftl"/> 