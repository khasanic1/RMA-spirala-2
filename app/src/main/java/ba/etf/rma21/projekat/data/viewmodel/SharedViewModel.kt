package ba.etf.rma21.projekat.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val oznaka = MutableLiveData<Int>()
    fun setInt(input: Int) {
        oznaka.value = input
    }

    fun getInt(): LiveData<Int> {
        return oznaka
    }
}