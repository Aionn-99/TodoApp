<?php
header("Content-Type: application/json");
include "koneksi.php";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    $id = $_POST['id'] ?? null;

    if (!$id) {
        echo json_encode([
            "success" => false,
            "message" => "ID wajib diisi"
        ]);
        exit;
    }

    $stmt = $conn->prepare("DELETE FROM todos WHERE id=?");
    $stmt->bind_param("i", $id);

    if ($stmt->execute()) {
        echo json_encode([
            "success" => true,
            "message" => "Todo berhasil dihapus"
        ]);
    } else {
        echo json_encode([
            "success" => false,
            "message" => "Gagal hapus todo"
        ]);
    }
}
