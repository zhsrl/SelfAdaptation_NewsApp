package com.e.selfadaptation.fragment

import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.e.selfadaptation.IOnBackPressed
import com.e.selfadaptation.ImageHelper
import com.e.selfadaptation.R


class NewsDetailFragment : Fragment() {

    companion object{
        val IMAGE = "image"
        val TITLE = "title"
        val DATE = "date"
        val DESCRIPTION = "description"
    }

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var date: TextView
    private lateinit var texts: TextView

    var newsImage: String? = null
    var newsTitle: String? = null
    var newsDate: String? = null
    var newsText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        newsImage = arguments!!.getString(IMAGE)
        newsTitle = arguments!!.getString(TITLE)
        newsDate = arguments!!.getString(DATE)
        newsText = arguments!!.getString(DESCRIPTION)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        image = view!!.findViewById(R.id.IV_fragment_detail_news_image)
        title = view!!.findViewById(R.id.TV_fragment_detail_news_title)
        date = view!!.findViewById(R.id.TV_fragment_detail_news_date)
        texts = view!!.findViewById(R.id.TV_fragment_detail_news_text)
//
//        val decToBitmap = ImageHelper.stringToBitmap(newsImage!!)
//
//        image.setImageBitmap(decToBitmap)
        title.text = newsTitle
        date.text = newsDate
        texts.text = newsText
    }

}