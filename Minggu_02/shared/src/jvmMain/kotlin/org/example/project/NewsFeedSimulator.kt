package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
