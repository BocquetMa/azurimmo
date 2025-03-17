package bts.sio.azurimmo2.api

import bts.sio.azurimmo2.model.*
import retrofit2.http.GET

interface ApiService {
    @GET("/batiments")
    suspend fun getBatiments(): List<Batiment>

    @GET("/appartements")
    suspend fun getAppartements(): List<Appartement>

    @GET("/contrats")
    suspend fun getContrats(): List<Contrat>

    @GET("/locataires")
    suspend fun getLocataires(): List<Locataire>

    @GET("/paiements")
    suspend fun getPaiements(): List<Paiement>
}
