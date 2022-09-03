package com.ashdroid.shaadiassignment.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.ashdroid.shaadiassignment.R
import com.ashdroid.shaadiassignment.databinding.ViewCardActionBinding

class CardActionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding: ViewCardActionBinding

    @DrawableRes
    private var actionIcon: Int = 0

    @DrawableRes
    private var actionIconBackground: Int = 0

    init {
        View.inflate(context, R.layout.view_card_action, this).also {
            binding = ViewCardActionBinding.bind(it)
        }
        initData(attrs)
    }

    private fun initData(attrs: AttributeSet?) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CardActionView, 0, 0)
        try {
            if (typedArray.hasValue(R.styleable.CardActionView_actionText)) {
                binding.tvLabel.text = typedArray.getString(R.styleable.CardActionView_actionText)
            }
            if (typedArray.hasValue(R.styleable.CardActionView_actionIcon)) {
                val drawableRes =
                    typedArray.getResourceId(R.styleable.CardActionView_actionIcon, 0)
                if (drawableRes != 0) {
                    actionIcon = drawableRes
                    binding.imIcon.setImageResource(actionIcon)
                }
            }
            if (typedArray.hasValue(R.styleable.CardActionView_actionBackground)) {
                val drawableRes =
                    typedArray.getResourceId(R.styleable.CardActionView_actionBackground, 0)
                if (drawableRes != 0) {
                    actionIconBackground = drawableRes
                    binding.imIcon.setBackgroundResource(actionIconBackground)
                }
            }
        } finally {
            typedArray.recycle()
        }
    }
}