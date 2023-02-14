package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.jolybell.jolybellunofficial.R

class DotCheckboxView: AppCompatCheckBox {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){
        setButtonDrawable(R.drawable.selector_dot_check_box)
    }
}