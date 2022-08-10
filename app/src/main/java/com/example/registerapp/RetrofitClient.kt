package com.example.registerapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object RetrofitClient {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build();


    val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun fieldsService() = retrofitBuilder.create(NewsService::class.java)
}

interface NewsService {
    @GET("405138c7-4723-4a2a-84d5-c45e3aa24550")
    suspend fun getFields(): Response<List<List<FieldModel>>>
}