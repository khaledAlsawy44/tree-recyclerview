package com.example.treerecyclerview.presentation.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.treerecyclerview.R
import com.example.treerecyclerview.databinding.ViewTreeItemBinding
import com.example.treerecyclerview.presentation.screens.tree.TreeUi
import com.example.treerecyclerview.presentation.utils.setMargins
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks


@ExperimentalCoroutinesApi
@FlowPreview
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TreeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewTreeItemBinding =
        ViewTreeItemBinding.inflate(LayoutInflater.from(context), this)

    private var viewScope: CoroutineScope? = null

    var itemClickedListener: (() -> Unit)? = null
        @CallbackProp set

    init {
        setBackgroundColorRes(R.color.colorPrimaryDark)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        viewScope = scope

        binding.expandIv.clicks()
            .debounce(250)
            .onEach { itemClickedListener?.invoke() }
            .launchIn(scope)

        clicks()
            .debounce(250)
            .onEach { itemClickedListener?.invoke() }
            .launchIn(scope)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewScope?.cancel()
    }


    @ModelProp
    fun setItemData(treeUi: TreeUi) {
        binding.titleTv.text = treeUi.title.title
        binding.subTitleTv.text = "SubTitle of ${treeUi.title.title}"



        setMargins(treeUi.level * 16)

        binding.expandIv.setImageResource(
            if (treeUi.isLeaf) {
                R.drawable.ic_dot
            } else {
                if (treeUi.isExpanded) {
                    R.drawable.ic_baseline_remove_circle_24
                } else {
                    R.drawable.ic_baseline_add_circle_24
                }
            }
        )

    }

    @ColorInt
    fun darkenColor(@ColorInt color: Int, level: Float): Int {
        return ColorUtils.blendARGB(color, Color.BLACK, level)
    }
}