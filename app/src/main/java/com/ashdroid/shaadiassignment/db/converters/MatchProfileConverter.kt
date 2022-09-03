package com.ashdroid.shaadiassignment.db.converters

import androidx.room.TypeConverter
import com.ashdroid.shaadiassignment.models.MatchProfile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MatchProfileConverter {
    @TypeConverter
    fun toMatchProfile(matchProfile: String): MatchProfile {
        val type = object : TypeToken<MatchProfile>() {}.type
        return Gson().fromJson(matchProfile, type)
    }

    @TypeConverter
    fun toUserInfoJson(matchProfile: MatchProfile): String {
        val type = object : TypeToken<MatchProfile>() {}.type
        return Gson().toJson(matchProfile, type)
    }
}