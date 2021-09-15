package com.komozan.newsapp.data.db

import androidx.room.TypeConverter
import com.komozan.newsapp.data.model.response.everything.Source


class Converters {
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name, name)
    }
}