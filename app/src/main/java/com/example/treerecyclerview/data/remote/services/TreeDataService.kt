package com.example.treerecyclerview.data.remote.services

import com.example.treerecyclerview.data.remote.entities.TreeDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TreeDataService {
    @GET("replace this with your API url")
    suspend fun fetchTreeData(): Response<List<TreeDataResponse>>
}