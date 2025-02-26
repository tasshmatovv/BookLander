<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .hero {
            text-align: center;
            padding: 50px 20px;
            background-color: #fff;
        }
        .book-section {
            padding: 40px 20px;
        }
       .book-card {
          width: 100%;
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          align-items: center;
        }
        .book-card img {
            width: 100%;
            height: 500px;
        }
        .author-section {
            text-align: center;
            padding: 50px 20px;
            background: #fff;
        }
        .nav-item{
            margin-right:10px
        }
    </style>
</head>
<body>
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


<div class="container book-section">
    <div class="row">
        <#list books as book>
        <div class="col-md-4">
            <div class="book-card">
                <img src="${book.cover}" alt="${book.name}" class="book-cover">
                <h5><a href="/book">${book.name}</a></h5>
                <p>Author: ${book.author}</p>
                <p class="text-primary">${book.status}</p>
            </div>
        </div>
    </#list>
</div>
</body>
</html>
