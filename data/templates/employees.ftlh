<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Список сотрудников</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
        }
        .container {
            background: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        .employee-card {
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 10px;
            background-color: #ffffff;
        }
        .employee-card h5 {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Список сотрудников</h2>
    <div class="list-group">
        <#list employees as employee>
        <div class="employee-card">
            <p><strong>ФИО:</strong> ${employee.fullName}</p>
            <p><strong>Текущие книги:</strong> ${employee.listCurrentBooks}</p>
            <p><strong>Прошлые книги:</strong> ${employee.listPastBooks}</p>
        </div>
    </#list>
</div>
</div>
</body>
</html>
