package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ViewTopAnimationLogoBinding


interface OnFinishAnimation{
    fun onFinishAnimation()
}
class TopAnimationLogo: LinearLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){
        binding = ViewTopAnimationLogoBinding.inflate(LayoutInflater.from(context), this, true)

        context.obtainStyledAttributes(attr, R.styleable.TopAnimationLogo, defStyle, 0).apply {
            val jumpToEnd = getBoolean(R.styleable.TopAnimationLogo_jumpToEnd, false)

            if (isInEditMode || jumpToEnd){
                binding.motion.setTransition(R.id.tra_animation_3)
                binding.motion.progress = 1f
                binding.mainMotion.progress = 1f
            }

            recycle()
        }
    }

    private var binding: ViewTopAnimationLogoBinding

    var onFinishAnimation: OnFinishAnimation? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

        binding.mainMotion.setTransitionListener(object: MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float,
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                val params = binding.motion.layoutParams
                params.height = resources.getDimension(R.dimen.height_top_logo).toInt()
                binding.motion.layoutParams = params
                onFinishAnimation?.onFinishAnimation()
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float,
            ) {

            }

        })

        binding.motion.setTransitionListener(object: MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
            ) {
                if (startId == R.id.middle && endId == R.id.end){
                    binding.mainMotion.transitionToEnd()
                }
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float,
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float,
            ) {

            }

        })
    }
}