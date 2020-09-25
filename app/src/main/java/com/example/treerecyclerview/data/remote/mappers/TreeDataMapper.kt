package com.example.treerecyclerview.data.remote.mappers

import com.example.treerecyclerview.data.remote.entities.TreeDataResponse
import com.example.treerecyclerview.domain.entities.TreeItem
import com.example.treerecyclerview.domain.entities.TreeItemId
import com.example.treerecyclerview.domain.entities.TreeItemParentId
import com.example.treerecyclerview.domain.entities.TreeItemTitle

fun TreeDataResponse.toTreeItem(): TreeItem? {
    if (id == null ||
        parentId == null ||
        title == null ||
        isLeaf == null
    ) return null

    return TreeItem(
        itemId = TreeItemId(id),
        parentId = TreeItemParentId(parentId),
        title = TreeItemTitle(title),
        isLeaf = isLeaf
    )
}
//fun TreeDataResponse.toTreeItem(): TreeItem? {
//    if (id == null || parent_id == null || suspicion == null)
//        return null
//
//    return TreeItem(
//        itemId = TreeItemId(id),
//        parentId = TreeItemParentId(parent_id),
//        title = TreeItemTitle(suspicion),
//        isLeaf = reply != null && reply.isNotEmpty()
//    )
//}