package com.example.appsanri.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Written by Muhammad Fachrizal
 **/
object ActivityHelpers {
    private lateinit var currentFragment : Fragment
    private lateinit var fragmentTransaction: FragmentTransaction
    private var FIRST_COMMIT = true

    fun showFragmentMenu (fragmentManager : FragmentManager,
                          fragment: Fragment, frameId : Int){
        fragmentTransaction = fragmentManager.beginTransaction()
        if (FIRST_COMMIT){
            FIRST_COMMIT = false
            currentFragment = fragment
        } else{
            fragmentTransaction.hide(currentFragment)
            currentFragment = fragment
        }
        if (!fragment.isAdded){
            fragmentTransaction.add(frameId, fragment)
        }
        if (fragment.isHidden){
            fragmentTransaction.show(fragment)
        }
        fragmentTransaction.commit()
    }

}