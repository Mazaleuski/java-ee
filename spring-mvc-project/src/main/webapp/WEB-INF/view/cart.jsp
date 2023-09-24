<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Корзина</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
<h2 style="text-align: center">Корзина</h2>
<p></p>
<div class="container-fluid mb-4" style="text-align: center">
    <c:if test="${not empty cart}">
        <c:forEach items="${cart.getProducts()}" var="product">
            <div class="card w-50 m-1" type="product">
                <div class="card-body">
                    <div class="card-body">
                        <div class="row">
                            <div class="col m-1">
                                <img
                                        class="card-img"
                                        style="width:100px;height:100px"
                                        src="${contextPath}/images/mobile/${product.getImagePath()}"
                                        alt=${product.getImagePath()}></div>
                            <div class="col m-1" style="text-align: left"><p></p>
                                <p>${product.getName()}</p>
                                <p>${product.getDescription()}</p>
                                <p>${product.getPrice()}</p></div>
                            <div class="col m-1"><a
                                    href="${contextPath}/cart/delete?product_id=${product.getId()}">
                                <p></p>
                                <button class="btn btn-danger m-2" style="text-align: right" type="button">Удалить
                                </button>
                            </a></div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
<div class="container-fluid mb-4" style="text-align: center"><a href="${contextPath}/order"
                                                                style="text-align: right"><p></p>
    <button class="btn btn-success m-2" type="button">Оформить заказ</button>
</a></div>
</body>
</html>
