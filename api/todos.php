<?php
header("Content-Type: application/json; charset=UTF-8");

include "koneksi.php";

// JANGAN echo apa pun di koneksi.php

$query = mysqli_query($conn, "SELECT * FROM todos");

$data = [];

while ($row = mysqli_fetch_assoc($query)) {
    $data[] = $row;
}

echo json_encode($data);
exit;
