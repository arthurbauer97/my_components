package com.example.my_components.widgets.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.my_components.R
import kotlinx.android.synthetic.main.fragment_tabs.*

class TabsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tab.setDynamicFragmentToTabLayout(
            listOf(
                ExampleTabs.TabModel(
                    text = "Normal",
                    fragment = FirstTabExampleFragment()
                ),
                ExampleTabs.TabModel(
                    text = "Favoritos",
                    fragment = SecondTabExampleFragment(),
                    icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_star)
                ),
            ),
            this
        )
    }
}