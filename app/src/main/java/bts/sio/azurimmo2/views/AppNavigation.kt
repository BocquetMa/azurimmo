package bts.sio.azurimmo2.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import bts.sio.azurimmo.views.appartement.AppartementAdd
import bts.sio.azurimmo.views.appartement.AppartementList
import bts.sio.azurimmo.views.batiment.BatimentAdd
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
            val viewModel: BatimentViewModel = viewModel()
            BatimentList(viewModel = viewModel, navController = navController)
        }

        composable(
            "appartements_list?batimentId={batimentId}",
            arguments = listOf(navArgument("batimentId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getInt("batimentId")
            val finalBatimentId = if (batimentId == -1) null else batimentId
            AppartementList(
                batimentId = finalBatimentId,
                navController = navController
            )
        }

        composable("add_batiment") {
            BatimentAdd(navController = navController)
        }

        composable(
            "add_appartement/{batimentId}",
            arguments = listOf(navArgument("batimentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getInt("batimentId") ?: -1
            if (batimentId != -1) {
                AppartementAdd(batimentId = batimentId, navController = navController)
            } else {
                Text("Erreur : Identifiant de bâtiment manquant")
            }
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