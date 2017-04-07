<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

   <%-- <link rel="stylesheet"  href="<c:url value="/bootstrap/css/bootstrap.css"/>">--%>
    <style type="text/css"><%@include file="/bootstrap/css/bootstrap.css" %>body{padding-top:40px;padding-bottom:40px;background-color:#f5f5f5;}.form-signin{max-width:300px;padding:19px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}.form-signin .form-signin-heading,.form-signin .checkbox{margin-bottom:10px;}.form-signin input[type="text"],.form-signin input[type="password"]{font-size:16px;height:auto;margin-bottom:15px;padding:7px 9px;}</style>
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/css/login/bootstrap-responsive.css"/>">





</head>
<body>
<div class="container">
    <form class="form-signin" action="/postProcPage" method="post">
        <h2 class="form-signin-heading">Please join</h2>
        <input type="text" class="input-block-level" placeholder="Username" name="username" required>
        <input type="text" class="input-block-level" placeholder="Email address" name="email" required>
        <input type="password" class="input-block-level" placeholder="Password" name="password" required>

        <button class="btn btn-large btn-primary" type="submit">Registration</button>
    </form>
</div>



</body>
</html>