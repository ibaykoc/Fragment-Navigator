package com.qblmchmmd.fragmentnavigatorexample


import android.os.Bundle
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_first_content.*
import java.util.*

class FirstContentFragment : LoggedNavigatorChild() {

    override val lTag: String = this::class.java.simpleName

    private val createdAt = Calendar.getInstance().time

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(lTag, "onCreateView, inflate: $inflater, container: $container, savedInstanceState: $savedInstanceState")
        return inflater.inflate(R.layout.fragment_first_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstContentText.text = "Main Content, Created At $createdAt"
        firstContentButton.setOnClickListener {
            fragmentNavigator.startFragment(SecondContentFragment::class.java)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.title = "FIRST"
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.first_content_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return item?.let {
            when (item.itemId) {
                R.id.firstContentToolbarMenuSearch -> {
                    Log.d(lTag, "Search toolbar menu selected")
                    true
                }
                else -> false
            }
        } ?: false
    }
}
