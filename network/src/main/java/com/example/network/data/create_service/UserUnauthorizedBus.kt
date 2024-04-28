package com.example.network.data.create_service

import androidx.lifecycle.LiveData

object UserUnauthorizedBus {

    private val stateUserUnauthorized = SingleLiveEvent<Boolean>()
    var liveDataStateUserUnauthorized: LiveData<Boolean> = stateUserUnauthorized

    fun getEvents() = liveDataStateUserUnauthorized

    fun setEvent() {
        stateUserUnauthorized.value = true
    }
}
