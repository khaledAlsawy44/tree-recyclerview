package com.example.treerecyclerview.presentation.screens.tree

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.treerecyclerview.R
import com.example.treerecyclerview.databinding.TreeFragmentBinding
import com.example.treerecyclerview.presentation.common.Node
import com.example.treerecyclerview.presentation.utils.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class TreeFragment : Fragment(R.layout.tree_fragment) {

    private val binding: TreeFragmentBinding by viewBinding(TreeFragmentBinding::bind)
    private val viewModel: TreeViewModel by viewModel()
    private val controller: TreeItemController by lazy {
        TreeItemController(::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.state.observe(viewLifecycleOwner) { state -> renderState(state) }
        viewModel.effect
            .onEach { effect -> onEffect(effect) }
            .flowOn(Dispatchers.Main)
            .launchIn(viewLifecycleOwner.lifecycleScope)


    }

    private fun initViews() {
        binding.treeItemsRv.setController(controller)
    }

    private fun onEffect(effect: TreeScreenEffects) {
        when (effect) {
            is TreeScreenEffects.OpenDetails -> {
                findNavController().navigate(
                    TreeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        effect.treeItem.title.title
                    )
                )
            }
        }
    }

    private fun renderState(state: TreeScreenState) {
        when (state) {
            TreeScreenState.Loading -> binding.showLoading()
            is TreeScreenState.Success -> binding.showSuccess(controller, state)
            TreeScreenState.Error -> binding.showError()
        }
    }

    private fun onItemClicked(node: Node<TreeUi>) {
        viewModel.dispatch(TreeScreenActions.TreeItemClicked(node))
    }
}

