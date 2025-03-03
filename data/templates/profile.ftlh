<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            color: black;
        }
        .navbar {
            background-color: #F8F8F8;
        }
        .navbar-brand, .nav-link {
            color: black;
        }
        .nav-link.btn {
            background-color: #F8F8F8;
            color: black;
        }
        .nav-link.btn:hover {
            background-color: #F8F8F8;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 40px 20px;
        }
        .card {
            background-color: #F8F8F8;
            color: black;
            border: none;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="/books">BookLander</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="/employees">Сотрудники</a></li>
                <li class="nav-item"><a class="nav-link" href="/books">Книги</a></li>
                <li class="nav-item"><a class="nav-link" href="/profile">Профиль</a></li>
                <li class="nav-item"><a class="nav-link btn" href="/login">Войти</a></li>
                <li class="nav-item"><a class="nav-link btn" href="/register">Регистрация</a></li>
                <li class="nav-item">
                    <form action="/logout" method="post">
                        <button class="nav-link btn" type="submit">Выйти</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="card text-center">
        <div class="card-body">
            <h1 class="card-title">Профиль пользователя</h1>
            <p class="card-text"><strong>Имя:</strong> ${fullName}</p>
            <p class="card-text"><strong>Email:</strong> ${email}</p>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <h2 class="card-title">Текущие книги</h2>
            <ul>
                <#if currentBooks?size == 0>
                <p>Нет текущих книг</p>
                <#else>
                <#list currentBooks as book>
                <li>${book}</li>
            </#list>
        </#if>
        </ul>
    </div>
</div>

<div class="card">
    <div class="card-body">
        <h2 class="card-title">Прошлые книги</h2>
        <ul>
            <#if pastBooks?size == 0>
            <p>Нет прошлых книг</p>
            <#else>
            <#list pastBooks as book>
            <li>${book}</li>
        </#list>
    </#if>
    </ul>
</div>
</div>
</div>
</body>
</html>