package com.example.my_components.widgets.tabs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.my_components.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.widget_tabs.view.*

class ExampleTabs @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : CoordinatorLayout(context, attrs) {


    init {
        setupView()
    }

    var onPageChanged: OnPageChanged? = null

    private fun setupView() {
        inflate(context, R.layout.widget_tabs, this)

        attrs?.run {
            context.obtainStyledAttributes(this, R.styleable.ExampleTabs).run {
                unselectTextColor = getColor(
                    R.styleable.ExampleTabs_unselectTextColor,
                    ContextCompat.getColor(context, R.color.black)
                )
                recycle()
            }
        }

        findViewById<TabLayout>(R.id.tabLayout).addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.currentItem = tab.position
                onPageChanged?.invoke(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // do nothing
            }

        })
    }

    fun setDynamicFragmentToTabLayout(tabsList: List<TabModel>, fragment: Fragment) {
        tabsList.forEach {
            findViewById<TabLayout>(R.id.tabLayout).addTab(
                findViewById<TabLayout>(R.id.tabLayout).newTab()
                    .setText(it.text)
                    .setIcon(it.icon)
            )
        }
        val adapter = DynamicFragmentAdapter(fragment, tabsList.map { it.fragment })
        viewpager.adapter = adapter
        viewpager.currentItem = 0
    }

    @ColorRes
    var unselectTextColor: Int = 0
        set(value) {
            field = value
            findViewById<TabLayout>(R.id.tabLayout).setTabTextColors(value, ContextCompat.getColor(context,R.color.yellow))
        }

    data class TabModel(
        val text: String,
        val fragment: Fragment,
        val icon: Drawable? = null
    )
}

typealias OnPageChanged = ((Int) -> Unit)