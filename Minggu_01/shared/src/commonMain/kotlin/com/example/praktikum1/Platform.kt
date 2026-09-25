package com.example.praktikum1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform