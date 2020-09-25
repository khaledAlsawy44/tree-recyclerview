package com.example.treerecyclerview.domain.entities


inline class TreeItemId(val id: Int)
inline class TreeItemParentId(val id: Int)
inline class TreeItemTitle(val title: String)

data class TreeItem(
    val itemId: TreeItemId,
    val title: TreeItemTitle,
    val parentId: TreeItemParentId,
    val isLeaf: Boolean
)