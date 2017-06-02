<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><c:out value="${category}"/></title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/static/css/bootstrap.css"/>" rel="stylesheet">


</head>

<body>

<div class="container">



    <!-- Jumbotron -->
    <div class="jumbotron">
        <h1>Hello <c:out value="${user.username}"/></h1>
        <p><a class="btn btn-lg btn-success" href="<c:url value="/login"/>" role="button">logout</a></p>
        <c:if test="${user.role eq 'admin'}">
            <a class="btn btn-lg btn-success" href="<c:url value="/users"/>" role="button">view all users</a>
        </c:if>
    </div>

    <!-- Example row of columns -->
    <div class="row">

        <c:forEach var="entry" items="${products}">
            <div class="col-lg-4">
                <form action="/" method="get">
                    <h2><c:out value="${entry.key}"/></h2>
                    <input name="name" value="<c:out value="${entry.key}"/>" type="hidden">
                    <p><c:out value="${entry.value}"/></p>
                    <input name="description" value="<c:out value="${entry.value}"/>" type="hidden">

                    <c:if test="${user.role eq 'admin'}">
                        <input class="btn btn-primary" type="submit" value="Edit &raquo;">
                    </c:if>
                </form>
            </div>
        </c:forEach>


        <c:if test="${user.role eq 'admin'}">
            <p><a class="btn btn-primary" href="#" role="button">Add category &raquo;</a></p>
        </c:if>



    </div>

    <!-- Site footer -->
    <footer class="footer">
        <p>&copy; Company 2017</p>
    </footer>

</div> <!-- /container -->

</body>
</html>
