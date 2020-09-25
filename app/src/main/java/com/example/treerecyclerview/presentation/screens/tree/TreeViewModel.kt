package com.example.treerecyclerview.presentation.screens.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class TreeViewModel(
    private val stateMachine: TreeStateMachine
) : ViewModel() {

    private val _state = MutableLiveData<TreeScreenState>()
    val state: LiveData<TreeScreenState> = _state

    val effect = stateMachine.effect


    init {
        viewModelScope.launch {
            stateMachine.state.collect { newState ->
                _state.value = newState
            }
        }
    }

    fun dispatch(action: TreeScreenActions) {
        viewModelScope.launch {
            stateMachine.dispatch(action)
        }
    }

    fun currentState() = state.value

}