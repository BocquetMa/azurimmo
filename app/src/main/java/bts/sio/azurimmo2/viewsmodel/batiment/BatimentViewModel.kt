package bts.sio.azurimmo.viewsmodel.batiment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo2.model.Batiment
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo2.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

// ViewModel pour gérer les données des bâtiments
class BatimentViewModel : ViewModel() {

    // Liste mutable des bâtiments
    private val _batiments = MutableStateFlow<List<Batiment>>(emptyList())
    val batiments: StateFlow<List<Batiment>> = _batiments

    private val _batiment = MutableStateFlow<Batiment?>(null)
    val batiment: StateFlow<Batiment?> = _batiment

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    fun getBatiment(batimentId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val batiment = RetrofitInstance.api.getBatiment(batimentId) // Retourne un seul Batiment
                _batiment.value = batiment

            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement du bâtiment terminé")
            }
        }
    }

}
