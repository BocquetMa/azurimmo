package bts.sio.azurimmo.viewsmodel.paiement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo2.api.RetrofitInstance
import bts.sio.azurimmo2.model.Paiement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PaiementViewModel : ViewModel() {

    private val _paiements = MutableStateFlow<List<Paiement>>(emptyList())
    val paiements: StateFlow<List<Paiement>> = _paiements

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getPaiements()
    }

    private fun getPaiements() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = RetrofitInstance.api.getPaiements()
                val paiementsTransformes = response.map { paiement ->
                    paiement.copy(
                        datePaiement = LocalDate.parse(
                            paiement.datePaiement,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        ).toString()
                    )
                }
                _paiements.value = paiementsTransformes

            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
