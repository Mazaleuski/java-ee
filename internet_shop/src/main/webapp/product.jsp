<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${product.getName()}</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-light">
    <div class="container-fluid">
        <form class="form-inline">
            <a href="home.jsp">
                <button class="btn btn-outline-success" type="button">Главная</button>
            </a>
        </form>
        <form class="form-inline my-2 my-lg-0">
            <a href="#">
                <button class="btn btn-outline-success m-1" type="button">Кабинет</button>
            </a>
            <a href="#">
                <button class="btn btn-outline-success m-1" type="button">Корзина</button>
            </a>
        </form>
    </div>
</nav>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2 style="text-align: center">${product.getName()}</h2>
<div class="card w-50 m-1" style="text-align: center" type="product">
    <div class="card-body">
        <div class="row">
            <div class="col m-1"><img
                    class="card-img"
                    style="width:350px;height:350px"
                    src="${contextPath}/images/mobile/${product.getImageName()}"
                    alt=${product.getImageName()}></div>
            <div class="col m-1" style="text-align: left"><p></p>
                <p>${product.getName()}</p>
                <p>${product.getDescription()}</p>
                <p>${product.getPrice()}</p></div>
            <div class="col m-1" style="text-align: center"><p></p></div>
        </div>
        <div class="row" style="text-align: end">
            <div class="col m-1" style="text-align: center"><p></p></div>
            <div class="col m-1" style="text-align: center"><p></p></div>
            <a href="#">
                <button class="btn btn-outline-success m-2" style="text-align: right" type="button">Купить</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
