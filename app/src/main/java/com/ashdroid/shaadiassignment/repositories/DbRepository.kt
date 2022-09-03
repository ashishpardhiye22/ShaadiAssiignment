package com.ashdroid.shaadiassignment.repositories

import com.ashdroid.shaadiassignment.BaseApp
import com.ashdroid.shaadiassignment.db.models.MatchProfileEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DbRepository(app: BaseApp) {
    private val matchProfileDao by lazy { app.appDatabase.matchProfileDao() }
    fun getAll() = matchProfileDao.getAll()
    fun insertAll(list: List<MatchProfileEntity>) {
        GlobalScope.launch {
            matchProfileDao.insertAll(list)
        }
    }

    suspend fun acceptProfile(uid: String) {
        matchProfileDao.update(uid = uid, action = 0)
    }

    suspend fun declineProfile(uid: String) {
        matchProfileDao.update(uid = uid, action = 1)
    }

}