package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutLaunchAnimationBinding

class TopAnimationLogo: LinearLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attr: AttributeSet): super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyle: Int): super(context, attr, defStyle)

    private var binding: LayoutLaunchAnimationBinding =
        LayoutLaunchAnimationBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onFinishInflate() {
        super.onFinishInflate()

        if (isInEditMode){
            // Preview mode in Android Studio
            binding.motion.setTransition(R.id.tra_animation_3)
            binding.motion.progress = 1f
        }else{
            binding.motion.transitionToEnd()
        }
    }
}