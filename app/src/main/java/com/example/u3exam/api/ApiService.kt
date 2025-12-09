package com.example.u3exam.api

import com.example.u3exam.model.RickAndMortyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    // The endpoint to get characters
    @GET("character")
    suspend fun getCharacters(): RickAndMortyResponse
}

object RetrofitInstance {
    // The base URL for the Rick and Morty API
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val api: ApiService by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}