package com.example.treerecyclerview.presentation.screens.tree

import com.example.treerecyclerview.domain.entities.TreeItem

fun TreeItem.toTreeUi(): TreeUi {
    return TreeUi(
        itemId = itemId,
        title = title,
        parentId = parentId,
        isLeaf = isLeaf
    )
}