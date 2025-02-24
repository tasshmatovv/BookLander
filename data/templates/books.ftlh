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
            background: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .book-card img {
            max-width: 100%;
            border-radius: 5px;
        }
        .author-section {
            text-align: center;
            padding: 50px 20px;
            background: #fff;
        }
    </style>
</head>
<body>
<div class="hero">
    <h1>Welcome</h1>
</div>

<div class="container book-section">
    <h2 class="text-center">All Books</h2>
    <div class="row">
        <#list books as book>
        <div class="col-md-4">
            <div class="book-card">
                <img src="book-placeholder.jpg" alt="${book.name}">
                <h5>${book.name}</h5>
                <p>Author: ${book.author}</p>
                <p class="text-primary">${book.status}</p>
            </div>
        </div>
    </#list>
</div>
</body>
</html>
