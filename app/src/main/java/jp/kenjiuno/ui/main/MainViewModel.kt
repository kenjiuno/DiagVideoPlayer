package jp.kenjiuno.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val url: MutableLiveData<String> = MutableLiveData()
    val urlToBePlayed: MutableLiveData<String> = MutableLiveData()

    fun playIt() {
        urlToBePlayed.postValue(url.value)
    }
}