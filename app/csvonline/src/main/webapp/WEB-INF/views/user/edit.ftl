<#assign header>
    <link href="res/css/table.css" rel="stylesheet" type="text/css"/>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    
    <style>
    .error{
        color:red;
    }
    </style>
    <script>
    $().ready(function() {
        jQuery.validator.addMethod("isMobile", function(value, element) {       
            var length = value.length;   
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
            return this.optional(element) || (length == 11 && mobile.test(value));       
        }, "请正确填写您的手机号码"); 
        $("#userForm").validate();
    }); 
    </script>
</#assign>
<#assign title>编辑</#assign>

<#assign content>
    <#include "public/menu.ftl"/>
    <div class="content">
    <form id="testForm" action="test.do" method="POST">
    <input name="id">
    <input name="fromDate">
    <input type="submit" value="提交">
    </form>
        <form id="userForm" action="save.action" method="POST">
            <input name="userId" type="hidden" value="${user.userId}">
            <table class="hovertable">
                <tr>
                    <th>First Name</th>
                    <td><input name="firstName" value="${user.firstName}" required="true"></td>
                </tr>
                <tr>
                    <th>Last Name</th>
                    <td><input name="lastName" value="${user.lastName}"></td>
                </tr>
                <tr>
                    <th>Company Name</th>
                    <td><input name="companyName" value="${user.companyName}"></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><input name="address" value="${user.address}"></td>
                </tr>
                <tr>
                    <th>City</th>
                    <td><input name="city" value="${user.city}"></td>
                </tr>
                <tr>
                    <th>Country</th>
                    <td><input name="country" value="${user.country}"></td>
                </tr>
                <tr>
                    <th>Postal</th>
                    <td><input name="postal" value="${user.postal}"></td>
                </tr>
                <tr>
                    <th>Phone1</th>
                    <td><input name="phone1" value="${user.phone1}" isMobile="true"></td>
                </tr>
                <tr>
                    <th>Phone2</th>
                    <td><input name="phone2" value="${user.phone2}"></td>
                </tr>
                <tr>
                    <th>Web</th>
                    <td><input name="web" value="${user.web}" url="true"></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input type="submit" value="提交"></td>
                </tr>
            <table>
        </form>
    </div>
</#assign>
<#include "public/layout.ftl"/> 