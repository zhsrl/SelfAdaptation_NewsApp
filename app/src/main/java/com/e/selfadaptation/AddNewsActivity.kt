package com.e.selfadaptation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class AddNewsActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var toolbar: MaterialToolbar

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var newsTitle: TextInputEditText
    private lateinit var newsText: TextInputEditText
    private lateinit var newsImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        job = Job()

        toolbar = findViewById(R.id.toolbar_by_add_item_activity)
        setSupportActionBar(toolbar)
        setTitle(R.string.add_news)

        init()


    }

    fun init(){
        newsImage = findViewById(R.id.IV_news_image)
        newsTitle = findViewById(R.id.TV_model_news_title)
        newsText = findViewById(R.id.TV_news_text)
    }
}