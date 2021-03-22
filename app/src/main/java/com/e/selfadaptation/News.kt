package com.e.selfadaptation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var newsId: Int = 0,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var newsImage: ByteArray? = null,

    @ColumnInfo(name = "title")
    var newsTitle: String,

    @ColumnInfo(name = "text")
    var newsText: String,

    @ColumnInfo(name = "date")
    var newsDate: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as News

        if (newsId != other.newsId) return false
        if (newsImage != null) {
            if (other.newsImage == null) return false
            if (!newsImage.contentEquals(other.newsImage)) return false
        } else if (other.newsImage != null) return false
        if (newsTitle != other.newsTitle) return false
        if (newsText != other.newsText) return false
        if (newsDate != other.newsDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = newsId
        result = 31 * result + (newsImage?.contentHashCode() ?: 0)
        result = 31 * result + newsTitle.hashCode()
        result = 31 * result + newsText.hashCode()
        result = 31 * result + newsDate.hashCode()
        return result
    }
}