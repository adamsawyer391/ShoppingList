package com.cosmic.shoppinglist

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun insert(item: ShoppingItem) = db.getShoppingDao().insert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    suspend fun update(item: ShoppingItem) = db.getShoppingDao().update(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}