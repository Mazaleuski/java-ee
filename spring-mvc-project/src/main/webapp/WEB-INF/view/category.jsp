<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${category}</title>
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
            <a href="${contextPath}/home">
                <button class="btn btn-outline-success" type="button">Главная</button>
            </a>
        </form>
        <form class="form-inline my-2 my-lg-0">
            <a href="${contextPath}/search">
                <button class="btn btn-outline-success m-1" type="button">Поиск</button>
            </a>
            <a href="${contextPath}/account">
                <button class="btn btn-outline-success m-1" type="button">Кабинет</button>
            </a>
            <a href="${contextPath}/cart/open">
                <button class="btn btn-outline-success m-1" type="button">Корзина</button>
            </a>
        </form>
    </div>
</nav>
<div style="text-align: center">${message}</div>
<c:if test="${not empty products}">
    <h2 style="text-align: center">${category}</h2>
    <div class="container-fluid mb-4">
        <c:forEach items="${products}" var="product">
            <div class="card w-25 m-1" type="product">
                <div class="card-body">
                    <div class="row">
                        <div class="col m-1"><a
                                href="${contextPath}/products/${product.getId()}"><img
                                class="card-img"
                                style="width:140px;height:140px"
                                src="${contextPath}/images/mobile/${product.getImagePath()}"
                                alt=${product.getImagePath()}></a></div>
                        <div class="col m-1" style="text-align: center"><p></p>
                            <a href="${contextPath}/products/${product.getId()}">
                                <p>${product.getName()}</p>
                            </a>
                            <p>${product.getDescription()}</p>
                            <p>${product.getPrice()}</p></div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
