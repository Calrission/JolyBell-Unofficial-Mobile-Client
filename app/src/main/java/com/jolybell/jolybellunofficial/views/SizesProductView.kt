package com.jolybell.jolybellunofficial.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.StyleRes
import com.google.android.material.tabs.TabLayout
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.models.ModelSize
import com.jolybell.jolybellunofficial.models.Pivot

class SizesProductView: TabLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attr: AttributeSet?): this(context, attr, R.style.SizeProductViewLight)
    constructor(context: Context, attr: AttributeSet?, defStyle: Int): super(context, attr, defStyle){

        context.obtainStyledAttributes(attr, R.styleable.SizesProductView, defStyle, defStyle).apply {
            sizeStyle = getResourceId(R.styleable.SizesProductView_theme_sizes, R.style.SizeProductViewLight)
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

        visibility = View.GONE

        if (isInEditMode){
            addSizes(listOf(
                ModelSize("-1", "S", Pivot(0, 0.0)),
                ModelSize("-1", "S", Pivot(0, 0.0)),
                ModelSize("-1", "S", Pivot(0, 0.0)),
                ModelSize("-1", "S", Pivot(0, 0.0)),
                ModelSize("-1", "S", Pivot(0, 0.0)),
                ModelSize("-1", "S", Pivot(0, 0.0)),
                ModelSize("-1", "S", Pivot(0, 0.0)),
            ))
        }
    }

    private val sizes = mutableListOf<ModelSize>()

    fun addSize(size: ModelSize){
        if (visibility == View.GONE)
            visibility = View.VISIBLE
        sizes.add(size)
        addTab(newTab(size))
    }

    fun addSizes(sizes: Collection<ModelSize>){
        sizes.forEach{
            addSize(it)
        }
    }

    @StyleRes
    var sizeStyle: Int = 0

    init {
        tabMode = MODE_SCROLLABLE
        tabGravity = GRAVITY_FILL
        setSelectedTabIndicator(null)
    }

    private fun setIsChecked(tab: Tab?, isCheck: Boolean){
        (tab?.customView as SizeCheckboxView).isChecked = isCheck
    }

    override fun newTab(): Tab {
        val newTab = super.newTab()
        newTab.view.minimumWidth = 0
        newTab.customView = SizeCheckboxView(context, null, sizeStyle)
        return newTab
    }

    private fun newTab(modelSize: ModelSize): Tab{
        return newTab().apply {
            (customView as SizeCheckboxView).apply {
                text = modelSize.name
            }
        }
    }

    fun addOnSizeSelect(onSizeSelected: OnSizeSelected){
        addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                onSizeSelected.onSelect(getModelSizeTab(tab!!))
            }

            override fun onTabUnselected(tab: Tab?) {
                onSizeSelected.onUnselect(getModelSizeTab(tab!!))
            }

            override fun onTabReselected(tab: Tab?) {
                onSizeSelected.onReselect(getModelSizeTab(tab!!))
            }
        })
    }

    fun getModelSizeTab(tab: Tab): ModelSize{
        return sizes[tab.position]
    }

    interface OnSizeSelected{
        fun onSelect(model: ModelSize)
        fun onUnselect(model: ModelSize)
        fun onReselect(model: ModelSize)
    }
}