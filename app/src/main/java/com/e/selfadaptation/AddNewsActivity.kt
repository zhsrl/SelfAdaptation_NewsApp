package com.e.selfadaptation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class AddNewsActivity : AppCompatActivity(), CoroutineScope {

    companion object{
        val PICK_IMAGE = 1
    }

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

        newsImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore
                    .Images
                    .Media
                    .EXTERNAL_CONTENT_URI)
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE)
        }

    }

    fun init(){
        newsImage = findViewById(R.id.IV_news_image)
        newsTitle = findViewById(R.id.TV_news_title)
        newsText = findViewById(R.id.TV_news_text)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode == PICK_IMAGE){
                val selectedImage: Uri? = data!!.data

                if(selectedImage != null){
                    val imgBitmap: Bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(selectedImage))

                    val decToStringBitmap: String? = ImageHelper.bitmapToString(imgBitmap)

                    newsImage.setImageBitmap(imgBitmap)
                }
            }
        }

    }
}