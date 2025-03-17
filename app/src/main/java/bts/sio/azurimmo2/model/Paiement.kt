package bts.sio.azurimmo2.model

import java.time.LocalDate

data class Paiement(
    val id: Int,
    val contrat: Contrat,
    val datePaiement: String,
    val montant: Double
)
