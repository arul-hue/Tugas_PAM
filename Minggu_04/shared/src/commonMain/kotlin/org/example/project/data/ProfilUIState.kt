package org.example.project.data

data class ProfileUiState(
    val name: String = "Syahrul Afwan",
    val bio: String = "Mahasiswa Teknik Informatika ITERA \nEnthusiast in Mobile & Web Development.",
    val email: String = "syahrul.124140096@student.itera.ac.id",
    val phone: String = "08991827544",
    val location: String = "Bandar Lampung, Indonesia",
    val isDarkMode: Boolean = false,
    val isContactVisible: Boolean = false,
    val isEditing: Boolean = false,
    val inputName: String = "",
    val inputBio: String = ""
)