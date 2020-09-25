package com.example.treerecyclerview.presentation.screens.tree

import com.example.treerecyclerview.domain.entities.TreeItemId
import com.example.treerecyclerview.domain.entities.TreeItemParentId
import com.example.treerecyclerview.domain.entities.TreeItemTitle

data class TreeUi(
    val itemId: TreeItemId = TreeItemId(-1),
    val title: TreeItemTitle = TreeItemTitle(""),
    val parentId: TreeItemParentId = TreeItemParentId(-1),
    val level: Int = 0,
    val isExpanded: Boolean = false,
    val isLeaf: Boolean = false
)