package com.e.selfadaptation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.selfadaptation.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class NewsListFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var recyclerView: RecyclerView
    private var newsAdapter: NewsAdapter? = null

    private lateinit var toolbar: MaterialToolbar

    private lateinit var addNewsButton: FloatingActionButton

    private lateinit var newsDatabase: NewsDatabase
    private lateinit var newsDao: NewsDao

    private var newsList: List<News> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        job = Job()

        toolbar = view!!.findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.main)

        newsDatabase = DatabaseProvider.getNewsDatabase(context!!.applicationContext)
        newsDao = newsDatabase.newsDao()

        launch {
            newsList = newsDao.getAllNews()
            recyclerViewInit(newsList)

            newsAdapter!!.itemClick = { view ->
                val position = recyclerView.getChildAdapterPosition(view)
                Toast.makeText(context!!.applicationContext, position.toString(), Toast.LENGTH_SHORT).show()



            }
        }

        addNewsButton = view!!.findViewById(R.id.FAB_add_news)
        addNewsButton.setOnClickListener {
            val intent = Intent(context!!.applicationContext, AddNewsActivity::class.java)
            startActivity(intent)
        }



    }

    private fun recyclerViewInit(list: List<News>){
        recyclerView = view!!.findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter(list)
        recyclerView.adapter = newsAdapter
        newsAdapter!!.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(context!!.applicationContext)
        recyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}