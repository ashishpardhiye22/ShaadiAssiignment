package com.ashdroid.shaadiassignment.db.models

import androidx.room.*
import com.ashdroid.shaadiassignment.models.MatchProfile

@Entity(tableName = "tbl_match_profile")
data class MatchProfileEntity(
    @PrimaryKey val uid: String,

    @ColumnInfo(name = "profile")
    val matchProfile: MatchProfile,

    @ColumnInfo(name = "action")
    val action: Int? = null,

    @ColumnInfo(name = "created_on")
    val createdOn: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_on")
    val updatedOn: Long? = System.currentTimeMillis()
) {

    fun actionPerformed(): Boolean {
        return action == actionAccept || action == actionDecline
    }
}

const val actionAccept: Int = 0
const val actionDecline: Int = 1