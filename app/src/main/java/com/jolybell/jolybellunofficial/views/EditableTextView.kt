package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutEditableTextViewBinding
import com.jolybell.jolybellunofficial.сommon.utils.UnitUtils

class EditableTextView: LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int) : super(context, attr, defStyle){

        binding = LayoutEditableTextViewBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.EditableTextView, defStyle, defStyle).apply{
            binding.editTextView.apply {
                setText(getString(R.styleable.EditableTextView_android_text) ?: "" )
                setTextColor(getColor(R.styleable.EditableTextView_text_color, context.getColor(R.color.dark)))
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    getDimensionPixelSize(R.styleable.EditableTextView_android_textSize, textSize.toInt()).toFloat()
                )
                val type = getInt(R.styleable.EditableTextView_android_inputType, EditorInfo.TYPE_NULL)
                if (type != EditorInfo.TYPE_NULL)
                    inputType = type
                typeface = ResourcesCompat.getFont(context, R.font.futurabookc)
                isEnabled = getBoolean(R.styleable.EditableTextView_android_enabled, true)
                backgroundTintList = ColorStateList.valueOf(getColor(R.styleable.EditableTextView_backgroundTint, context.getColor(R.color.dark)))
            }
            binding.hint.apply {
                text = getString(R.styleable.EditableTextView_android_hint) ?: ""
                typeface = ResourcesCompat.getFont(context, R.font.futurabookc)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    getDimensionPixelSize(R.styleable.EditableTextView_hintSize, UnitUtils.dpToPx(14f, context).toInt()).toFloat()
                )
                setTextColor(getColor(R.styleable.EditableTextView_android_textColorHint, context.getColor(R.color.hint)))
            }
            binding.drawableRight.setImageDrawable(getDrawable(R.styleable.EditableTextView_android_drawableRight))
            recycle()
        }
    }
    private var binding: LayoutEditableTextViewBinding

    var text: String = ""
    set(value) {
        field = value
        binding.editTextView.setText(value)
    }
    get() {
        return binding.editTextView.text.toString()
    }
}