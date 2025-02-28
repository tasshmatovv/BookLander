<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Книги - BookLander</title>
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
            text-align: center;
        }
        .card img {
            width: 100%;
            height: auto;
            border-radius: 10px;
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

<div class="container section">
    <h1>Книги</h1>
    <p>Просматривайте и управляйте вашими любимыми книгами.</p>
</div>

<div class="container">
    <div class="row">
        <#list books as book>
        <div class="col-md-4">
            <div class="card">
                <img src="${book.cover}" alt="${book.name}">
                <h5><a href="/book">${book.name}</a></h5>
                <p>Автор: ${book.author}</p>
                <p class="text-primary">${book.status}</p>
            </div>
        </div>
    </#list>
</div>
</div>
</body>
</html>
