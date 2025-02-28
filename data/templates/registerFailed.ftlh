<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка регистрации</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
<!--            width: 300px;-->
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .input-group {
            margin-bottom: 15px;
        }

        .input-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .input-group input {
            width: 100%;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .input-group input:focus {
            border-color: #007BFF;
            outline: none;
        }

        .button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        .button:hover {
            background-color: #0056b3;
        }

        .link {
            display: block;
            text-align: center;
            margin-top: 15px;
        }

        .link a {
            color: #007BFF;
            text-decoration: none;
        }

        .link a:hover {
            text-decoration: underline;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-header text-center">
                    <h3>Ошибка регистрации</h3>
                </div>
                <div class="card-body">
                    <div class="alert alert-danger" role="alert">
                        <strong>Ошибка!</strong> Регистрация не удалась. Попробуйте еще раз.
                    </div>
                    <form action="/register" method="post">
                        <div class="input-group">
                            <label for="fullName">Имя</label>
                            <input type="text" id="fullName" name="fullName" placeholder="Введите ваше имя" required>
                        </div>

                        <div class="input-group">
                            <label for="email">Почта</label>
                            <input type="email" id="email" name="email" placeholder="Введите вашу почту" required>
                        </div>

                        <div class="input-group">
                            <label for="password">Пароль</label>
                            <input type="password" id="password" name="password" placeholder="Введите ваш пароль" required>
                        </div>

                        <button type="submit" class="button">Зарегистрироваться</button>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <small>Уже зарегистрированы? <a href="/">Войти</a></small>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
