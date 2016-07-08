<#assign header>
    <link href="res/css/table.css" rel="stylesheet" type="text/css"/> 
</#assign>
<#assign title>详情查看</#assign>

<#assign content>
    <#include "public/menu.ftl"/>
    <div class="content">
        <form action="upload.action" method="POST" enctype="multipart/form-data">
            
            <table class="hovertable">
                <tr>
                    <th>选择文件</th>
                    <td><input type="file" name="uploadFile" accept="image/png, image/jpeg" ></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input type="submit" value="上传"></td>
                </tr>                
            <table>
        </form>
        
    </div>
</#assign>
<#include "public/layout.ftl"/> 