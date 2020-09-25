package com.example.treerecyclerview.presentation.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.treerecyclerview.R
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel

open class MaterialConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val shapeDrawable = MaterialShapeDrawable()

    init {
        val shapeAppearanceModelBuilder = ShapeAppearanceModel
            .builder()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MaterialShapeAppearance,
            0,
            0
        ).apply {
            try {
                val cornerFamilyType =
                    getInt(R.styleable.MaterialShapeAppearance_cornerType, CornerFamily.ROUNDED)

                val defaultCornerSize =
                    getDimension(R.styleable.MaterialShapeAppearance_allCornersRadius, 0f)

                val topLeftCorner = getDimension(
                    R.styleable.MaterialShapeAppearance_cornerRadiusTopLeft,
                    defaultCornerSize
                )
                val topRightCorner = getDimension(
                    R.styleable.MaterialShapeAppearance_cornerRadiusTopRight,
                    defaultCornerSize
                )
                val bottomRightCorner = getDimension(
                    R.styleable.MaterialShapeAppearance_cornerRadiusBottomRight,
                    defaultCornerSize
                )
                val bottomLeftCorner = getDimension(
                    R.styleable.MaterialShapeAppearance_cornerRadiusBottomLeft,
                    defaultCornerSize
                )

                val backgroundColor = getColor(
                    R.styleable.MaterialShapeAppearance_backgroundColor,
                    ContextCompat.getColor(context, R.color.light_color)
                )

                val elevation = getDimension(
                    R.styleable.MaterialShapeAppearance_android_elevation,
                    0f
                )

                shapeAppearanceModelBuilder
                    .setAllCorners(
                        if (cornerFamilyType == CornerFamily.ROUNDED)
                            RoundedCornerTreatment()
                        else
                            CutCornerTreatment()
                    )
                    .setAllCornerSizes(defaultCornerSize)
                    .setTopLeftCornerSize(topLeftCorner)
                    .setTopRightCornerSize(topRightCorner)
                    .setBottomRightCornerSize(bottomRightCorner)
                    .setBottomLeftCornerSize(bottomLeftCorner)

                shapeDrawable.shapeAppearanceModel = shapeAppearanceModelBuilder.build()
                shapeDrawable.fillColor = ColorStateList.valueOf(backgroundColor)
                shapeDrawable.elevation = elevation
            } finally {
                recycle()
            }
        }

        val rippleColor =
            AppCompatResources.getColorStateList(context, R.color.colorPrimary).withAlpha(12)
        background = RippleDrawable(rippleColor, shapeDrawable, null)
    }

    fun setShapeAppearanceModel(shapeAppearanceModel: ShapeAppearanceModel) {
        shapeDrawable.shapeAppearanceModel = shapeAppearanceModel
    }

    fun setAllCornersRadius(@Px cornerSize: Float) {
        shapeDrawable.setCornerSize(cornerSize)
    }

    fun setTopLeftCornerSize(@Px cornerSize: Float) {
        shapeDrawable.shapeAppearanceModel = shapeDrawable
            .shapeAppearanceModel
            .toBuilder()
            .setTopLeftCornerSize(cornerSize)
            .build()
    }

    fun setTopRightCornerSize(@Px cornerSize: Float) {
        shapeDrawable.shapeAppearanceModel = shapeDrawable
            .shapeAppearanceModel
            .toBuilder()
            .setTopRightCornerSize(cornerSize)
            .build()
    }

    fun setBottomLeftCornerSize(@Px cornerSize: Float) {
        shapeDrawable.shapeAppearanceModel = shapeDrawable
            .shapeAppearanceModel
            .toBuilder()
            .setBottomLeftCornerSize(cornerSize)
            .build()
    }

    fun setBottomRightCornerSize(@Px cornerSize: Float) {
        shapeDrawable.shapeAppearanceModel = shapeDrawable
            .shapeAppearanceModel
            .toBuilder()
            .setBottomRightCornerSize(cornerSize)
            .build()
    }

    fun setStrokeColor(@ColorRes color: Int) {
        shapeDrawable.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, color))
    }

    fun setStrokeWidth(@Px width: Float) {
        shapeDrawable.strokeWidth = width
    }

    fun setBackgroundColorRes(@ColorRes id: Int) {
        val backgroundColor = ContextCompat.getColor(context, id)
        shapeDrawable.fillColor = ColorStateList.valueOf(backgroundColor)
    }

    fun setBackgroundColorCode(color: Int) {
        shapeDrawable.fillColor = ColorStateList.valueOf(color)
    }

    fun setMaterialElevation(elevation: Float) {
        shapeDrawable.elevation = elevation
    }
}
