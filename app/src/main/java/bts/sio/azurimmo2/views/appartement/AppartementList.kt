package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel

@Composable
fun AppartementList(
    batimentId: Int? = null,
    appartementViewModel: AppartementViewModel = viewModel(),
    batimentViewModel: BatimentViewModel = viewModel(),
    navController: NavController
) {
    val appartements by appartementViewModel.appartements.collectAsState()
    val isLoading by appartementViewModel.isLoading.collectAsState()
    val errorMessage by appartementViewModel.errorMessage.collectAsState()
    val batiment by batimentViewModel.batiment.collectAsState()

    LaunchedEffect(batimentId) {
        if (batimentId == null) {
            appartementViewModel.getAppartements()
        } else {
            appartementViewModel.getAppartementsByBatiment(batimentId)
            batimentViewModel.getBatiment(batimentId)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erreur inconnue",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {
                LazyColumn {
                    if (batiment != null) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally // Centrer le contenu horizontalement
                            ) {
                                Text(
                                    text = "Informations sur le bâtiment",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Adresse : ${batiment?.adresse ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Ville : ${batiment?.ville ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        item {
                            Button(
                                onClick = {
                                    navController.navigate("add_appartement/${batiment?.id}")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Ajouter")
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Ajouter un appartement")
                                }
                            }
                        }
                    }

                    if (appartements.isNotEmpty()) {
                        item {
                            Text(
                                text = "Liste des appartements",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        items(appartements) { appartement ->
                            AppartementCard(appartement = appartement)
                        }
                    } else {
                        item {
                            Text(
                                text = "Pas d'appartement pour ce bâtiment",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}