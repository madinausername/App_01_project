package com.example.app_01_project.api

import com.example.app_01_project.enter.Login
import com.example.app_01_project.enter.UserReg
import com.example.app_01_project.enter.f
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("/book-api/login")
    fun login(@Body loginRequest: Login): Call<f>

    @POST("/book-api/register")
    fun register(@Body regRequest: UserReg):Call<UserReg>
}