package com.example.treerecyclerview.data.remote.datasource

import arrow.core.Either
import com.example.treerecyclerview.AppFailure
import com.example.treerecyclerview.domain.entities.TreeItem

interface ITreeDataRemoteDataSource {
    suspend fun fetch(): Either<AppFailure, List<TreeItem>>
}