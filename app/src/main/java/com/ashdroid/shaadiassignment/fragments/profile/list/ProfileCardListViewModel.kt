package com.ashdroid.shaadiassignment.fragments.profile.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ashdroid.shaadiassignment.models.MatchProfile
import com.ashdroid.shaadiassignment.models.toEntity
import com.ashdroid.shaadiassignment.repositories.ApiRepository
import com.ashdroid.shaadiassignment.repositories.DbRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProfileCardListViewModel(application: Application) : AndroidViewModel(application) {
    private val apiRepository by lazy {
        ApiRepository(getApplication())
    }
    private val dbRepository by lazy {
        DbRepository(getApplication())
    }

    fun getAllProfiles() = dbRepository.getAll().map { it }

    fun fetchProfiles() {
        viewModelScope.launch {
            try {
                insertData(apiRepository.getProfiles("1").results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun insertData(list: List<MatchProfile>?) {
        list?.map { it.toEntity() }?.let { dbRepository.insertAll(it) }
    }

    fun acceptProfile(uid: String) {
        viewModelScope.launch { dbRepository.acceptProfile(uid) }
    }

    fun declineProfile(uid: String) {
        viewModelScope.launch { dbRepository.declineProfile(uid) }
    }
}