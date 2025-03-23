package bts.sio.azurimmo.viewsmodel.contrat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo2.api.RetrofitInstance
import bts.sio.azurimmo2.model.Contrat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ContratViewModel : ViewModel() {

    private val _contrats = MutableStateFlow<List<Contrat>>(emptyList())
    val contrats: StateFlow<List<Contrat>> = _contrats

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getContrats()
    }

    private fun getContrats() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = RetrofitInstance.api.getContrats()

                val contratsTransformes = response.map { contrat ->
                    contrat.copy(
                        locataire = contrat.locataire.copy(
                            dateNaissance = LocalDate.parse(
                                contrat.locataire.dateNaissance,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            ).toString()
                        ),
                        dateDebut = LocalDate.parse(
                            contrat.dateDebut,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        ).toString(),
                        dateFin = LocalDate.parse(
                            contrat.dateFin,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        ).toString()
                    )
                }

                _contrats.value = contratsTransformes
            } catch (e: Exception) {
                _errorMessage.value =
                    "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}
