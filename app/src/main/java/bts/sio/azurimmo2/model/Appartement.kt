package bts.sio.azurimmo2.model

data class Appartement(
    val id: Int,
    val numero: String,
    val surface: Float = 0f,
    val nombrePieces: Int = 1,
    val description: String = "",
    val batiment: Batiment? = null
)