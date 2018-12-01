package com.qblmchmmd.fragmentnavigatorexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.qblmchmmd.fragmentnavigator.FragmentNavigatorChild

abstract class LoggedNavigatorChild : FragmentNavigatorChild() {
    protected abstract val lTag: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(lTag, "onAttached, context: $context")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(lTag, "onCreate, savedInstanceState: $savedInstanceState")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(lTag, "onViewCreated, view: $view, savedInstanceState: $savedInstanceState")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(lTag, "onActivityCreated, savedInstanceState: $savedInstanceState")
    }

    override fun onResume() {
        super.onResume()
        Log.d(lTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(lTag, "onPaused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(lTag, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(lTag, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(lTag, "onDestroy")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        Log.d(lTag, "onCreateOptionsMenu, menu: $menu, inflater: $inflater")
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        Log.d(lTag, "onPrepareOptionsMenu, menu: $menu")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(lTag, "onOptionItemSelected, item: $item")
        return super.onOptionsItemSelected(item)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        Log.d(lTag, "onAttachFragment, childFragment:$childFragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(lTag, "onDetach")
    }
}