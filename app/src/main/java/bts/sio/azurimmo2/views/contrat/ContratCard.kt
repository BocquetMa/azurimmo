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



