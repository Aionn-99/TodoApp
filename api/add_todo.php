<?php
header("Content-Type: application/json");
include "koneksi.php";

$title = $_POST['title'];
$description = $_POST['description'];
$deadline = $_POST['deadline'];
$priority = $_POST['priority'];
$category = $_POST['category'];

$query = mysqli_query($conn,
    "INSERT INTO todos (title, description, deadline, priority, category)
     VALUES ('$title', '$description', '$deadline', '$priority', '$category')"
);

echo json_encode(["success" => true]);
