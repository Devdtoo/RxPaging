package com.htf.tasmemcom.repository.remote


import com.htf.tasmemcom.repository.tempModel.ApiResponse
import io.reactivex.Single
import retrofit2.http.*


interface NetworkService {

    @GET("api/users")
    fun getListData(
        @Query("page") pageNumber: Int): Single<ApiResponse>
}