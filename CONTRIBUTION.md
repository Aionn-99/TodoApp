---

# 📄 Laporan Kontribusi Proyek — TodoApp

Proyek aplikasi **TodoApp** ini merupakan hasil kerja kolaboratif dari tim yang terdiri dari **4 anggota**. Setiap anggota memberikan kontribusi signifikan sesuai peran masing-masing dalam siklus pengembangan aplikasi.

---

## 👤 1. Pengembang 1: **Aa Afriz Muhammad Gymnastiar Asegaf**

**Peran:** *Pimpinan Proyek & Arsitek Sistem*
**Kontribusi Utama:**

* Merancang arsitektur aplikasi menggunakan pola **MVVM (Model–View–ViewModel)**.
* Menginisiasi struktur proyek, termasuk setup **Gradle** dan struktur direktori.
* Mengembangkan fitur inti **CRUD** (Create, Read, Update, Delete) untuk Todo.
* Mengintegrasikan seluruh komponen (View, ViewModel, Repository, Database) menjadi aplikasi yang berjalan dengan baik.
* Memastikan alur data berjalan sesuai desain arsitektur.

---

## 👤 2. Pengembang 2: **Pipin Sopiah**

**Peran:** *Spesialis Data & Backend*
**Kontribusi Utama:**

* Mengimplementasikan **Room Database** (`TodoDatabase.kt`) untuk penyimpanan data lokal.
* Mendesain skema database dan membuat entitas `Todo`.
* Mengembangkan `TodoRepository.kt` sebagai *Single Source of Truth* bagi aplikasi.
* Melakukan optimasi query dan penyusunan DAO agar performa database tetap cepat dan efisien.

---

## 👤 3. Pengembang 3: **Nursalsa Bilha** 
**Peran:** *Pengembang ViewModel & UI*
**Kontribusi Utama:**

* Mengimplementasikan `TodoViewModel.kt` sebagai jembatan antara UI dan Repository.
* Menghubungkan ViewModel dengan Activity/Fragment agar UI responsif terhadap perubahan data.
* Menyusun logika dialog tambah/edit todo (`dialog_add_edit_todo.xml`).
* Mengembangkan `TodoAdapter` untuk menampilkan daftar Todo pada RecyclerView.

---

## 👤 4. Pengembang 4: **Mirshanda Nur Oktavia** 

**Peran:** *Desainer UI/UX & Frontend*
**Kontribusi Utama:**

* Mendesain dan membuat layout XML untuk item Todo (`item_todo.xml`) dan dialog input.
* Mengelola seluruh aset visual aplikasi seperti warna, teks, dan tema (`colors.xml`, `strings.xml`, `themes.xml`).
* Memastikan konsistensi tampilan aplikasi sesuai pedoman UI/UX.
* Menangani styling dan theming agar aplikasi terlihat profesional dan responsif di berbagai ukuran layar.

---

