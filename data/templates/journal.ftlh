<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Журнал книг - BookLander</title>
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
            color: black;
        }
        .nav-link.btn {
            background-color: #F8F8F8;
            color: black;
        }
        .nav-link.btn:hover {
            background-color: #ccc;
        }
        .section {
            padding: 40px 20px;
            text-align: center;
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
        .btn-custom {
            background-color: #fff;
            color: black;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }
        .btn-custom:hover {
            background-color: #ccc;
        }
        .table {
            width: 100%;
            margin-bottom: 1rem;
            color: black;
            background-color: #F8F8F8;
            border-radius: 10px;
        }
        .table th,
        .table td {
            padding: 12px;
            vertical-align: top;
            border-top: 1px solid #dee2e6;
        }
        .table thead th {
            vertical-align: bottom;
            border-bottom: 2px solid #dee2e6;
        }
        .table tbody + tbody {
            border-top: 2px solid #dee2e6;
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
                <#if email??>
                <li class="nav-item">
                    <form action="/logout" method="post">
                        <button class="nav-link btn" type="submit">Выйти</button>
                    </form>
                </li>
                <#else>
                <li class="nav-item"><a class="nav-link btn" href="/login">Войти</a></li>
                <li class="nav-item"><a class="nav-link btn" href="/register">Регистрация</a></li>
            </#if>
            </ul>
        </div>
    </div>
</nav>

<div class="container section">
    <h1>Журнал книг</h1>
    <p>Просмотр истории взятия и возвращения книг</p>
</div>

<div class="container">
    <div class="card">
        <table class="table">
            <thead>
            <tr>
                <th>Сотрудник</th>
                <th>Название книги</th>
                <th>Время взятия</th>
                <th>Время возврата</th>
            </tr>
            </thead>
            <tbody>
            <#if journal?? && journal?size gt 0>
            <#list journal as entry>
            <tr>
                <td>${entry.employeeName}</td>
                <td>${entry.bookName}</td>
                <td>${entry.takeTime}</td>
                <td>${entry.returnedTime?default("Неизвестно")}</td>
            </tr>
            </#list>
            <#else>
            <tr>
                <td colspan="4" class="text-center">Нет записей</td>
            </tr>
        </#if>
    </tbody>
    </table>
</div>
</div>
</body>
</html>
