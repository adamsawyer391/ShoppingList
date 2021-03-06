package com.cosmic.shoppinglist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
): ViewModel(){
    fun insert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }
    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }
    fun update(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(item)
    }
    fun getAllShoppingItems() = repository.getAllShoppingItems()
}