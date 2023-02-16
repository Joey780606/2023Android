package com.example.p01comicslibrary.model.api

import com.example.p01comicslibrary.model.CharactersApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    fun getCharacters(@Query("nameStartsWith") name: String): Call<CharactersApiResponse>
}