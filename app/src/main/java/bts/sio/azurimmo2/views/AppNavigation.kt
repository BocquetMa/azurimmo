package bts.sio.azurimmo2.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bts.sio.azurimmo.views.appartement.AppartementList
import bts.sio.azurimmo.views.batiment.BatimentList
import bts.sio.azurimmo.views.contrat.ContratList
import bts.sio.azurimmo.views.locataire.LocataireList
import bts.sio.azurimmo.views.paiement.PaiementList
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "batiments_list",
        modifier = modifier
    ) {
        composable("batiments_list") {
            // Passe le navController à BatimentList
            BatimentList(navController = navController)
        }

        composable("appartements_list?batimentId={batimentId}") { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getString("batimentId")?.toIntOrNull()
            AppartementList(batimentId = batimentId)  // Passe le batimentId récupéré ou null
        }

        composable("appartements_list") {
            AppartementList(batimentId = null)  // Lorsque aucun batimentId n'est fourni
        }

        composable("contrats_list") {
            ContratList()
        }

        composable("locataires_list") {
            LocataireList()
        }

        composable("paiements_list") {
            PaiementList()
        }
    }
}
