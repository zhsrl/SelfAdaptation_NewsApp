package com.e.selfadaptation

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.selfadaptation.fragment.NewsDetailFragment
import com.e.selfadaptation.fragment.NewsListFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(){

    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle("Janalyqtar")

        if(findViewById<View>(R.id.layout_default) != null){
            supportFragmentManager
                    .beginTransaction()
                    .hide(NewsDetailFragment())
                    .show(NewsListFragment())
                    .commit()


        }

        if(findViewById<View>(R.id.layout_land) != null){
            supportFragmentManager
                    .beginTransaction()
                    .show(NewsListFragment())
                    .show(NewsDetailFragment())
                    .commit()

        }







    }


}