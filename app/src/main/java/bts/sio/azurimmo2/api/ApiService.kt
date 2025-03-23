package bts.sio.azurimmo2.api

import bts.sio.azurimmo2.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/batiments")
    suspend fun getBatiments(): List<Batiment>

    @GET("/appartements")
    suspend fun getAppartements(): List<Appartement>

    @GET("/contrats")
    suspend fun getContrats(): List<Contrat>

    @POST("/contrat")
    suspend fun addContrat(@Body contrat: Contrat): Contrat

    @GET("/locataires")
    suspend fun getLocataires(): List<Locataire>

    @GET("/paiements")
    suspend fun getPaiements(): List<Paiement>

    @GET("/appartements/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    @GET("/batiments/{batimentId}")
    suspend fun getBatiment(@Path("batimentId") batimentId: Int): Batiment
}
