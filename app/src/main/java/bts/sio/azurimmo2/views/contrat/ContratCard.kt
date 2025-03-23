package bts.sio.azurimmo.views.contrat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel
import bts.sio.azurimmo2.model.Contrat
import bts.sio.azurimmo2.model.Locataire
import bts.sio.azurimmo2.model.Appartement

@Composable
fun ContratCard(contrat: Contrat) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Contrat ID: ${contrat.id}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Locataire: ${contrat.locataire.prenom} ${contrat.locataire.nom}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Appartement N°${contrat.appartement.numero}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Loyer: ${contrat.montantLoyer}€", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Début: ${contrat.dateDebut}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Fin: ${contrat.dateFin.ifEmpty { "En cours" }}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun AjouterContratScreen(viewModel: ContratViewModel = viewModel(), onContratAdded: () -> Unit) {
    var locataire by remember { mutableStateOf("") }
    var appartement by remember { mutableStateOf("") }
    var dateDebut by remember { mutableStateOf("") }
    var dateFin by remember { mutableStateOf("") }
    var montantLoyer by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = locataire, onValueChange = { locataire = it }, label = { Text("Locataire ID") })
        OutlinedTextField(value = appartement, onValueChange = { appartement = it }, label = { Text("Appartement ID") })
        OutlinedTextField(value = dateDebut, onValueChange = { dateDebut = it }, label = { Text("Date Début (yyyy-MM-dd)") })
        OutlinedTextField(value = dateFin, onValueChange = { dateFin = it }, label = { Text("Date Fin (yyyy-MM-dd) (optionnel)") })
        OutlinedTextField(value = montantLoyer, onValueChange = { montantLoyer = it }, label = { Text("Montant Loyer") })

        if (errorMessage != null) {
            Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val locataireId = locataire.toIntOrNull()
                val appartementId = appartement.toIntOrNull()
                val loyer = montantLoyer.toDoubleOrNull()

                if (locataireId != null && appartementId != null && loyer != null) {
                    val contrat = Contrat(
                        id = 0,
                        locataire = Locataire(id = locataireId, prenom = "?", nom = "?", dateNaissance = "?"),
                        appartement = Appartement(id = appartementId, numero = "Non défini"),
                        dateDebut = dateDebut,
                        dateFin = if (dateFin.isNotEmpty()) dateFin else "",
                        montantLoyer = loyer
                    )
                    viewModel.addContrat(contrat, onSuccess = onContratAdded, onError = { errorMessage = "Erreur lors de l'ajout du contrat" })
                } else {
                    errorMessage = "Veuillez remplir correctement tous les champs."
                }
            }
        ) {
            Text("Ajouter Contrat")
        }
    }
}
