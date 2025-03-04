<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выбор книги - BookLander</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            color: black;
        }
        .container {
            max-width: 600px;
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
                <li class="nav-item">
                    <form action="/logout" method="post">
                        <button class="nav-link btn" type="submit">Выйти</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container text-center">
    <h2>Выберите книгу</h2>
    <form>
        <div class="form-group">
            <select class="form-control" id="bookSelect">
                <option value="">Выберите книгу</option>
                <option value="book1">Книга 1</option>
                <option value="book2">Книга 2</option>
                <option value="book3">Книга 3</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Подтвердить</button>
    </form>
    <div class="mt-3">
        <a href="/books" class="btn btn-secondary">Вернуться</a>
    </div>
</div>

</body>
</html>
