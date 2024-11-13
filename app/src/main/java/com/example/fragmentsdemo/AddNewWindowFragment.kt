package com.example.fragmentsdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class AddNewWindowFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new_window, container, false)

        val itemList = mutableListOf<Pair<String, Int>>()

        arguments?.let { bundle ->
            // Iterate over all keys in the bundle
            for (key in bundle.keySet()) {
                // Retrieve each item from the bundle
                val number = bundle.getInt(key, 0) // Default value is 0
                itemList.add(Pair(key, number)) // Add the pair (name, number) to the list
            }
        }

        val itemNameEditText: EditText = view.findViewById(R.id.itemName)
        val amountEditText: EditText = view.findViewById(R.id.itemAmount)
        val addButton: Button = view.findViewById(R.id.addDataButton)

        addButton.setOnClickListener {
            onAddDataButtonPressed(itemNameEditText, amountEditText, itemList)
        }

        return view
    }

    private fun onAddDataButtonPressed(
        itemNameEditText: EditText,
        amountEditText: EditText,
        itemList: MutableList<Pair<String, Int>>
    ) {
        // Get the text from the EditText fields
        val itemName = itemNameEditText.text.toString()
        val amount = amountEditText.text.toString()

        // Check if the fields are empty
        if (itemName.isNotEmpty() && amount.isNotEmpty()) {
            val amountInt = Integer.parseInt(amount)
            Log.d("test", "not empty")
            val itemListFragment = ItemListFragment()

            val bundle = Bundle()
            itemList.add(Pair(itemName, amountInt))
            itemList.forEach { item ->
                bundle.putInt(item.first, item.second)
            }

            itemListFragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentView, itemListFragment, "findThisFragment")
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}