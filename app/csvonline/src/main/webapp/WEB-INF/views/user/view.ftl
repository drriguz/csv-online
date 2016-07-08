<#assign header>
    <link href="res/css/table.css" rel="stylesheet" type="text/css"/> 
</#assign>
<#assign title>详情查看</#assign>

<#assign content>
    <#include "public/menu.ftl"/>
    <div class="content">
        <table class="hovertable">
            <tr>
                <th style="width:200px">ID</th>
                <td>${user.userId}</td>
            </tr>
            <tr>
                <th>First Name</th>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <th>Company Name</th>
                <td>${user.companyName}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${user.address}</td>
            </tr>
            <tr>
                <th>City</th>
                <td>${user.city}</td>
            </tr>
            <tr>
                <th>Country</th>
                <td>${user.country}</td>
            </tr>
            <tr>
                <th>Postal</th>
                <td>${user.postal}</td>
            </tr>
            <tr>
                <th>Phone1</th>
                <td>${user.phone1}</td>
            </tr>
            <tr>
                <th>Phone2</th>
                <td>${user.phone2}</td>
            </tr>
            <tr>
                <th>Web</th>
                <td>${user.web}</td>
            </tr>
        <table>
        
    </div>
</#assign>
<#include "public/layout.ftl"/> 