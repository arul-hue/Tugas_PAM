# My Profile App - Praktikum 4

Aplikasi antarmuka pengguna (UI) profil interaktif yang dikembangkan menggunakan arsitektur **MVVM (Model-View-ViewModel)** dan **State Management** pada Compose Multiplatform (Kotlin Multiplatform & Jetpack Compose)[cite: 1, 20].

---

## Fitur Utama

1. **Arsitektur MVVM (Model-View-ViewModel)**: Memisahkan tampilan UI (`ProfileScreen`), logika bisnis (`ProfileViewModel`), dan data state (`ProfileUiState`) untuk menghasilkan kode yang bersih, terstruktur, dan mudah diuji (*testable*)[cite: 20, 21].
2. **UI State Pattern & Reactive State Flow**: Menggunakan `data class ProfileUiState` dan `StateFlow` (`MutableStateFlow`) di ViewModel untuk mengelola seluruh data UI secara reaktif serta tahan terhadap *configuration changes*[cite: 23, 24, 25].
3. **State Hoisting**: Menerapkan pola *State Hoisting* pada komponen reusabel `LabeledTextField` di mana state mengalir ke bawah via parameter `value` dan event mengalir ke atas via callback `onValueChange`[cite: 16, 18, 30].
4. **Fitur Edit Profile**: Menyediakan form interaktif untuk mengubah nama dan bio secara langsung, lengkap dengan tombol simpan dan batal yang terintegrasi penuh dengan ViewModel[cite: 30, 35].
5. **Fitur Dark Mode Toggle**: Dukungan peralihan tema terang (*Light Mode*) dan tema gelap (*Dark Mode*) secara reaktif menggunakan sakelar `Switch` dan `MaterialTheme`[cite: 35, 36].

---

## Hasil Tampilan (Screenshots)

|                 Profile View                 |                Edit Form                |                Dark Mode                |
|:--------------------------------------------:|:---------------------------------------:|:---------------------------------------:|
| ![Profile View](screenshots/Profil_View.png) | ![Edit Form](screenshots/Edit_Form.png) | ![Dark Mode](screenshots/Dark_Mode.png) |
|           *Tampilan Utama Profil*            |         *Form Edit Nama & Bio*          |          *Tampilan Tema Gelap*          |

---

## Cara Menjalankan

1. *Clone* repositori ini ke komputer lokal Anda.
2. Buka proyek menggunakan **Android Studio** atau **IntelliJ IDEA**.
3. Tunggu hingga proses *Gradle Sync* selesai sepenuhnya (mengunduh *dependencies* yang diperlukan).
4. Untuk menjalankan di Android (Emulator/Perangkat Fisik):
  - Pilih konfigurasi *run* `composeApp` atau `androidApp` pada menu *dropdown* (di sebelah tombol "Run" di panel atas IDE).
  - Klik tombol **Run** (ikon segitiga hijau ▶️).
5. Untuk menjalankan di Desktop (Komputer):
  - Buka tab **Terminal** di bagian bawah IDE Anda, lalu jalankan perintah berikut:

    ```bash
    ./gradlew :composeApp:run
    ```

    *(Atau sesuaikan dengan nama modul utama proyek Anda jika berbeda).*