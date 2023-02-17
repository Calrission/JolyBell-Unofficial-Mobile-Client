package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutTopTitleScreenBinding

class TopTitleScreen: LinearLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, R.style.TopTitleLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){
        binding = LayoutTopTitleScreenBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.TopTitleScreen, defStyle, defStyle).apply {
            text = getString(R.styleable.TopTitleScreen_text) ?: "TextView"
            textColor = getColor(R.styleable.TopTitleScreen_textColor, 0)
            setBackgroundColor(getColor(R.styleable.TopTitleScreen_backgroundColor, 0))
            recycle()
        }
    }

    var text: String
    set(value) {
        field = value
        binding.title.text = field
    }

    @ColorInt
    var textColor: Int
    set(value) {
        field = value
        binding.title.setTextColor(value)
        binding.back.imageTintList = ColorStateList.valueOf(value)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.back.setOnClickListener(l)
    }

    private var binding: LayoutTopTitleScreenBinding
}