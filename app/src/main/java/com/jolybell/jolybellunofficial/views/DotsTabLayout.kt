package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

class DotsTabLayout: TabLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){
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
            addTab(newTab())
            addTab(newTab())
            addTab(newTab())
        }
    }

    init {
        tabGravity = GRAVITY_CENTER
        tabMode = MODE_SCROLLABLE
        setSelectedTabIndicator(null)
    }

    fun selectTab(index: Int){
        selectTab(getTabAt(index))
    }

    override fun newTab(): Tab {
        val newTab = super.newTab()
        newTab.view.minimumWidth = 0
        newTab.customView = DotCheckboxView(context)
        return newTab
    }

    private fun setIsChecked(tab: Tab?, isChecked: Boolean) {
        if (tab != null && tab.customView != null) {
            (tab.customView as DotCheckboxView).isChecked = isChecked
        }
    }
}