package bts.sio.azurimmo2.api

import bts.sio.azurimmo2.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/batiments")
    suspend fun getBatiments(): List<Batiment>

    @GET("/appartements")
    suspend fun getAppartements(): List<Appartement>

    @GET("/appartement/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    @GET("/batiment/{id}")
    suspend fun getBatiment(@Path("id") batimentId: Int): Response<Batiment>

    @POST("/batiment")
    suspend fun addBatiment(@Body batiment: Batiment): Response<Batiment>

    @POST("/appartement")
    suspend fun addAppartement(@Body appartement: Appartement): Response<Appartement>

    @GET("/contrats")
    suspend fun getContrats(): List<Contrat>

    @GET("/locataires")
    suspend fun getLocataires(): List<Locataire>

    @GET("/paiements")
    suspend fun getPaiements(): List<Paiement>
}