package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ViewSizeCheckboxBinding
import com.jolybell.jolybellunofficial.models.ModelSize

class SizeCheckboxView: LinearLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, R.style.SizeProductViewLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){

        binding = ViewSizeCheckboxBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.SizeCheckboxView, defStyle, defStyle).apply {
            colorTextSelect = getColor(R.styleable.SizeCheckboxView_colorTextSelect, 0)
            colorTextNotSelect = getColor(R.styleable.SizeCheckboxView_colorTextNotSelect, 0)
            backgroundColorSelect = getColor(R.styleable.SizeCheckboxView_backgroundColorSelect, 0)
            backgroundColorNotSelect =
                getColor(R.styleable.SizeCheckboxView_backgroundColorNotSelect, 0)
            text = getString(R.styleable.SizeCheckboxView_sizeTitle) ?: ""
            recycle()
        }

        refresh()
    }

    var isChecked: Boolean = false
    set(value) {
        field = value
        refresh()
    }

    var text: String
    set(value){
        field = value
        binding.textSize.text = text
    }

    @ColorInt
    var colorTextSelect: Int
    @ColorInt
    var colorTextNotSelect: Int
    @ColorInt
    var backgroundColorSelect: Int
    @ColorInt
    var backgroundColorNotSelect: Int

    private val binding: ViewSizeCheckboxBinding

    private fun refresh(){
        val backgroundColor = if (isChecked) backgroundColorSelect else backgroundColorNotSelect
        val textColor = if (isChecked) colorTextSelect else colorTextNotSelect

        binding.textSize.setBackgroundColor(backgroundColor)
        binding.textSize.setTextColor(textColor)
    }


}