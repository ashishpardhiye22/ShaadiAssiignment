package com.ashdroid.shaadiassignment.db.daos

import androidx.room.*
import com.ashdroid.shaadiassignment.db.models.MatchProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchProfileDao {
    @Query("SELECT * FROM tbl_match_profile")
    fun getAll(): Flow<List<MatchProfileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MatchProfileEntity>)

    @Query("UPDATE tbl_match_profile SET `action` = :action, `updated_on` = :updatedOn WHERE uid = :uid")
    suspend fun update(uid: String, action: Int, updatedOn: Long = System.currentTimeMillis())
}