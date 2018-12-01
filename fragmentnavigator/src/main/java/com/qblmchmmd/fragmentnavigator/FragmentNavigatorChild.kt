package com.qblmchmmd.fragmentnavigator

import android.content.Context
import androidx.fragment.app.Fragment

abstract class FragmentNavigatorChild protected constructor() : Fragment() {

    private var onAttach: ((context: Context?) -> Unit)? = null

    lateinit var fragmentNavigator: FragmentNavigator
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttach?.invoke(context)
    }

    class Builder<T : FragmentNavigatorChild>(entityClass: Class<T>) {
        private val entity = entityClass.newInstance()

        fun setOnAttach(listener: ((context: Context?) -> Unit)?): Builder<T> {
            entity.onAttach = listener
            return this
        }

        fun build(
                fragmentNavigator: FragmentNavigator
        ): T {
            entity.fragmentNavigator = fragmentNavigator
            return entity
        }
    }
}