package com.slcode.freegameslist.model.api

import com.slcode.freegameslist.model.FreeGame
import retrofit2.Response
import retrofit2.http.GET

/**
 * Интерфейс для взаимодействия с FreeToGameAPI
 */
interface FreeGamesApi {
    /**
     * GET метод для получения списка всех бесплатных игр на ПК
     */
    @GET("/api/games?platform=pc")
    suspend fun getFreePcGames(): Response<List<FreeGame>>
}