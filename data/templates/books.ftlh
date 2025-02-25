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
