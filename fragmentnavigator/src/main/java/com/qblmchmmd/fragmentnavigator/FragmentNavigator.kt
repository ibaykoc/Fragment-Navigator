package com.qblmchmmd.fragmentnavigator

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment

class FragmentNavigator : Fragment() {

    private var onStackChangeListener: ((topStackFragment: FragmentNavigatorChild) -> Unit)? = null
    private var onAttachListener: ((FragmentNavigator) -> Unit)? = null
    private val childPlaceholderId = ViewCompat.generateViewId()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(container!!.context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            id = childPlaceholderId
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachListener?.invoke(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        getTopStackFragment().onCreateOptionsMenu(menu, inflater)
    }

    fun getTopStackFragment(): FragmentNavigatorChild {
        return childFragmentManager.fragments.last() as FragmentNavigatorChild
    }

    fun <T : FragmentNavigatorChild> startFragment(
            fragmentClass: Class<T>,
            args: Bundle? = null,
            addToBackStack: Boolean = true
    ) {
        val fragmentChild = FragmentNavigatorChild.Builder(fragmentClass).build(this).apply {
            args?.let { arguments = it }
        }
        val t = childFragmentManager.beginTransaction()
        if (childFragmentManager.fragments.size > 0) {
            val toHideFragment =
                    childFragmentManager.fragments[childFragmentManager.fragments.size - 1] as FragmentNavigatorChild
            t.detach(toHideFragment)
        }
        t.add(childPlaceholderId, fragmentChild)
        if (addToBackStack) t.addToBackStack(null)
        t.commit()
    }

    fun handleBackPressedElse(onBackStackEmpty: () -> Unit) {
        if (!popFragment()) onBackStackEmpty()
    }

    private fun popFragment(): Boolean {
        if (childFragmentManager.backStackEntryCount > 1) {
            childFragmentManager.popBackStack()
            return true
        }
        return false
    }

    fun backStart() {
        if (childFragmentManager.backStackEntryCount > 1) {
            val toShowFragment =
                    childFragmentManager.fragments[childFragmentManager.fragments.size - 2] as FragmentNavigatorChild
            toShowFragment.onResume()
            childFragmentManager.popBackStack(0, 0)
        }
    }

    class Builder<StartFragment : FragmentNavigatorChild>(
            startFragmentClass: Class<StartFragment>,
            startFragmentArgs: Bundle? = null
    ) {
        private val fragmentNavigator = FragmentNavigator().apply {
            onAttachListener = {
                it.childFragmentManager.addOnBackStackChangedListener {
                    it.onStackChangeListener?.invoke(it.childFragmentManager.fragments.last() as FragmentNavigatorChild)
                }
                it.startFragment(startFragmentClass, args = startFragmentArgs)
            }
        }

        fun setOnStackChange(listener: ((lastStack: FragmentNavigatorChild) -> Unit)?): Builder<StartFragment> {
            fragmentNavigator.onStackChangeListener = listener
            return this
        }

        fun build(): FragmentNavigator {
            return fragmentNavigator
        }
    }

}
