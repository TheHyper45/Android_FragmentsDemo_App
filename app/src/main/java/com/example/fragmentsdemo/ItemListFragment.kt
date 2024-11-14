package com.example.fragmentsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Locale

class ItemListFragment : Fragment(R.layout.item_list_fragment) {
    data class ItemElement(
        val name: String,
        val count: Int
    )

    inner class ItemElementAdapter : BaseAdapter() {
        override fun getCount(): Int = items.size
        override fun getItem(index: Int): Any = items[index]
        override fun getItemId(index: Int): Long = index.toLong()

        override fun getView(index: Int,convertView: View?,viewGroup: ViewGroup?): View {
            val newConvertView = convertView ?: LayoutInflater.from(context).inflate(
                R.layout.item_list_element,viewGroup,false
            )
            newConvertView.findViewById<Button>(R.id.itemDeleteButton).setOnClickListener {
                items.removeAt(index)
                notifyDataSetChanged()
            }
            val item = getItem(index) as ItemElement
            newConvertView.findViewById<TextView>(R.id.itemText).text = item.name
            newConvertView.findViewById<TextView>(R.id.itemCountText).text = String.format(Locale.ROOT,"%d",item.count)
            return newConvertView
        }

        fun addItem(item: ItemElement) {
            items.add(item)
            notifyDataSetChanged()
        }
    }

    private val addNewWindowFragment = AddNewWindowFragment()
    private val items = mutableListOf<ItemElement>()
    val adapter = ItemElementAdapter()

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        view.findViewById<ListView>(R.id.itemList).adapter = adapter

        val addNewButton = view.findViewById<Button>(R.id.addNewItem)

        addNewButton.setOnClickListener {
            addNewButton.isEnabled = false
            val mainActivity = activity as MainActivity
            mainActivity.findViewById<Button>(R.id.switchToItemListFragmentButton).isEnabled = false
            mainActivity.findViewById<Button>(R.id.switchToDataFormFragmentButton).isEnabled = false
            requireActivity().supportFragmentManager.beginTransaction().add(R.id.mainFragmentView,addNewWindowFragment).addToBackStack("AddNewItem").commit()
        }
    }
}