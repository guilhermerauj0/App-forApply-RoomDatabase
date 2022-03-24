package com.example.mysubscribers.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysubscribers.R
import com.example.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SubscriberViewModel(
    private val repository:SubscriberRepository // TEM QUE SER INTERFACE
) : ViewModel() {

    // NOTIFICAR A VIEW DE QUE ALGO ACONTECEU
    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberstateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addSubscriber(name:String, email:String) = viewModelScope.launch{
        try {
            val id = repository.insertSubscriber(name, email)
            if(id > 0){ // SE INSERIDO COM SUCESSO
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_inserted_successfully

            }
        }catch (ex: Exception){
            _messageEventData.value = R.string.subscriber_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }

    sealed class SubscriberState{
        object Inserted:SubscriberState()
    }

    companion object{
        private val TAG = SubscriberViewModel::class.java.simpleName
    }

}