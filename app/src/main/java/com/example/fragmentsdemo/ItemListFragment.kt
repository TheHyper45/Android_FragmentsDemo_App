package com.example.fragmentsdemo

import android.content.Context
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

        val item = getItem(index) as ItemElement
        val itemNameText = newConvertView.findViewById<TextView>(R.id.itemText)
        val itemCountText = newConvertView.findViewById<TextView>(R.id.itemCountText)

        itemNameText.text = item.name
        itemCountText.text = String.format(Locale.ROOT,"%d",item.count)
        return newConvertView
    }

    fun addItem(item: ItemElement) {
        list.add(item)
        notifyDataSetChanged()
    }
}

class ItemListFragment : Fragment(R.layout.item_list_fragment) {
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val items = mutableListOf(
            ItemElement("WhiteBoard",1),
            ItemElement("Markers",3),
            ItemElement("Laptop",10),
            ItemElement("Chairs",10),
            ItemElement("Desks",10)
        )
        val adapter = context?.let { ItemElementAdapter(it,items) }
        view.findViewById<ListView>(R.id.itemList).adapter = adapter

        view.findViewById<Button>(R.id.addNewItem).setOnClickListener {
            //adapter?.addItem(ItemElement("x",1))
            val nextFrag = AddNewWindowFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.mainFragmentView, nextFrag, "findThisFragment")
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}