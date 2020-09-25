package com.example.treerecyclerview.presentation.screens.tree

import com.example.treerecyclerview.R
import com.example.treerecyclerview.databinding.TreeFragmentBinding
import com.example.treerecyclerview.presentation.utils.setVisibility
import kotlinx.coroutines.ExperimentalCoroutinesApi

fun TreeFragmentBinding.showLoading() {
    lottieAnimationView.setAnimation(R.raw.loading_lottie)
    lottieAnimationView.setVisibility(true)
    treeItemsRv.setVisibility(false)
}

fun TreeFragmentBinding.showError() {
    // add and show your error view
    lottieAnimationView.setVisibility(false)
    treeItemsRv.setVisibility(false)
}

@ExperimentalCoroutinesApi
fun TreeFragmentBinding.showSuccess(
    controller: TreeItemController,
    state: TreeScreenState.Success
) {
    lottieAnimationView.setVisibility(false)
    treeItemsRv.setVisibility(true)
    controller.setData(state)
}