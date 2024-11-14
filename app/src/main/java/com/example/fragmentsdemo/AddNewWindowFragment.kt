package com.example.fragmentsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText

class AddNewWindowFragment : Fragment(R.layout.fragment_add_new_window) {
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val itemNameEditText: EditText = view.findViewById(R.id.itemName)
        val amountEditText: EditText = view.findViewById(R.id.itemAmount)

        view.findViewById<Button>(R.id.cancelAddDataButton).setOnClickListener {
            returnToItemList()
        }
        view.findViewById<Button>(R.id.addDataButton).setOnClickListener {
            if(itemNameEditText.text.isNotEmpty() && amountEditText.text.isNotEmpty()) {
                val itemList = (activity as MainActivity).itemListFragment
                itemList.requireView().findViewById<Button>(R.id.addNewItem).isEnabled = true
                itemList.adapter.addItem(ItemListFragment.ItemElement(
                    itemNameEditText.text.toString(),
                    Integer.parseInt(amountEditText.text.toString())
                ))
                returnToItemList()
                itemNameEditText.text.clear()
                amountEditText.text.clear()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun returnToItemList() {
        val mainActivity = activity as MainActivity
        mainActivity.findViewById<Button>(R.id.switchToItemListFragmentButton).isEnabled = true
        mainActivity.findViewById<Button>(R.id.switchToDataFormFragmentButton).isEnabled = true
        val itemList = mainActivity.itemListFragment
        itemList.requireView().findViewById<Button>(R.id.addNewItem).isEnabled = true
        requireView().findViewById<EditText>(R.id.itemName).text.clear()
        requireView().findViewById<EditText>(R.id.itemAmount).text.clear()
        requireActivity().supportFragmentManager.popBackStack()
    }
}