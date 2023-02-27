package com.honey.examplejetpackcoompose.secondexample.productdetails

import android.net.wifi.aware.Characteristics
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Thread.State

data class CharacteristicsModel(
    val title: String,
    val value: String
)

class ProductViewModel : ViewModel() {

    private val _sku = MutableStateFlow<String>("Артикул: 9781230912")
    val sku: StateFlow<String> = _sku.asStateFlow()

    private val _title = MutableStateFlow<String>("Дрель аккумуляторная DrillPill, 10В, Li-ion, 2 Ач")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _itemsInCart = MutableStateFlow<Int>(0)
    val itemsInCart : StateFlow<Int> = _itemsInCart.asStateFlow()

    private val _availableCount = MutableStateFlow<Int>(9999)
    val availableCount : StateFlow<Int> = _availableCount.asStateFlow()

    private val _pickupStoresCount = MutableStateFlow<Int>(10)
    val pickupStoresCount : StateFlow<Int> = _pickupStoresCount

    private val _characteristics = MutableStateFlow<List<CharacteristicsModel>>(
        listOf(
            CharacteristicsModel(title = "Толщина, мм: ", "12.5"),
            CharacteristicsModel(title = "Вес, кг: ", "5.3"),
            CharacteristicsModel(title = "Марка: ", "LAMBORGINI"),
            CharacteristicsModel(title = "Страна: ", "Россия")
        )
    )
    val characteristics : StateFlow<List<CharacteristicsModel>> = _characteristics

    fun addToCart(){
        _itemsInCart.tryEmit(1)
    }
}