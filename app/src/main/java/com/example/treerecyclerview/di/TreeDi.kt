package com.example.treerecyclerview.di

import com.example.treerecyclerview.data.remote.datasource.ITreeDataRemoteDataSource
import com.example.treerecyclerview.data.remote.datasource.TreeDataRemoteDataSource
import com.example.treerecyclerview.data.remote.services.TreeDataService
import com.example.treerecyclerview.data.repository.TreeDataRepository
import com.example.treerecyclerview.domain.repos.ITreeDataRepository
import com.example.treerecyclerview.presentation.screens.tree.TreeStateMachine
import com.example.treerecyclerview.presentation.screens.tree.TreeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val treeModule = module {

    single { treeService(get()) }
    factory { TreeStateMachine(get()) }
    viewModel { TreeViewModel(get()) }

    single<ITreeDataRepository> { TreeDataRepository(get()) }
    single<ITreeDataRemoteDataSource> { TreeDataRemoteDataSource(get()) }
}

fun treeService(retrofit: Retrofit): TreeDataService {
    return retrofit.create(TreeDataService::class.java)
}