package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ViewCounterBinding

class ProductCounterView: LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, R.style.ProductCounterViewLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int) : super(context, attr, defStyle){
        binding = ViewCounterBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.ProductCounterView, defStyle, defStyle).apply {
            colorTint = getColor(R.styleable.ProductCounterView_tintStroke, 0)
            colorBackground = getColor(R.styleable.ProductCounterView_backgroundColorCounter, 0)
            recycle()
        }

        binding.minusBtn.setOnClickListener {
            if (count > 1)
                count -= 1
        }

        binding.plusBtn.setOnClickListener {
            count += 1
        }
    }

    private val binding: ViewCounterBinding

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

    var onChangeCounter: OnChangeCounter? = null

    var count: Int = 1
    set(value) {
        field = value
        binding.count.text = value.toString()
        onChangeCounter?.onChange(field)
    }
    interface OnChangeCounter{
        fun onChange(count: Int)
    }
}

