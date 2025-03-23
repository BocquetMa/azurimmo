package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel
import bts.sio.azurimmo2.model.Appartement
import bts.sio.azurimmo2.model.Batiment

@Composable
fun AppartementAdd(batimentId: Int, navController: NavController) {
    val viewModel: AppartementViewModel = viewModel()
    var description by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var surface by remember { mutableStateOf("") }
    var nbPieces by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ajouter un appartement",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text("Numéro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = surface,
            onValueChange = { surface = it },
            label = { Text("Surface (m²)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nbPieces,
            onValueChange = { nbPieces = it },
            label = { Text("Nombre de pièces") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (numero.isNotBlank() && description.isNotBlank() && surface.isNotBlank() && nbPieces.isNotBlank()) {
                    val surfaceValue = surface.toFloatOrNull() ?: 0f
                    val nbPiecesValue = nbPieces.toIntOrNull() ?: 0

                    val batiment = Batiment(id = batimentId, adresse = "", ville = "")
                    val appartement = Appartement(
                        id = 0,
                        numero = numero,
                        description = description,
                        surface = surfaceValue,
                        nombrePieces = nbPiecesValue,
                        batiment = batiment
                    )

                    viewModel.addAppartement(appartement)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = numero.isNotBlank() && description.isNotBlank() &&
                    surface.isNotBlank() && nbPieces.isNotBlank()
        ) {
            Text("Ajouter l'appartement")
        }
    }
}