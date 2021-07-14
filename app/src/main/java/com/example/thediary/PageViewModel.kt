package com.example.thediary

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PageViewModel(private val repository: PageRepository): ViewModel() {

    val allPages: LiveData<List<Page>> = repository.allPages.asLiveData()

    fun insert(page:Page)= viewModelScope.launch {
        repository.insert(page)
    }
}

class PageViewModelFactory(private  val repository: PageRepository): ViewModelProvider.Factory{
override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return PageViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
}
}
