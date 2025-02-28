<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка регистрации</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
                    <form action="/register" method="POST">
                        <div class="form-group">
                            <label for="identifier">Идентификатор</label>
                            <input type="text" class="form-control" id="identifier" name="identifier" required>
                        </div>
                        <div class="form-group">
                            <label for="username">Имя пользователя</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Попробовать снова</button>
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
