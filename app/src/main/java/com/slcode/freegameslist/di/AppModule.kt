package com.slcode.freegameslist.di

import com.slcode.freegameslist.model.api.FreeGamesApi
import com.slcode.freegameslist.viewmodel.GamesListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        Retrofit.Builder().baseUrl("https://www.freetogame.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(FreeGamesApi::class.java)
    }
    viewModel {
        GamesListViewModel(get())
    }
}