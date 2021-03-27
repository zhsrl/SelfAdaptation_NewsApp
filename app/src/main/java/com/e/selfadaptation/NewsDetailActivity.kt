package com.e.selfadaptation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class NewsDetailActivity : AppCompatActivity() {

    companion object{
        val TITLE = "title"
        val DESCRIPTION = "desc"
        val IMAGE = "image"
        val DATE = "date"
    }

    private lateinit var newsImage: ImageView
    private lateinit var newsTitle: TextView
    private lateinit var newsDate: TextView
    private lateinit var newsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        init()

        val mImage: String? = intent.getStringExtra(IMAGE)
        val mTitle = intent.getStringExtra(TITLE)
        val mDate = intent.getStringExtra(DATE)
        val mText = intent.getStringExtra(DESCRIPTION)

        val decToBitmap = mImage?.let { ImageHelper.stringToBitmap(it) }
        newsImage.setImageBitmap(decToBitmap)

        newsTitle.text = mTitle
        newsDate.text = mDate
        newsText.text = mText


    }

    fun init(){
        newsImage = findViewById(R.id.IV_detail_news_image)
        newsTitle = findViewById(R.id.TV_detail_news_title)
        newsDate = findViewById(R.id.TV_detail_news_date)
        newsText = findViewById(R.id.TV_detail_news_text)
    }
}