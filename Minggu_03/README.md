# My Profile App - Praktikum 3

Aplikasi antarmuka pengguna (UI) profil interaktif yang dibangun menggunakan paradigma UI Deklaratif dengan Kotlin Multiplatform dan Jetpack Compose.

## Fitur Utama

1. **Paradigma UI Deklaratif**: Menggunakan anotasi `@Composable` untuk membangun komponen UI yang reaktif terhadap perubahan.
2. **Basic Layouts**: Menerapkan kombinasi susunan `Column` (vertikal), `Row` (horizontal), dan `Box` (bertumpuk/z-index) untuk menyusun elemen UI secara presisi.
3. **Reusable Composables**: Memisahkan UI menjadi komponen modular yang dapat digunakan kembali seperti `ProfileHeader`, `ProfileCard`, dan `InfoItem`.
4. **Modifiers**: Menggunakan rantai (*chaining*) modifier untuk mengatur ukuran, jarak dalam (*padding*), warna latar belakang (*background*), dan bentuk komponen (contohnya membuat foto profil menjadi bulat).
5. **Animasi**: Penggunaan fungsi `AnimatedVisibility` untuk menampilkan dan menyembunyikan detail kontak dengan efek *fade in* dan *fade out*.

## Hasil Tampilan (Screenshots)

|  Android |  Desktop |
| :---: | :---: |
| <img src="ss_android.png" width="200" alt="Tampilan Android"> | <img src="ss_dekstop.png" width="350" alt="Tampilan Desktop"> |

## Cara Menjalankan

1. *Clone* repositori ini ke komputer lokal Anda.
2. Buka proyek menggunakan **Android Studio** atau **IntelliJ IDEA**.
3. Tunggu hingga proses *Gradle Sync* selesai sepenuhnya (mengunduh *dependencies* yang diperlukan).
4. **Untuk menjalankan di Android (Emulator/Perangkat Fisik):**
    * Pilih konfigurasi *run* `composeApp` atau `androidApp` pada menu *dropdown* (di sebelah tombol "Run" di panel atas IDE).
    * Klik tombol **Run** (ikon segitiga hijau ▶️).
5. **Untuk menjalankan di Desktop (Komputer):**
    * Buka tab **Terminal** di bagian bawah IDE Anda, lalu jalankan perintah berikut:

      ```bash
      ./gradlew :composeApp:run
      ```
      *(Atau sesuaikan dengan nama modul utama proyek Anda jika berbeda).*