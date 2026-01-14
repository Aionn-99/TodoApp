# 🗂️ Todo App — Hybrid MVVM Architecture

Aplikasi Todo Android dengan **MVVM + Repository Pattern**, menggabungkan **Room Database** (offline) dan **REST API Backend** (online sync) menggunakan **Retrofit**.

---

## ✨ Fitur Utama

- ✅ **Offline-First**: Data tersimpan lokal dengan Room Database
- 🌐 **Online Sync**: Sinkronisasi otomatis dengan server PHP backend
- 🔄 **Auto Sync**: Checkbox otomatis tersinkronisasi dengan server
- 🏗️ **Clean Architecture**: MVVM + Repository Pattern
- 📱 **Modern UI**: Material Design dengan RecyclerView

---

## 📐 Arsitektur

Aplikasi menggunakan **Hybrid Architecture**:

```
UI Layer (View)
    ↕
ViewModel
    ↕
Repository (SSOT)
    ↕
Local (Room) + Remote (Retrofit API)
```

### Komponen Utama:

**1. UI Layer**

- `MainActivity.kt` - Activity utama
- `TodoAdapter.kt` - RecyclerView adapter
- `AddEditTodoDialog.kt` - Dialog tambah/edit
- Layout XML (`dialog_add_edit_todo.xml`, `item_todo.xml`)

**2. ViewModel**

- `TodoViewModel.kt` - Mengelola UI state & logika bisnis

**3. Repository**

- `TodoRepository.kt` - Single Source of Truth, koordinasi data lokal & remote

**4. Data Layer**

- **Local**: `TodoDatabase.kt`, `TodoDao.kt`, `Todo.kt`
- **Remote**: `ApiClient.kt`, `ApiService.kt` (Retrofit)
- **Backend API**: Folder `/api` (PHP - CRUD endpoints)

---

## 🔄 Alur Data

**Menambah Todo:**

1. User input di UI → ViewModel
2. ViewModel → Repository
3. Repository → Simpan ke Room (offline) → Sync ke API (online)
4. Response → Update UI via LiveData

**Checkbox Sync:**

- Toggle checkbox → Auto update Room → API call `update_todo.php`
- Lihat detail di `SYNC_CHECKBOX_GUIDE.md`

---

## 🛠️ Tech Stack

- **Kotlin** - Bahasa pemrograman
- **Room** - Database lokal (SQLite)
- **Retrofit** - HTTP client untuk REST API
- **Gson** - JSON serialization
- **Coroutines** - Asynchronous programming
- **LiveData** - Observable data holder
- **Material Design** - UI components
- **PHP + MySQL** - Backend API

---

## 📂 Backend API

Folder `/api` berisi:

- `koneksi.php` - Database connection
- `todos.php` - GET all todos
- `add_todo.php` - POST new todo
- `update_todo.php` - PUT/PATCH update todo
- `delete_todo.php` - DELETE todo

---

## 🎯 Keunggulan Arsitektur

✔ **Separation of Concerns** - Pemisahan jelas antar layer  
✔ **Testable** - Mudah di-unit test  
✔ **Scalable** - Mudah dikembangkan  
✔ **Offline Support** - Tetap berfungsi tanpa internet  
✔ **Data Consistency** - Sinkronisasi otomatis saat online

---
