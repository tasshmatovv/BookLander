<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookLander - Выбор книги</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
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
        .section {
            padding: 60px 20px;
            text-align: center;
            margin-bottom: 30px;
            border-radius: 10px;
        }
        .btn-custom {
            background-color: #F8F8F8;
            color: black;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
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

<div class="container section">
    <p>Выберите книгу из списка и нажмите "Взять".</p>

    <form>
        <div class="form-group">
            <select class="form-control" id="bookSelect">
                <option selected disabled>Выберите книгу...</option>
                <option>Книга 1</option>
                <option>Книга 2</option>
                <option>Книга 3</option>
                <option>Книга 4</option>
            </select>
        </div>

        <button type="submit" class="btn btn-custom">Взять</button>
    </form>
</div>
</body>
</html>
