package bts.sio.azurimmo2.model

import java.time.LocalDate
import com.fasterxml.jackson.annotation.JsonFormat

data class Locataire(
    val id: Int,
    val prenom: String,
    val nom: String,
    val dateNaissance: String

)