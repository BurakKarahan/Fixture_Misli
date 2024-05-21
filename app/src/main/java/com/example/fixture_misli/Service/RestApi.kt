package com.example.fixture_misli.Service

import com.example.fixture_misli.Model.Misli
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {
    @GET("matches")
    fun getFixtureResult() : Call<Misli>
}