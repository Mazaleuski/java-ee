<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Кабинет</title>
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
            <a href="${contextPath}/account">
                <button class="btn btn-outline-success m-1" type="button">Кабинет</button>
            </a>
            <a href="${contextPath}/cart/open">
                <button class="btn btn-outline-success m-1" type="button">Корзина</button>
            </a>
        </form>
    </div>
</nav>
<c:if test="${not empty info}">
    <p style="text-align: center" class="text-danger">${info}</p>
    <br>
    <div class="container-fluid" style="text-align: center">
        <a href="${contextPath}/login">
            <button class="btn btn-outline-success" type="button">Вход</button>
        </a></div>
</c:if>
<c:if test="${not empty user}">
    <div class="container-fluid" style="text-align: center">
        <p>Личные данные:</p>
        <div class="row">
            <div class="col m-1" style="text-align: right">
                <p>Имя пользователя: ${user.getName()}</p>
                <p>Фамилия пользователя: ${user.getSurname()}</p></div>
            <div class="col m-1" style="text-align: left">
                <p>Дата рождения: ${user.getBirthday()}</p>
                <p>Почта: ${user.getEmail()}</p></div>
        </div>
    </div>
    <div class="container-fluid" style="text-align: center">
        <p>История заказов:</p>
        <p>Дата заказа: ${date}</p>
        <p>Номер заказа: ${number}</p>
        <c:if test="${not empty orders}">
            <c:forEach items="${orders}" var="product">
                <div class="card w-50 m-1" type="product">
                    <div class="card-body">
                        <div class="row">
                            <div class="col m-1">
                                <img class="card-img"
                                     style="width:70px;height:70px"
                                     src="${contextPath}/images/mobile/${product.getImagePath()}"
                                     alt=${product.getImagePath()}></div>
                            <div class="col m-1" style="text-align: center">
                                <p>${product.getName()}</p></div>
                            <div class="col m-1" style="text-align: center">
                                <p>${product.getDescription()}</p></div>
                            <div class="col m-1" style="text-align: center">
                                <p>${product.getPrice()}</p></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</c:if>
</body>
</html>
