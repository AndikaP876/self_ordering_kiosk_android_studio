<?php

$file_db = "records.db";

try{
    $pdo = new PDO('sqlite:records');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);

    $sql_create = "CREATE TABLE IF NOT EXISTS 'records'(
        'id' integer NOT NULL PRIMARY KEY AUTOINCREMENT,
        'name' text NOT NULL, 
        'price' integer NOT NULL,
        'quantity' integer NOT NULL)";
    $pdo->exec($sql_create);
} catch(\Throwable $th){
    throw new PDOException($e->getMessage(), (int)$e->getCode());
}

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'GET'){
    $query = 'select * from records';
    $stmt = $pdo->prepare($query);
    $stmt->execute();
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($data);
} elseif ($_SERVER['REQUEST_METHOD'] === 'POST'){
    $name = $_POST['name'];
    $price = $_POST['price'];
    $quantity = $_POST['quantity'];
    $query = "insert into records (name, price, quantity) values (?, ?, ?)";
    $stmt = $pdo->prepare($query);
    $res = $stmt->execute([$name, $price, $quantity]);
    if ($res){
        $data = ['name'=>$name, 'price'=>$price, 'quantity'=>$quantity];
        echo json_encode($data);
    } else{
        echo json_encode(['error'=>$stmt->errorCode()]);
    }
} elseif ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
    $id = $_GET['id'];
    $query = "delete from records where id = ?";
    $stmt = $pdo->prepare($query);
    $res = $stmt->execute([$id]);
    if($res){
        $data = ['id'=>$id];
        echo json_encode($data);
    }else{
        echo json_encode(['error'=>$stmt->errorCode()]);
    }
} elseif ($_SERVER['REQUEST_METHOD'] === 'SUM'){
    $query = 
    'SELECT SUM(total_price) AS total_all_prices
    FROM (
        SELECT id, SUM(price * quantity) AS total_price
        FROM records
        GROUP BY id
    ) subquery;';
    $stmt = $pdo->prepare($query);
    $stmt->execute();
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($data);
}