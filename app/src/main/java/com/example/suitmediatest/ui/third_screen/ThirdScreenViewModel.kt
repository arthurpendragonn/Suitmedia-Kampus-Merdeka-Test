package com.example.suitmediatest.ui.third_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatest.data.UserRepository
import com.example.suitmediatest.data.UserResponseItem

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    val users: LiveData<PagingData<UserResponseItem>> =
        repository.getUser().cachedIn(viewModelScope)

}