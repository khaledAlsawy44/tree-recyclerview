package com.example.treerecyclerview.data.repository

import arrow.core.Either
import com.example.treerecyclerview.data.remote.datasource.ITreeDataRemoteDataSource
import com.example.treerecyclerview.AppFailure
import com.example.treerecyclerview.domain.entities.TreeItem
import com.example.treerecyclerview.domain.repos.ITreeDataRepository

class TreeDataRepository(
    private val remote: ITreeDataRemoteDataSource
) : ITreeDataRepository {
    override suspend fun fetch(): Either<AppFailure, List<TreeItem>> {
        return remote.fetch()
    }

}