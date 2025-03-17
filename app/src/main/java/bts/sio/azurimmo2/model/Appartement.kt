package bts.sio.azurimmo2.model

data class Appartement(
    val id: Int,
    val numero: String,
    val surface: Float,
    val nombrePieces: Int,
    val description: String,
    val batiment: Batiment?

)