<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Информация о книге</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 900px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 50px;
        }
        .book-cover {
            max-width: 200px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        .btn-custom {
            background-color: #1c1c1c;
            color: #fff;
            border-radius: 20px;
            padding: 10px 20px;
            text-decoration: none;
        }
        .btn-custom:hover {
            background-color: #333;
        }
    </style>
</head>
<body>
<div class="container d-flex flex-column align-items-center text-center">
    <img src="${book.cover}" alt="${book.name}" class="book-cover">
    <h2 class="mb-3">${book.name}</h2>
    <p class="text-muted">${book.author}</p>
    <hr class="w-100 mt-4">
    <a href="/books" class="btn btn-custom mt-3">Назад</a>
</div>
</body>
</html>
