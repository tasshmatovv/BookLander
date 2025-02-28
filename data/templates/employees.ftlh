<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Список сотрудников - BookLander</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            color: black;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            padding: 40px 20px;
        }
        .navbar {
            background-color: #F8F8F8;
        }
        .navbar-brand, .nav-link {
            color: black ;
        }
        .nav-link.btn {
            background-color: #F8F8F8;
            color: black ;
         }
        .nav-link.btn:hover {
            color: black ;
        }
        .section {
            padding: 60px 20px;
            text-align: center;
            background-color: #111;
            margin-bottom: 30px;
            border-radius: 10px;
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
        <a class="navbar-brand" href="/">BookLander</a>
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
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="text-center">Список сотрудников</h2>
    <div class="list-group">
        <#list employees as employee>
        <div class="card">
            <p><a href="/employee"><strong>ФИО:</strong> ${employee.fullName}</a></p>
            <p><strong>Текущие книги:</strong> ${employee.listCurrentBooks}</p>
            <p><strong>Прошлые книги:</strong> ${employee.listPastBooks}</p>
        </div>
    </#list>
</div>
</div>
</body>
</html>
