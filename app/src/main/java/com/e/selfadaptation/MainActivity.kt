package com.e.selfadaptation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerView: RecyclerView

    private lateinit var addNewsButton: FloatingActionButton

    private var newsList: List<News> = ArrayList()

    private lateinit var newsDatabase: NewsDatabase
    private lateinit var newsDao: NewsDao

    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle(R.string.main)

        newsDatabase = DatabaseProvider.getNewsDatabase(applicationContext)
        newsDao = newsDatabase.newsDao()

//        launch {
//            newsList = newsDao.getAllNews()
//            recyclerViewInit(newsList)
//        }

        // Add news
        addNewsButton = findViewById(R.id.FAB_add_news)
        addNewsButton.setOnClickListener {
            val intent = Intent(applicationContext, AddNewsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun recyclerViewInit(list: List<News>){
        recyclerView = findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter
        newsAdapter!!.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL
    }

}