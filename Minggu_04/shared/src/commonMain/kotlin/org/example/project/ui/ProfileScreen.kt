package org.example.project.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.ui.components.LabeledTextField
import org.example.project.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.painterResource
import org.example.project.shared.generated.resources.Res
import org.example.project.shared.generated.resources.foto_profil

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel { ProfileViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    val isDark = uiState.isDarkMode
    val colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
    val backgroundColor = if (isDark) Color(0xFF121212) else Color(0xFFF5F5F5)

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor,
            contentColor = colorScheme.onBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Bar Atas: Switch Dark Mode
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isDark) "Dark Mode" else "Light Mode",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Switch(
                        checked = isDark,
                        onCheckedChange = { viewModel.toggleDarkMode(it) }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ProfileHeader(
                    name = uiState.name,
                    bio = uiState.bio,
                    isDark = isDark
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (!uiState.isEditing) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.startEditing() },
                            border = BorderStroke(1.dp, if (isDark) Color(0xFF80CBC4) else Color(0xFF00695C)),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = if (isDark) Color(0xFF80CBC4) else Color(0xFF00695C)
                            )
                        ) {
                            Text("Edit Profile")
                        }

                        Button(
                            onClick = { viewModel.toggleContactVisibility() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDark) Color(0xFF00796B) else Color(0xFF00695C),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = if (uiState.isContactVisible) "Sembunyikan Kontak" else "Tampilkan Kontak")
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, if (isDark) Color(0xFF383838) else Color(0xFFE0E0E0)),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isDark) Color(0xFF1E1E1E) else Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Edit Informasi Profil",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            LabeledTextField(
                                label = "Nama Lengkap",
                                value = uiState.inputName,
                                onValueChange = { viewModel.onInputNameChange(it) }
                            )

                            LabeledTextField(
                                label = "Bio / Deskripsi Singkat",
                                value = uiState.inputBio,
                                onValueChange = { viewModel.onInputBioChange(it) },
                                singleLine = false
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = { viewModel.cancelEditing() }) {
                                    Text("Batal")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = { viewModel.saveProfile() },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isDark) Color(0xFF00796B) else Color(0xFF00695C),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Simpan")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = uiState.isContactVisible,
                    enter = fadeIn(animationSpec = tween(500)),
                    exit = fadeOut(animationSpec = tween(300))
                ) {
                    ProfileCard(
                        email = uiState.email,
                        phone = uiState.phone,
                        location = uiState.location,
                        isDark = isDark
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String, bio: String, isDark: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.foto_profil),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
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
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = bio,
            fontSize = 14.sp,
            color = if (isDark) Color(0xFFB0BEC5) else Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun ProfileCard(email: String, phone: String, location: String, isDark: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, if (isDark) Color(0xFF383838) else Color(0xFFE0E0E0)),
        colors = CardDefaults.cardColors(
            containerColor = if (isDark) Color(0xFF1E1E1E) else Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoItem(icon = Icons.Default.Email, label = "Email", value = email, isDark = isDark)
            HorizontalDivider(
                color = if (isDark) Color(0xFF333333) else Color(0xFFEEEEEE),
                thickness = 1.dp
            )
            InfoItem(icon = Icons.Default.Phone, label = "Phone", value = phone, isDark = isDark)
            HorizontalDivider(
                color = if (isDark) Color(0xFF333333) else Color(0xFFEEEEEE),
                thickness = 1.dp
            )
            InfoItem(icon = Icons.Default.LocationOn, label = "Location", value = location, isDark = isDark)
        }
    }
}

@Composable
fun InfoItem(icon: ImageVector, label: String, value: String, isDark: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isDark) Color(0xFF4DB6AC) else Color(0xFF00695C),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = if (isDark) Color(0xFFB0BEC5) else Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDark) Color.White else Color(0xFF212121)
            )
        }
    }
}
