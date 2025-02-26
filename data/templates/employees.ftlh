<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Список сотрудников</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
        }
        .container {
            background: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        .employee-card {
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 10px;
            background-color: #ffffff;
        }
        .employee-card h5 {
            margin-bottom: 10px;
        }
        .nav-item{
            margin-right:10px
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="/books">BookLander</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/employees">Сотрудники</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/books">Книги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Профиль</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-primary text-white" href="/login">Войти</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-primary text-white" href="/register">Регистрация</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h2 class="text-center">Список сотрудников</h2>
    <div class="list-group">
        <#list employees as employee>
        <div class="employee-card">
            <p><a href="/employee"><strong>ФИО:</strong> ${employee.fullName}</a></p>
            <p><strong>Текущие книги:</strong> ${employee.listCurrentBooks}</p>
            <p><strong>Прошлые книги:</strong> ${employee.listPastBooks}</p>
        </div>
    </#list>
</div>
</body>
</html>
