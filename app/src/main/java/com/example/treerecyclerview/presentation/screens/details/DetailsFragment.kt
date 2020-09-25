package com.example.treerecyclerview.presentation.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.treerecyclerview.R
import com.example.treerecyclerview.databinding.DetailsFragmentBinding
import com.example.treerecyclerview.presentation.utils.viewBinding

class DetailsFragment : Fragment(R.layout.details_fragment) {
    private val binding: DetailsFragmentBinding by viewBinding(DetailsFragmentBinding::bind)
    private val navArgs: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemTitle.text = navArgs.title

    }

}