package com.e.selfadaptation.fragment

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
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

    lateinit var recyclerView: RecyclerView
    var newsAdapter: NewsAdapter? = null

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

        newsDatabase = DatabaseProvider.getNewsDatabase(context!!.applicationContext)
        newsDao = newsDatabase.newsDao()

        // Background Thread for get data from RoomDB
        launch{
            newsList = newsDao.getAllNews()
            recyclerViewInit(newsList)

            newsAdapter!!.itemClick = { view ->

                val position = recyclerView.getChildAdapterPosition(view)
                val news = newsList[position]

                if (activity!!
                                .resources
                                .configuration
                                .orientation == Configuration.ORIENTATION_PORTRAIT) {
                    val intent = Intent(context!!.applicationContext, NewsDetailActivity::class.java)



                    intent.putExtra(NewsDetailActivity.TITLE, news.newsTitle)
                    intent.putExtra(NewsDetailActivity.IMAGE, news.newsImage)
                    intent.putExtra(NewsDetailActivity.DESCRIPTION, news.newsText)
                    intent.putExtra(NewsDetailActivity.DATE, news.newsDate)
                    startActivity(intent)
                }

                if (activity!!
                                .resources
                                .configuration
                                .orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Toast.makeText(context, "L", Toast.LENGTH_SHORT).show()

                    val bundle = Bundle()

                    bundle.putString(NewsDetailFragment.IMAGE, news.newsImage)
                    bundle.putString(NewsDetailFragment.TITLE, news.newsTitle)
                    bundle.putString(NewsDetailFragment.DATE, news.newsDate)
                    bundle.putString(NewsDetailFragment.DESCRIPTION, news.newsText)

                    val fragmentObj = NewsDetailFragment()

                    fragmentObj.arguments = bundle

                }

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