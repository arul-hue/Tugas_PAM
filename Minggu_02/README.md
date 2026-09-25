# News Feed Simulator - Praktikum 2

Aplikasi simulasi pembaruan berita real-time menggunakan Kotlin Coroutines dan Flow.

## Fitur Utama

1. **Flow Stream**: Simulasi penerimaan data berita baru secara berkala setiap 2 detik.
2. **Operators (filter, map, onEach)**: Menyaring berita berdasarkan kategori dan mengubah format tampilan teks.
3. **StateFlow**: Mengelola _state_ jumlah berita yang telah dibaca pengguna secara _real-time_.
4. **Async Detail Fetch**: Mengambil detail konten berita secara _asynchronous_ menggunakan `Dispatchers.IO`.

## Cara Menjalankan

1. Clone repositori ini ke komputer lokal.
2. Buka proyek menggunakan **Android Studio** atau **IntelliJ IDEA**.
3. Buka tab **Terminal** di bagian bawah IDE, lalu jalankan perintah berikut:
   ```bash
   ./gradlew :shared:runSimulator
   ```
