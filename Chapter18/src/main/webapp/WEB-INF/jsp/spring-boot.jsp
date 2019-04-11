<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>首页</title>
</head>
<body style="background-image: none;">
<div class="body_wrap" style="text-align:center;width:800px;">
    <div class="container" >
    	<!-- http://tengj.top/2017/03/13/springboot5/ -->
        <div class="alert alert-success text-center" role="alert">Sptring Boot学习资源大奉送，爱我就关注嘟嘟公众号：嘟爷java超神学堂</div>
        <table class="table table-striped table-bordered" border="1">
            <thead>   
	            <tr>
	                <td>作者</td>
	                <td>教程名称</td>
	                <td>地址</td>
	            </tr>
            </thead>
            <tbody>
            <c:forEach var="learn" items="${learnList}">
                <tr class="text-info">
                    <td >${learn.author}</td>
                    <td >${learn.title}</td>
                    <td>
                    	<a href="${learn.url}" class="btn btn-search btn-green" target="_blank"><span>点我</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
