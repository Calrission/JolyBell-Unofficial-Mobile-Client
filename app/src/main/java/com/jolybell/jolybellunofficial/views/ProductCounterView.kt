package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutCounterViewBinding

class ProductCounterView: LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, R.style.ProductCounterViewLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int) : super(context, attr, defStyle){
        binding = LayoutCounterViewBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.ProductCounterView, defStyle, defStyle).apply {
            colorTint = getColor(R.styleable.ProductCounterView_tintStroke, 0)
            colorBackground = getColor(R.styleable.ProductCounterView_backgroundColorCounter, 0)
            recycle()
        }
    }

    private val binding: LayoutCounterViewBinding

    @ColorInt
    private var colorTint: Int
    set(value) {
        field = value
        binding.count.setTextColor(value)
        binding.minusImg.imageTintList = ColorStateList.valueOf(value)
        binding.plusImg.imageTintList = ColorStateList.valueOf(value)
        binding.line2.setBackgroundColor(value)
        binding.line1.setBackgroundColor(value)
        binding.cardCounter.strokeColor = value
    }

    @ColorInt
    private var colorBackground: Int
    set(value){
        field = value
        binding.cardCounter.setCardBackgroundColor(value)
    }
}

