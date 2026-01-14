<?php
$conn = mysqli_connect("localhost", "appocaly_kel8_IFVIIA", "SttCipasung2025Kelompok8", "appocaly_Todo_kel8_IFVIIA");

if (!$conn) {
    http_response_code(500);
    echo json_encode([
        "error" => "Database connection failed"
    ]);
    exit;
}
