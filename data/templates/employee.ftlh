<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 800px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 50px;
        }
        .employeeInfo {
            text-align: center;
        }
        .employeeInfo h2 {
            margin-bottom: 20px;
            font-weight: bold;
        }
        .info-item {
            font-size: 18px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container text-center">
    <div class="employeeInfo">
        <h2>Employee Information</h2>
        <p class="info-item"><strong>Name:</strong>${employee.fullName}</p>
        <p class="info-item"><strong>Current Books:</strong><#list currentBooks as book>${book}<#if book?has_next>, </#if></#list></p>
        <p class="info-item"><strong>Past Books:</strong><#list pastBooks as book>${book}<#if book?has_next>, </#if></#list></p>
        <a href="/employees" class="btn btn-custom mt-3">Назад</a>
    </div>
</div>
</body>
</html>
