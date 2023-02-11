package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.transition.Transition.TransitionListener
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet.Motion
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutLaunchAnimationBinding
import com.jolybell.jolybellunofficial.сommon.UnitUtils.Companion.dpToPx


interface OnFinishAnimation{
    fun onFinishAnimation()
}
class TopAnimationLogo: LinearLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attr: AttributeSet): super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyle: Int): super(context, attr, defStyle)

    private var binding: LayoutLaunchAnimationBinding =
        LayoutLaunchAnimationBinding.inflate(LayoutInflater.from(context), this, true)

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

        if (isInEditMode){
            // Preview mode in Android Studio
            binding.motion.setTransition(R.id.tra_animation_3)
            binding.motion.progress = 1f
            binding.mainMotion.progress = 1f
        }


    }
}