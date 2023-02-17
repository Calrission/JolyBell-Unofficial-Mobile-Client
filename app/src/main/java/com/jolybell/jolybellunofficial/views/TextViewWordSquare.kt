package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.сommon.utils.UnitUtils.Companion.dpToPx


class TextViewWordSquare: FlexboxLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, R.style.TextViewWordSquareLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){
        context.obtainStyledAttributes(attr, R.styleable.TextViewWordSquare, defStyle, defStyle).apply {

            val wordsStr: String = getString(R.styleable.TextViewWordSquare_words) ?: ""
            val rectWordsStr = getString(R.styleable.TextViewWordSquare_rect_words) ?: ""

            textSize = getDimensionPixelSize(R.styleable.TextViewWordSquare_text_size, 0)

            colorTextSquare = getColor(R.styleable.TextViewWordSquare_textColorSquare, 0)
            colorText = getColor(R.styleable.TextViewWordSquare_textColorNotSquare, 0)
            colorSquare = getColor(R.styleable.TextViewWordSquare_squareColor, 0)

            words = wordsStr.split(" ")
            indexesSquare = rectWordsStr.split(" ").map { words.indexOf(it) }.toMutableList()

            recycle()
        }

        refresh()
    }

    private var words = listOf<String>()
    private var indexesSquare = mutableListOf<Int>()
    private var textSize: Int

    @ColorInt
    var colorTextSquare: Int
    @ColorInt
    var colorText: Int
    @ColorInt
    var colorSquare: Int

    init {
        justifyContent = JustifyContent.CENTER
        flexDirection = FlexDirection.ROW
        alignItems = AlignItems.CENTER
        flexWrap = FlexWrap.WRAP
    }

    fun setText(text: String, sep: String=" "){
        words = text.split(sep)
        indexesSquare.clear()
    }

    private fun refresh(){
        words.forEachIndexed{ index, word ->
            val view = if (index in indexesSquare)
                createSquareText(word)
            else
                createText(word, colorText)
            setMarginItem(view, index)
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
        lin.setPadding(
            dpToPx(8f, context).toInt(), dpToPx(8f, context).toInt(),
            dpToPx(8f, context).toInt(), dpToPx(8f, context).toInt()
        )
        val params = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lin.layoutParams = params
        lin.setBackgroundColor(colorSquare)
        val textView = createText(text, colorTextSquare)
        lin.addView(textView)
        return lin
    }

    private fun createText(text: String, @ColorInt colorText: Int): View{
        val textView = TextView(context)
        val params = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        textView.layoutParams = params
        textView.text = text
        textView.setTextColor(colorText)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        textView.typeface = ResourcesCompat.getFont(context, R.font.futurademic)
        return textView
    }

    private fun setMarginItem(item: View, position: Int){
        val margin = resources.getDimension(R.dimen.margin_item_slogan).toInt()
        val layoutParams = item.layoutParams as LinearLayout.LayoutParams
        if (position == 0)
            layoutParams.setMargins(margin, 0, margin, 0)
        else
            layoutParams.setMargins(0, 0, margin, 0)
        item.layoutParams = layoutParams
    }
}