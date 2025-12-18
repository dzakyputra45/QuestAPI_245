package com.example.myapi.apiservice

import com.example.myapi.modeldata.DataSiswa
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApiSiswa {

    @GET("bacateman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postSiswa(@Body datasiswa: DataSiswa):retrofit2.Response<Void>


    //    @GET("baca1Teman.php/{id}")
//    suspend fun getStasusSiswa(@Query("id") id: Int): DataSiswa

//    @PUT("editTM.php/{id}")
//    suspend fun editStatusSiswa(@Qyery("id") id: Int): DataSiswa

}