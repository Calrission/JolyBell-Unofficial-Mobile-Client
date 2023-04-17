package com.jolybell.jolybellunofficial.views

import android.R.attr.password
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ViewEditableTextBinding
import com.jolybell.jolybellunofficial.сommon.utils.UnitUtils


class EditableTextView: LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int) : super(context, attr, defStyle){

        binding = ViewEditableTextBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.EditableTextView, defStyle, defStyle).apply{
            binding.editTextView.apply {
                setText(getString(R.styleable.EditableTextView_android_text) ?: "" )
                setTextColor(getColor(R.styleable.EditableTextView_text_color, context.getColor(R.color.dark)))
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    getDimensionPixelSize(R.styleable.EditableTextView_android_textSize, textSize.toInt()).toFloat()
                )
                val type = getInt(R.styleable.EditableTextView_android_inputType, EditorInfo.TYPE_NULL)
                inputType = InputType.TYPE_CLASS_TEXT or type
                typeface = ResourcesCompat.getFont(context, R.font.futurabookc)
                isEnabled = getBoolean(R.styleable.EditableTextView_android_enabled, true)
                backgroundTintList = ColorStateList.valueOf(getColor(R.styleable.EditableTextView_backgroundTint, context.getColor(R.color.dark)))
            }
            binding.hint.apply {
                this@EditableTextView.hint = getString(R.styleable.EditableTextView_android_hint) ?: ""
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
    private var binding: ViewEditableTextBinding

    var text: String = ""
    set(value) {
        field = value
        binding.editTextView.setText(value)
    }
    get() {
        return binding.editTextView.text.toString()
    }

    var hint: String = ""
    set(value) {
        field = value
        binding.hint.text = value
    }
    get() = binding.hint.text.toString()

    var inputType: Int = 0
    set(value){
        field = value
        binding.editTextView.inputType
    }
    get() = binding.editTextView.inputType

    @SuppressLint("ClickableViewAccessibility")
    override fun setOnClickListener(l: OnClickListener?) {
        binding.root.setOnClickListener {
            l?.onClick(this)
        }

        binding.editTextView.isEnabled = true
        binding.editTextView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP)
                l?.onClick(this)
            true
        }
    }
}