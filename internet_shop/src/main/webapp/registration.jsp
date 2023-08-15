<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
        Регистрация
    </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body><h2 style="text-align:center">Регистрация</h2>
<p></p>

<div class="container">
    <c:if test="${not empty message}">
        <p style="text-align: center" class="text-danger">${message}</p>
    </c:if>
    <div class="row">
        <div class="col-md-8 offset-md-5">
            <form method="post" action="register" class="needs-validation" novalidate>
                <div class="form-group">
                    <input type="text" class="form-control w-25" id="name" placeholder="Имя"
                           name="name"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control w-25" id="surname" placeholder="Фамилия"
                           name="surname"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <div class="form-group">
                    <label for="birthday">Дата рождения</label>
                    <input type="text" class="form-control w-25 datepicker" id="birthday" placeholder="YYYY-mm-dd"
                           name="birthday"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control w-25" id="email" placeholder="Введите email"
                           name="email"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control w-25" id="password" placeholder="Введите пароль"
                           name="password"
                           required>
                    <div class="invalid-feedback">Поле не может быть пустым!</div>
                </div>
                <button id="registrationBtn" type="submit" class="btn btn-success">Регистрация</button>
            </form>
        </div>
    </div>
</div>
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            const forms = document.getElementsByClassName('needs-validation');
            const validation = Array.prototype.filter.call(forms, function (form) {
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
    document.getElementById('registrationBtn').disabled = true;
    document.getElementById('name').addEventListener('keyup', e => {
        document.getElementById('registrationBtn').disabled = e.target.value === "";
    });
    document.getElementById('surname').addEventListener('keyup', e => {
        document.getElementById('registrationBtn').disabled = e.target.value === "";
    });
    document.getElementById('birthday').addEventListener('keyup', e => {
        document.getElementById('registrationBtn').disabled = e.target.value === "";
    });
    document.getElementById('email').addEventListener('keyup', e => {
        document.getElementById('registrationBtn').disabled = e.target.value === "";
    });
    document.getElementById('password').addEventListener('keyup', e => {
        document.getElementById('registrationBtn').disabled = e.target.value === "";
    });
</script>
</body>
</html>