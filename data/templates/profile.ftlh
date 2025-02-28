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
            color:  black;
        }
        .nav-link.btn {
            background-color:  #F8F8F8;
            color: black;
        }
        .nav-link.btn:hover {
            background-color:  #F8F8F8;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 40px 20px;
        }
        .card {
            background-color:  #F8F8F8;
            color: black;
            border: none;
            border-radius: 10px;
            padding: 20px;
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
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="card">
        <div class="card-body text-center">
            <h1 class="card-title">Профиль пользователя</h1>
            <p class="card-text">Email: ${email}</p>
        </div>
    </div>
</div>
</body>
</html>