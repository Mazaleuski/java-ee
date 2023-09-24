<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
        Домашняя страница
    </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-light">
    <div class="container-fluid">
        <form class="form-inline">
        </form>
        <form class="form-inline my-2 my-lg-0">
            <a href=${contextPath}"/search">
                <button class="btn btn-outline-success m-1" type="button">Поиск</button>
            </a>
            <a href=${contextPath}"/account">
                <button class="btn btn-outline-success m-1" type="button">Кабинет</button>
            </a>
            <a href="${contextPath}/cart/open">
                <button class="btn btn-outline-success m-1" type="button">Корзина</button>
            </a>
        </form>
    </div>
</nav>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<p></p>
<h1 style="text-align: center">Каталог товаров</h1>
<p></p>
<div class="container-fluid">
    <c:if test="${not empty categories}">
        <div class="row">
            <c:forEach items="${categories}" var="category">
                <div class="card w-25 m-1" style="text-align: center" type="category">
                    <div class="card-body">
                        <a href="${contextPath}/category/${category.getId()}"><img
                                class="card-img" style="width:160px;height:160px"
                                src="${contextPath}/images/${category.getImagePath()}"
                                alt=${category.getImagePath()}></a>
                    </div>
                    <a href="${contextPath}/category/${category.getId()}"
                       class="btn">${category.getName()}</a>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>