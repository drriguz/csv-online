<#assign header>
    <link href="res/css/table.css" rel="stylesheet" type="text/css"/> 
</#assign>
<#assign title>编辑</#assign>

<#assign content>
    <#include "public/menu.ftl"/>
    <div class="content">
        <form action="save.do" method="POST">
            <input name="userId" type="hidden" value="${user.userId}">
            <table class="hovertable">
                <tr>
                    <th>First Name</th>
                    <td><input name="firstName" value="${user.firstName}"></td>
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
                    <td><input name="phone1" value="${user.phone1}"></td>
                </tr>
                <tr>
                    <th>Phone2</th>
                    <td><input name="phone2" value="${user.phone2}"></td>
                </tr>
                <tr>
                    <th>Web</th>
                    <td><input name="web" value="${user.web}"></td>
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