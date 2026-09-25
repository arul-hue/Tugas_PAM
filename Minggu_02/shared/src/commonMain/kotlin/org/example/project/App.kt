package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.milliseconds
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*

class PencatatBeritaTerbaca {
    private val _jumlahTerbaca = MutableStateFlow(0)
    val jumlahTerbaca: StateFlow<Int> = _jumlahTerbaca.asStateFlow()

    fun tambah() {
        _jumlahTerbaca.value++
    }
}

data class Berita(
    val id: Int,
    val judul: String,
    val kategori: String
)

fun aliranBerita(): Flow<Berita> = flow {
    val daftarBeritaTiruan = listOf(
        Berita(1, "Peluncuran Satelit Komunikasi Baru", "Teknologi"),
        Berita(2, "Kemenangan Timnas di Kualifikasi", "Olahraga"),
        Berita(3, "Update Kotlin Coroutines & Flow di KMP", "Teknologi"),
        Berita(4, "Resep Kuliner Khas Nusantara", "Gaya Hidup"),
        Berita(5, "Pembaruan Keamanan Sistem Android 15", "Teknologi")
    )

    for (berita in daftarBeritaTiruan) {
        delay(2000.milliseconds)
        emit(berita)
    }
}.catch { e ->
    println("Terjadi error pada stream: ${e.message}")
}

suspend fun ambilDetailBerita(idBerita: Int): String = withContext(Dispatchers.IO) {
    delay(1000.milliseconds)
    "Detail konten lengkap untuk berita ID #$idBerita berhasil diunduh."
}

fun main() = runBlocking {
    val pencatat = PencatatBeritaTerbaca()
    val kategoriPilihan = "Teknologi"

    println("NEWS FEED SIMULATOR")
    println("Kategori Aktif: $kategoriPilihan\n")

    val tugasPemantau = launch {
        pencatat.jumlahTerbaca.collect { count ->
            println("[STATEFLOW UPDATE] Total berita dibaca: $count")
        }
    }

    aliranBerita()
        .filter { berita -> berita.kategori == kategoriPilihan }
        .map { berita -> "[HEADLINE] #${berita.id} - ${berita.judul.uppercase()}" }
        .onEach { judulFormat ->
            println("\nProcessing: $judulFormat")
        }
        .collect { judulFormat ->
            println("Diterima UI: $judulFormat")

            val idBerita = judulFormat.substringAfter("#").substringBefore(" -").toInt()

            val tugasDetail = async(Dispatchers.IO) {
                ambilDetailBerita(idBerita)
            }

            val hasilDetail = tugasDetail.await()
            println("   -> $hasilDetail")

            pencatat.tambah()
        }

    tugasPemantau.cancel()
    println("\nSIMULASI SELESAI")
}

@Composable
fun App() {
    val pencatat = remember { PencatatBeritaTerbaca() }
    val jumlahTerbaca by pencatat.jumlahTerbaca.collectAsState()

    val daftarBeritaUI = remember { mutableStateListOf<Pair<String, String>>() }
    val kategoriPilihan = "Teknologi"

    LaunchedEffect(Unit) {
        aliranBerita()
            .filter { berita -> berita.kategori == kategoriPilihan }
            .map { berita -> "[HEADLINE] #${berita.id} - ${berita.judul.uppercase()}" }
            .collect { judulFormat ->
                val idBerita = judulFormat.substringAfter("#").substringBefore(" -").toInt()

                val hasilDetail = withContext(Dispatchers.IO) {
                    ambilDetailBerita(idBerita)
                }

                daftarBeritaUI.add(Pair(judulFormat, hasilDetail))
                pencatat.tambah()
            }
    }

    Column {
        Text("NEWS FEED SIMULATOR")
        Text("Kategori Aktif: $kategoriPilihan")
        Text("[STATEFLOW UPDATE] Total Berita Dibaca: $jumlahTerbaca\n")

        daftarBeritaUI.forEach { (judul, detail) ->
            Text(judul)
            Text("   -> $detail\n")
        }
    }
}

//mencatatBeritaTerbaca: Mengelola dan menyimpan jumlah hitungan data berita yang sudah selesai dibaca menggunakan StateFlow.
//Berita: Model data (Data Class) untuk menyimpan informasi dasar setiap berita.
//aliranBerita(): Berfungsi sebagai produsen data yang memancarkan (emit) objek berita secara berkala tiap 2 detik.
//ambilDetailBerita(): Fungsi penundaan (suspend) yang berjalan di jalur latar belakang (Dispatchers.IO) untuk menyimulasikan proses unduh data.
//main(): Tempat utama program dieksekusi, mengatur pemantauan skor, menyaring data dengan operator Flow, serta menjalankan pengambilan detail secara asinkron.