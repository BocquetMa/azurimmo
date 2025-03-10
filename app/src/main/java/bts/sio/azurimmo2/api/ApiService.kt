package bts.sio.azurimmo2.api

import bts.sio.azurimmo2.model.Batiment
import retrofit2.http.GET

interface ApiService {
    @GET("/batiments")
    suspend fun getBatiments(): List<Batiment>
}