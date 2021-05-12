package com.cosmic.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel : ViewModelProvider = ViewModelProvider(this, factory)
        val vm = viewModel.get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), vm)
//        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
//        ContextCompat.getDrawable(this, R.drawable.divider)?.let {
//            dividerItemDecoration.setDrawable(
//                it
//            )
//        }
        recycler_view.addItemDecoration(LinearSpacingDecoration(itemSpacing = 10, edgeSpacing = 20))
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        vm.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener{
            AddShoppingItemDialog(this,
                object : AddDialogListener{
                    override fun onAddButtonClick(item: ShoppingItem) {
                        vm.insert(item)
                    }
                }
            ).show()
        }
    }
}