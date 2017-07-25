<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>学生详情</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>详情</h1>   <a class="btn btn-primary btn-lg" href="/updateStudent/${student.id}">修改 </a>
    <table class="table table-bordered" >
        <tr>
            <th>编号</th>
            <td>${student.id}</td>
        </tr>
        <tr>
            <th>姓名</th>
            <td>${student.name}</td>
        </tr>
        <tr>
            <th>性别</th>
            <td>${student.gender}</td>
        </tr>
        <tr>
            <th>年龄</th>
            <td>${student.age}</td>
        </tr>
        <tr>
            <th>学号</th>
            <td>${student.number} </td>
        </tr>
        <tr>
            <th>联系方式</th>
            <td>${student.tel} </td>
        </tr>
    </table>
</div>
</body>
</html>