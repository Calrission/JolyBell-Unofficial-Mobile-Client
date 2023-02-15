package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutTopTitleScreenBinding

class TopTitleScreen: LinearLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){
        binding = LayoutTopTitleScreenBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.TopTitleScreen, defStyle, 0).apply {
            text = getString(R.styleable.TopTitleScreen_text) ?: "TextView"

            recycle()
        }
    }

    var text: String
    set(value) {
        field = value
        binding.titleCategory.text = field
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.back.setOnClickListener(l)
    }

    private var binding: LayoutTopTitleScreenBinding
}