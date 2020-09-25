package com.example.treerecyclerview.presentation.screens.tree

import arrow.core.Either
import com.example.treerecyclerview.domain.repos.ITreeDataRepository
import com.example.treerecyclerview.presentation.common.Node
import com.freeletics.flowredux.GetState
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.SetState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class TreeStateMachine(
    private val repo: ITreeDataRepository
) : FlowReduxStateMachine<TreeScreenState, TreeScreenActions>(
    TreeScreenState.Loading
) {

    private val _effect = Channel<TreeScreenEffects>(Channel.CONFLATED)
    val effect = _effect.receiveAsFlow()
    private suspend fun dispatchEffect(effect: TreeScreenEffects) {
        _effect.send(effect)
    }


    init {

        spec {

            inState<TreeScreenState.Loading> {
                onEnter(block = ::loadFirstLevel)
            }

            inState<TreeScreenState.Success> {
                on(block = ::treeItemClicked)
            }

        }
    }

    private suspend fun loadFirstLevel(
        getState: GetState<TreeScreenState>,
        setState: SetState<TreeScreenState>
    ) {
        when (val result = repo.fetch()) {
            is Either.Left -> {
                setState {
                    TreeScreenState.Error
                }
            }
            is Either.Right -> {
                val allTreeItems = result.b.map { treeItem ->
                    treeItem.toTreeUi()
                }
                val root: Node<TreeUi> = Node(TreeUi())
                allTreeItems.forEach {
                    if (it.parentId.id == 0) {
                        root.addChild(Node(it))
                    }
                }
                setState {
                    TreeScreenState.Success(
                        allTreeItems = allTreeItems,
                        currentTree = root
                    )
                }
            }
        }
    }


    private suspend fun treeItemClicked(
        action: TreeScreenActions.TreeItemClicked,
        getState: GetState<TreeScreenState>,
        setState: SetState<TreeScreenState>
    ) {
        val currentState = getState()
        if (currentState is TreeScreenState.Success) {

            val clickedItem = action.node.value
            if (clickedItem.isLeaf) {
                dispatchEffect(TreeScreenEffects.OpenDetails(clickedItem))
            } else {
                if (clickedItem.isExpanded) {
                    action.node.value = clickedItem.copy(
                        isExpanded = false
                    )
                } else {
                    action.node.value = clickedItem.copy(
                        isExpanded = true
                    )
                    if (action.node.children.isEmpty()) {
                        action.node.children = currentState.allTreeItems.filter {
                            it.parentId.id == action.node.value.itemId.id
                        }.mapTo(mutableListOf()) {
                            Node(it.copy(level = clickedItem.level + 1))
                        }
                    }
                }
                setState {
                    currentState.copy(
                        currentTree = currentState.currentTree
                    )
                }
            }
        }
    }

}




