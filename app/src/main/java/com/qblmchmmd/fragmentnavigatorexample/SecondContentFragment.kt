package com.qblmchmmd.fragmentnavigatorexample


import android.os.Bundle
import android.util.Log
import android.view.*

class SecondContentFragment : LoggedNavigatorChild() {

    override val lTag: String = this::class.java.simpleName

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(lTag, "onCreateView, inflate: $inflater, container: $container, savedInstanceState: $savedInstanceState")
        return inflater.inflate(R.layout.fragment_second_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.title = "SECOND"
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.second_content_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return item?.let {
            when (item.itemId) {
                R.id.secondContentToolbarMenuShare -> {
                    Log.d(lTag, "Share toolbar menu selected")
                    true
                }
                else -> false
            }
        } ?: false
    }
}
