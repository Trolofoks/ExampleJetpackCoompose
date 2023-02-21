package com.honey.examplejetpackcoompose.example.testviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


//@HiltViewModel
class KittenViewModel : ViewModel() {

    var kitten by mutableStateOf(4)
        private set

    private val _kitten2 = MutableStateFlow<Int>(5)
    val kitten2 : StateFlow<Int> = _kitten2.asStateFlow()

    fun releaseNewKittens(){

        kitten += 10
        _kitten2.value += 10
    }
}