package com.example.treerecyclerview.domain.repos

import arrow.core.Either
import com.example.treerecyclerview.AppFailure
import com.example.treerecyclerview.domain.entities.TreeItem

interface ITreeDataRepository {
    suspend fun fetch(): Either<AppFailure, List<TreeItem>>
}

