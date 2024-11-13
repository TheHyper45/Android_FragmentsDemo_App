package com.example.fragmentsdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Locale

data class ItemElement(val name: String,val count: Int)

class ItemElementAdapter(
    private val context: Context,
    private val list: MutableList<ItemElement>
) : BaseAdapter() {
    override fun getCount(): Int = list.size
    override fun getItem(index: Int): Any = list[index]
    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(index: Int,convertView: View?,viewGroup: ViewGroup?): View {
        val newConvertView = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_list_element,viewGroup,false
        )

        val deleteButton = newConvertView.findViewById<Button>(R.id.itemDeleteButton)
        deleteButton.setOnClickListener{
            list.removeAt(index)
            notifyDataSetChanged()
        }

        val item = getItem(index) as ItemElement
        val itemNameText = newConvertView.findViewById<TextView>(R.id.itemText)
        val itemCountText = newConvertView.findViewById<TextView>(R.id.itemCountText)

        itemNameText.text = item.name
        itemCountText.text = String.format(Locale.ROOT,"%d",item.count)
        return newConvertView
    }
}

class ItemListFragment : Fragment(R.layout.item_list_fragment) {
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val items = mutableListOf<ItemElement>()

        arguments?.let { bundle ->
            // Iterate over all keys in the bundle
            for (key in bundle.keySet()) {
                // Retrieve each item from the bundle
                val number = bundle.getInt(key, 0) // Default value is 0
                items.add(ItemElement(key.toString(), number)) // Add the pair (name, number) to the list
            }
        }
/*
        // cant use, because it will be added every time a new item is being added
        val items = mutableListOf(
            ItemElement("WhiteBoard",1),
            ItemElement("Markers",3),
            ItemElement("Laptop",10),
            ItemElement("Chairs",10),
            ItemElement("Desks",10)
        )
 */
        val adapter = context?.let { ItemElementAdapter(it,items) }
        view.findViewById<ListView>(R.id.itemList).adapter = adapter
        val addNewButton =  view.findViewById<Button>(R.id.addNewItem)

        addNewButton.setOnClickListener {
           addNewButton.isEnabled = false
            val addNewWindowFragment = AddNewWindowFragment()
            val bundle = Bundle()
            items.forEach { item ->
                bundle.putInt(item.name, item.count)
            }
            addNewWindowFragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.mainFragmentView, addNewWindowFragment, "findThisFragment")
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}