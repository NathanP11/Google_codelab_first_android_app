package com.example.dessertclicker.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.dessertclicker.data.Datasource


data class DesertUiState(
    var desserts: List<Dessert> = Datasource.dessertList,
    var revenue : Int = 0,
    var dessertsSold : Int = 0,
    val currentDessertIndex : Int = 0,
    val currentDessertPrice : Int = desserts[currentDessertIndex].price,
    @DrawableRes val dessertImageId : Int = desserts[currentDessertIndex].imageId,
    val startProductionAmount : Int = desserts[currentDessertIndex].startProductionAmount,

    val numDesserts: Int = Datasource.dessertList.size,
    )
