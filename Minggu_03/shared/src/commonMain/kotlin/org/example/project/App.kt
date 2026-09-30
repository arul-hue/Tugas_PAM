package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// MAIN ENTRY POINT
@Composable
fun App() {
    MyProfileApp()
}

// MAIN SCREEN
@Composable
fun MyProfileApp() {
    // State untuk mengontrol animasi tampil/sembunyi ProfileCard
    var isContactVisible by remember { mutableStateOf(false) }

    // Column utama (Susunan Vertikal)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Background abu-abu muda
            .systemBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Memanggil Reusable Composable Pertama
        ProfileHeader(
            name = "Syahrul Afwan",
            bio = "Mahasiswa Teknik Informatika ITERA \nEnthusiast in Mobile & Web Development."
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol interaktif
        Button(
            onClick = { isContactVisible = !isContactVisible },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C))
        ) {
            Text(text = if (isContactVisible) "Sembunyikan Kontak" else "Tampilkan Kontak")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Animasi
        AnimatedVisibility(
            visible = isContactVisible,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            // 2. Memanggil Reusable Composable Kedua
            ProfileCard(
                email = "syahrul.124140096@student.itera.ac.id",
                phone = "08991827544",
                location = "Bandar Lampung, Indonesia"
            )
        }
    }
}

// REUSABLE COMPOSABLE 1: ProfileHeader
@Composable
fun ProfileHeader(name: String, bio: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Box Layout
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            // Layer 1: Gambar profil melingkar
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape) // Avatar circular image
                    .background(Color.LightGray)
                    .padding(16.dp)
            )
            // Layer 2: Indikator Online
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            )
        }

        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold // Nama (bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = bio,
            fontSize = 14.sp,
            color = Color.Gray, // Bio (gray color)
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

// REUSABLE COMPOSABLE 2: ProfileCard
@Composable
fun ProfileCard(email: String, phone: String, location: String) {
    // Card Container dengan elevasi
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp), // Padding dalam yang rapi
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 3. Memanggil Reusable Composable Ketiga untuk tiap item informasi
            InfoItem(icon = Icons.Default.Email, label = "Email", value = email)
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp) // Garis pemisah
            InfoItem(icon = Icons.Default.Phone, label = "Phone", value = phone)
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
            InfoItem(icon = Icons.Default.LocationOn, label = "Location", value = location)
        }
    }
}

// REUSABLE COMPOSABLE 3: InfoItem
@Composable
fun InfoItem(icon: ImageVector, label: String, value: String) {
    // Row Layout (Susunan Horizontal)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF00695C),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

//komen di code disengaja agar lebih mudah dipahami dan dibaca tiao fungsinya.
