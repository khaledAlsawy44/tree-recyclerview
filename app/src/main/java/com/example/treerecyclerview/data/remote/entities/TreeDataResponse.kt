package com.example.treerecyclerview.data.remote.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TreeDataResponse(
    val id: Int? = null,
    val title: String? = null,
    val parentId: Int? = null,
    val isLeaf: Boolean? = null
)
