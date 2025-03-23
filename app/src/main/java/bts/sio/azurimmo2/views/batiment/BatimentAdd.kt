package bts.sio.azurimmo.views.batiment

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel
import bts.sio.azurimmo2.model.Batiment

@Composable
fun BatimentAdd(navController: NavController) {
    val viewModel: BatimentViewModel = viewModel()
    var adresse by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ajouter un bâtiment",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = adresse,
            onValueChange = { adresse = it },
            label = { Text("Adresse") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = ville,
            onValueChange = { ville = it },
            label = { Text("Ville") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (adresse.isNotBlank() && ville.isNotBlank()) {
                    val batiment = Batiment(id = 0, adresse = adresse, ville = ville)
                    viewModel.addBatiment(batiment)
                    navController.navigate("batiments_list")
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = adresse.isNotBlank() && ville.isNotBlank()
        ) {
            Text("Ajouter le bâtiment")
        }
    }
}