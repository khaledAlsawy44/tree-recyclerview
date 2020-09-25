package com.example.treerecyclerview.presentation.screens.tree

import com.example.treerecyclerview.presentation.common.Node

sealed class TreeScreenState {
    object Loading : TreeScreenState()

    data class Success(
        val currentTree: Node<TreeUi>,
        val allTreeItems: List<TreeUi>
    ) : TreeScreenState()

    object Error : TreeScreenState()
}

sealed class TreeScreenActions {
    data class TreeItemClicked(val node: Node<TreeUi>) : TreeScreenActions()
}

sealed class TreeScreenEffects {
    data class OpenDetails(val treeItem: TreeUi) : TreeScreenEffects()
}