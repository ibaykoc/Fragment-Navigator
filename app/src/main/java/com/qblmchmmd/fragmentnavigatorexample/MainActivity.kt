package com.qblmchmmd.fragmentnavigatorexample

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.qblmchmmd.fragmentnavigator.FragmentNavigator

class MainActivity : AppCompatActivity() {

    private val mainContentFragment = FragmentNavigator
            .Builder(FirstContentFragment::class.java)
            .setOnStackChange { invalidateOptionsMenu() }
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent(mainContentFragment)
    }

    override fun onBackPressed() {
        mainContentFragment.handleBackPressedElse { super.onBackPressed() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mainContentFragment.onCreateOptionsMenu(menu, menuInflater)
        return true
    }

    private fun setContent(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContentPlaceholder, fragment)
                .commit()
    }
}
