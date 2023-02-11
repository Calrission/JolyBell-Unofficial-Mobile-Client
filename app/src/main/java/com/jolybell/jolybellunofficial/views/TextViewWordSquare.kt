package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent
import com.jolybell.jolybellunofficial.R

class TextViewWordSquare: FlexboxLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attr: AttributeSet): super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyle: Int): super(context, attr, defStyle)

    private var words = listOf<String>()
    private var indexesSquare = mutableListOf<Int>()

    init {
        justifyContent = JustifyContent.CENTER
        flexDirection = FlexDirection.ROW
        alignItems = AlignItems.CENTER
        flexWrap = FlexWrap.WRAP

        if (isInEditMode){
            setText("test test test")
            addIndexSquare(1)
        }

        refresh()
    }

    fun setText(text: String, sep: String=" "){
        words = text.split(sep)
        this.words = words
    }

    fun refresh(){
        words.forEachIndexed{ index, word ->
            val view = if (index in indexesSquare)
                createSquareText(word)
            else
                createText(word)
            addView(view)
        }
    }

    fun addIndexSquare(index: Int){
        indexesSquare.add(index)
    }

    fun removeIndexSquare(index: Int){
        indexesSquare.remove(index)
    }

    private fun createSquareText(text: String): View {
        val lin = LinearLayout(context)
        lin.setPadding(8, 8, 8, 8)
        val textView = createText(text, R.color.text_word_in_square_slogan)
        lin.addView(textView)
        return lin
    }

    private fun createText(text: String, @ColorRes colorRes: Int = R.color.text_word_in_slogan): View{
        val textView = TextView(context)
        textView.text = text
        textView.setTextColor(context.getColor(colorRes))
        textView.textSize = resources.getDimension(R.dimen.text_size_slogan)
        return textView
    }
}