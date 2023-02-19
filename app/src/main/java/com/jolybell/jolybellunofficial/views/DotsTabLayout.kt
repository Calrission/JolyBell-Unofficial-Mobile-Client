package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.annotation.ColorInt
import com.google.android.material.tabs.TabLayout
import com.jolybell.jolybellunofficial.R

class DotsTabLayout: TabLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, R.style.DotsTabLayoutLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){

        context.obtainStyledAttributes(attr, R.styleable.DotsTabLayout, defStyle, defStyle).apply {
            dotsTint = getColor(R.styleable.DotsTabLayout_tintDots, 0)
            recycle()
        }

        addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                setIsChecked(tab, true)
            }

            override fun onTabUnselected(tab: Tab?) {
                setIsChecked(tab, false)
            }

            override fun onTabReselected(tab: Tab?) {}
        })

        if (isInEditMode){
            createDots(3)
        }
    }

    @ColorInt
    var dotsTint: Int

    init {
        tabGravity = GRAVITY_CENTER
        tabMode = MODE_SCROLLABLE
        setSelectedTabIndicator(null)
    }

    fun addNewTab(){
        addTab(newTab())
    }

    fun selectTab(index: Int){
        selectTab(getTabAt(index))
    }

    fun createDots(count: Int){
        repeat((0 until count).count()) {
            addTab(newTab())
        }
    }

    override fun newTab(): Tab {
        val newTab = super.newTab()
        newTab.view.minimumWidth = 0
        newTab.customView = DotCheckboxView(context).apply {
            buttonTintList = ColorStateList.valueOf(dotsTint)
        }
        return newTab
    }

    private fun setIsChecked(tab: Tab?, isChecked: Boolean) {
        if (tab != null && tab.customView != null) {
            (tab.customView as DotCheckboxView).isChecked = isChecked
        }
    }
}