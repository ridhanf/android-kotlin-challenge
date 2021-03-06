package com.fadhil.challenge.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org"
//    private const val AUTH_URL = "https://fadhil-auth.herokuapp.com"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

//    @Singleton
//    @Provides
//    fun provideAuthRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
//        .baseUrl(AUTH_URL)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

}