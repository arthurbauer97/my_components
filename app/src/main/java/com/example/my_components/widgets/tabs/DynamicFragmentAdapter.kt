package com.example.my_components.widgets.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DynamicFragmentAdapter(
    fragment: Fragment,
    private val listOfFragment: List<Fragment>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = listOfFragment.size

    override fun createFragment(position: Int): Fragment = listOfFragment[position]
}