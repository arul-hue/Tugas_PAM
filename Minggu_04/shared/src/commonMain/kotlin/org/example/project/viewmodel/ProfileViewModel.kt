package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.data.ProfileUiState

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
    }

    fun toggleContactVisibility() {
        _uiState.update { it.copy(isContactVisible = !it.isContactVisible) }
    }

    fun startEditing() {
        _uiState.update { currentState ->
            currentState.copy(
                isEditing = true,
                inputName = currentState.name,
                inputBio = currentState.bio
            )
        }
    }

    fun onInputNameChange(newName: String) {
        _uiState.update { it.copy(inputName = newName) }
    }

    fun onInputBioChange(newBio: String) {
        _uiState.update { it.copy(inputBio = newBio) }
    }

    fun saveProfile() {
        _uiState.update { currentState ->
            currentState.copy(
                name = currentState.inputName,
                bio = currentState.inputBio,
                isEditing = false
            )
        }
    }

    fun cancelEditing() {
        _uiState.update { it.copy(isEditing = false) }
    }
}