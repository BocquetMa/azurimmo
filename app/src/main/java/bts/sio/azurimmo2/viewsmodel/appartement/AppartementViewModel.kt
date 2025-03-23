package bts.sio.azurimmo.viewsmodel.appartement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo2.api.RetrofitInstance
import bts.sio.azurimmo2.model.Appartement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppartementViewModel : ViewModel() {

    private val _appartements = MutableStateFlow<List<Appartement>>(emptyList())
    val appartements: StateFlow<List<Appartement>> = _appartements

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getAppartements() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = RetrofitInstance.api.getAppartements()
                _appartements.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAppartementsByBatiment(batimentId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitInstance.api.getAppartementsByBatimentId(batimentId)
                _appartements.value = response

            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement des appartements du batiment sélectionné terminé: " + batimentId)
            }
        }
    }

    fun addAppartement(appartement: Appartement) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.addAppartement(appartement)
                if (response.isSuccessful) {
                    if (appartement.batiment != null && appartement.batiment.id > 0) {
                        getAppartementsByBatiment(appartement.batiment.id.toInt())
                    } else {
                        getAppartements()
                    }
                } else {
                    _errorMessage.value = "Erreur lors de l'ajout de l'appartement : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}