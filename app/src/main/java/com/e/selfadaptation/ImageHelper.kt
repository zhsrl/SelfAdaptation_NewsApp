package com.e.selfadaptation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*

object ImageHelper {

    @TypeConverter
    fun bitmapToString(bitmap: Bitmap): String? {
        val baos: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()

        val temp: String? = Base64.encodeToString(b, Base64.DEFAULT)

        if(temp == null){
            return null
        }else{
            return temp
        }

    }

    @TypeConverter
    fun stringToBitmap(encodedString: String): Bitmap? {
        try {
            val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)

            val bitmap: Bitmap? = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)

            if(bitmap == null){
                return null
            }else{
                return bitmap
            }
        }catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }
}