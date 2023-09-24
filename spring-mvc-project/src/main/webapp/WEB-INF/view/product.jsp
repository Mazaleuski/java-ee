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
            <a href=${contextPath}/cart/open>
                <button class="btn btn-outline-success m-1" type="button">Корзина</button>
            </a>
        </form>
    </div>
</nav>
<h2 style="text-align: center">${product.getName()}</h2>
<p></p>
<div class="card w-50 m-1" style="text-align: center" type="product">
    <div class="card-body">
        <div class="row">
            <div class="col m-1"><img
                    class="card-img"
                    style="width:350px;height:350px"
                    src="${contextPath}/images/mobile/${product.getImagePath()}"
                    alt=${product.getImagePath()}></div>
            <div class="col m-1" style="text-align: left"><p></p>
                <p>${product.getName()}</p>
                <p>${product.getDescription()}</p>
                <p>${product.getPrice()}</p></div>
            <div class="col m-1" style="text-align: center"><p></p></div>
        </div>
        <div class="row" style="text-align: end">
            <div class="col m-1" style="text-align: center"><p></p></div>
            <div class="col m-1" style="text-align: center"><p></p></div>
            <button class="btn btn-outline-success m-2" style="text-align: right" type="button" data-toggle="modal"
                    data-target="#myModal">Купить
            </button>
            <div class="modal" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <p class="modal-title" style="text-align: center">Добавить ${product.getName()} в
                                корзину?</p>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body" style="text-align: center">
                            <a href="${contextPath}/cart/add?product_id=${product.getId()}">
                                <button class="btn btn-outline-success m-2" style="text-align: left" type="button"
                                        data-toggle="modal"
                                        data-target="#myModal">Да
                                </button>
                            </a>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Нет</button>
                        </div>
                    </div>
                </div>
            </div>
            <a href="${contextPath}/cart/open">
                <button class="btn btn-outline-success m-2" style="text-align: right" type="button">Корзина</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
