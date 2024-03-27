package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.model.DesertUiState
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DesertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DesertUiState())
    val uiState: StateFlow<DesertUiState> = _uiState.asStateFlow()
    private

    fun resetGame() {
        _uiState.value = DesertUiState()  //create a new instance
    }

    fun buyDesert() {
        val updatedDessertsSold = _uiState.value.dessertsSold.plus(1)
        val updatedRevenue = _uiState.value.revenue.plus(_uiState.value.currentDessertPrice)
        _uiState.update { currentState ->
            currentState.copy(
                dessertsSold = updatedDessertsSold,
                revenue = updatedRevenue,
            )
        }
    }

    private fun nextDessert(){
        val updatedDessertIndex = _uiState.value.currentDessertIndex.plus(1)
        if( updatedDessertIndex > _uiState.value.numDesserts ){
            System.err
        }
        _uiState.update { currentState ->
            currentState.copy(
                currentDessertIndex = updatedDessertIndex,
                startProductionAmount = currentState.desserts[currentState.currentDessertIndex+1].startProductionAmount,
                currentDessertPrice = currentState.desserts[currentState.currentDessertIndex+1].price,
                dessertImageId = currentState.desserts[currentState.currentDessertIndex+1].imageId,
            )
        }

    }

    fun determineDessertToShow() {
        if (_uiState.value.dessertsSold >= _uiState.value.startProductionAmount) {
            nextDessert()
        }
    }

}