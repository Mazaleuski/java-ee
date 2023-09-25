<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
        Вход
    </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body><h2 style="text-align:center">Вход</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<p style="text-align:center">Введите имя пользователя и пароль</p>
<div class="container">
    <c:if test="${not empty info}">
        <p style="text-align: center" class="text-danger">${info}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="text-align: center" class="text-danger">${error}</p>
        <div class="form-group" style="text-align: center">
            <a href="${contextPath}/registration">
                <button class="btn btn-danger" type="button">Регистрация</button>
            </a></div>
    </c:if>
    <div class="row">
        <div class="col-md-8 offset-md-5">
            <form method="post" class="needs-validation" novalidate>
                <input type="hidden" name="command" value="login">
                <div class="form-group">
                    <label for="email">Пользователь:</label>
                    <input type="text" class="form-control w-25" id="email" placeholder="Введите почту"
                           name="email"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <div class="form-group">
                    <label for="password">Пароль:</label>
                    <input type="text" class="form-control w-25" id="password" placeholder="Введите пароль"
                           name="password"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <button id="loginBtn" type="submit" class="btn btn-success">Вход</button>
            </form>
        </div>
    </div>
</div>
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
    document.getElementById('loginBtn').disabled = true;
    document.getElementById('email').addEventListener('keyup', e => {
        document.getElementById('loginBtn').disabled = e.target.value === "";
    });
    document.getElementById('password').addEventListener('keyup', e => {
        document.getElementById('loginBtn').disabled = e.target.value === "";
    });
</script>
</body>
</html>