<#assign header>
    <link href="res/jquery-easyui-1.4.5/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="res/jquery-easyui-1.4.5/themes/icon.css" rel="stylesheet" type="text/css"/>
    <script src="res/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script src="res/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
    <script src="res/js/events.js"></script>
</#assign>

<#assign panels>
    <#include "easyui/header.ftl"/>
    <#include "easyui/menu.ftl"/>
    <#include "easyui/footer.ftl"/>
    <div data-options="region:'center'">
        <div class="easyui-tabs" id="content-tab" style="height:100%;">
            <div title="主页">
                <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul>
            </div>
        </div>
    </div>
</#assign>
<#include "easyui/easyui-layout.ftl"/> 