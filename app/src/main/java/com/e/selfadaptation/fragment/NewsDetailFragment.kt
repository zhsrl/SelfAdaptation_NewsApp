package com.e.selfadaptation.fragment

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.selfadaptation.IOnBackPressed
import com.e.selfadaptation.R


class NewsDetailFragment : Fragment(), IOnBackPressed, View.OnKeyListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }



    override fun onBackPressed(): Boolean {

        return false
    }

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        if(p1 == KeyEvent.KEYCODE_BACK){
            activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, NewsListFragment())
                    .addToBackStack(null)
                    .commit()

            return true
        }

        return false
    }


}