package bts.sio.azurimmo2.model

import java.time.LocalDate

data class Contrat(
    val id: Int,
    val locataire: Locataire,
    val appartement: Appartement,
    val dateDebut: String,
    val dateFin: String,
    val montantLoyer: Double
)
