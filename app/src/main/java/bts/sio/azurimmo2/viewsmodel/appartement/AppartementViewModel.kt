package bts.sio.azurimmo2.viewsmodel.appartement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo2.api.RetrofitInstance
import bts.sio.azurimmo2.model.Appartement
import bts.sio.azurimmo2.model.Batiment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel pour gérer les données des appartements
class AppartementViewModel : ViewModel() {

    // Liste mutable des appartements
    private val _appartements = MutableStateFlow<List<Appartement>>(emptyList())
    val appartements: StateFlow<List<Appartement>> = _appartements

    // État de chargement
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Message d'erreur
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        // Chargement des données (exemple avec données simulées)
        getAppartements()
    }

    private fun getAppartements() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                // Idéalement, vous feriez un appel API ici
                // val response = RetrofitInstance.api.getAppartements()

                // Pour l'instant, nous utilisons des données simulées
                val batiment1 = Batiment(
                    id = 1,
                    adresse = "123 Rue Principale",
                    ville = "Nice",
                )

                val batiment2 = Batiment(
                    id = 2,
                    adresse = "456 Avenue des Champs",
                    ville = "Marseille",
                )

                _appartements.value = listOf(
                    Appartement(1, 101, 45.5f, 2, "Charmant T2 avec balcon", batiment1),
                    Appartement(2, 202, 60.0f, 3, "Appartement lumineux avec vue", batiment2),
                    Appartement(3, 303, 75.0f, 4, "Grand duplex au dernier étage", batiment1)
                )
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}