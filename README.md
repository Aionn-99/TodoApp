---

# 🗂️ Todo App — MVVM + Repository Pattern + Room

A simple yet modern Android Todo application built with **MVVM architecture**, **Repository Pattern**, and **Room Database**.
This project separates the UI, data management, and business logic clearly—making the app scalable, testable, and maintainable.

---

## 📐 Arsitektur Utama

Aplikasi ini menggunakan kombinasi **MVVM (Model–View–ViewModel)** dan **Repository Pattern**.
Alur datanya sebagai berikut:

```
View (UI Layer) ↔ ViewModel ↔ Repository ↔ Model (Data Layer)
```

---

## 📁 Struktur & Penjelasan Komponen

### 1️⃣ View (UI Layer)

Lapisan yang berinteraksi langsung dengan pengguna.

Berisi:

* `Activity` / `Fragment`
* Layout XML

  * `dialog_add_edit_todo.xml`
  * `item_todo.xml`
* `TodoAdapter`

**Tugas utama:**

* Menampilkan data yang diberikan oleh `ViewModel`.
* Mengirim event pengguna (klik tombol, input teks, dll) ke `ViewModel`.

---

### 2️⃣ ViewModel (`TodoViewModel.kt`)

Berfungsi sebagai jembatan antara **View** dan **Repository**.

**Tugas utama:**

* Mengelola data terkait UI.
* Menjaga data tetap survive saat konfigurasi berubah (misal rotasi layar).
* Meminta data ke Repository dan menyiapkannya untuk View.

ViewModel **tidak tahu detail penyimpanan data**, hanya tahu cara memintanya.

---

### 3️⃣ Repository (`TodoRepository.kt`)

Disebut juga sebagai:

> **Single Source of Truth (SSOT)**

**Tugas utama:**

* Menentukan dari mana data diambil (database lokal / network).
* Mengelola dan membungkus seluruh operasi data.
* Mengisolasi ViewModel dari sumber data langsung.

Di proyek ini Repository terhubung langsung dengan **Room Database**.

---

### 4️⃣ Model (Data Layer)

Lapisan penyimpanan dan manajemen data.

Berisi:

* `TodoDatabase.kt` (Room)
* Data entity seperti:

  * `Todo.kt`

**Tugas utama:**

* Mengelola data aplikasi secara persisten di perangkat.
* Room menyediakan abstract layer di atas SQLite sehingga lebih aman dan mudah.

---

## 🔄 Alur Kerja Data

Contoh ketika pengguna menambahkan to-do baru:

1. User mengisi form di UI → View mengirim event ke ViewModel
2. ViewModel memanggil Repository
3. Repository menyimpan data ke Room Database
4. Database mengirim kembali data terbaru
5. ViewModel memancarkan data ke View
6. UI menampilkan data yang sudah terupdate

---

## 📝 Singkatnya

Arsitektur ini memberikan:

✔ Struktur kode rapi
✔ Mudah diuji
✔ Perubahan UI tidak memengaruhi data layer
✔ ViewModel aman dari perubahan konfigurasi

---

