package com.cosmic.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.itemView.tvAmount.text = "${currentShoppingItem.amount}"
        holder.itemView.tvName.text = currentShoppingItem.name
        holder.itemView.ivDelete.setOnClickListener{
            viewModel.delete(currentShoppingItem)
        }
        holder.itemView.ivAdd.setOnClickListener{
            currentShoppingItem.amount++
            viewModel.update(currentShoppingItem)
        }
        holder.itemView.ivSubtract.setOnClickListener{
            if (currentShoppingItem.amount > 0){
                currentShoppingItem.amount--
                viewModel.update(currentShoppingItem)
            }else{
                Toast.makeText(holder.itemView.context, "Cannot decrease any further", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class ShoppingItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}