<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap-theme.min.css">
    <style>
        .nav-item{
           margin-right:10px
       }
    </style>
</head>

<body class="bg-light">
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
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h1 class="card-title text-center">User Profile</h1>
                    <p class="card-text">Email: ${email}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
