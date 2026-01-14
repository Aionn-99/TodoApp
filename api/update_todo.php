<?php
header("Content-Type: application/json");
include "koneksi.php";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    $id = $_POST['id'] ?? null;
    $title = $_POST['title'] ?? '';
    $description = $_POST['description'] ?? '';
    $deadline = $_POST['deadline'] ?? '';
    $priority = $_POST['priority'] ?? 0;
    $category = $_POST['category'] ?? '';
    $is_done = $_POST['is_done'] ?? 0; // 🔥 PENTING

    if (!$id) {
        echo json_encode([
            "success" => false,
            "message" => "ID wajib diisi"
        ]);
        exit;
    }

    $stmt = $conn->prepare("
        UPDATE todos 
        SET 
            title=?, 
            description=?, 
            deadline=?, 
            priority=?, 
            category=?, 
            is_done=? 
        WHERE id=?
    ");

    $stmt->bind_param(
        "sssissi",
        $title,
        $description,
        $deadline,
        $priority,
        $category,
        $is_done,
        $id
    );

    if ($stmt->execute()) {
        echo json_encode([
            "success" => true,
            "message" => "Todo berhasil diupdate"
        ]);
    } else {
        echo json_encode([
            "success" => false,
            "message" => "Gagal update todo"
        ]);
    }
}
