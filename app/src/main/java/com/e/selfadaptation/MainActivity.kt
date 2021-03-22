package com.e.selfadaptation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerView: RecyclerView

    private lateinit var addNewsButton: FloatingActionButton

    var newsList: List<News> = ArrayList()

    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle(R.string.main)


        newsList = newsGen()

        recyclerView = findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter
        newsAdapter!!.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // Add news
        addNewsButton = findViewById(R.id.FAB_add_news)
        addNewsButton.setOnClickListener {
            val intent = Intent(applicationContext, AddNewsActivity::class.java)
            startActivity(intent)
        }
    }

    fun newsGen(): List<News>{
        val news: MutableList<News> = ArrayList()

        val n1 = News(1, "Наурыз күні ескеретін алты міндет ", "A", "22-03-2021")
        val n2 = News(2, "Шотландская компания призналась в участии в коррупционных схемах в Казахстане", "A", "21-03-2021")
        val n3 = News(3, "Хайп прошел? Что происходит с Clubhouse", "A", "15-03-2021")

        news.add(n1)
        news.add(n2)
        news.add(n3)

        return news
    }
}