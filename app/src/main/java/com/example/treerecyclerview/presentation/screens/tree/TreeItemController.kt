package com.example.treerecyclerview.presentation.screens.tree

import com.airbnb.epoxy.TypedEpoxyController
import com.example.treerecyclerview.presentation.common.Node
import com.example.treerecyclerview.presentation.components.treeItemView
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class TreeItemController(
    val onClicked: (node: Node<TreeUi>) -> Unit,
) : TypedEpoxyController<TreeScreenState.Success>() {

    override fun buildModels(state: TreeScreenState.Success) {
        buildTreeItemsModels(state.currentTree.children)
    }

    private fun buildTreeItemsModels(nodes: List<Node<TreeUi>>) {
        nodes.forEach { node ->
            treeItemView {
                id(node.value.itemId.id)
                itemData(node.value)
                itemClickedListener { onClicked(node) }
            }
            if (node.value.isExpanded) {
                buildTreeItemsModels(node.children)
            }
        }
    }
}

