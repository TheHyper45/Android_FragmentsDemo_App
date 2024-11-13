package com.example.fragmentsdemo

import android.os.Bundle
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

        val itemNameEditText: EditText = view.findViewById(R.id.itemName)
        val amountEditText: EditText = view.findViewById(R.id.itemAmount)
        val addButton: Button = view.findViewById(R.id.addDataButton)

        addButton.setOnClickListener {
            onAddDataButtonPressed(itemNameEditText, amountEditText)
        }

        return view
    }

    private fun onAddDataButtonPressed(itemNameEditText: EditText, amountEditText: EditText) {
        // Get the text from the EditText fields
        val itemName = itemNameEditText.text.toString()
        val amount = amountEditText.text.toString()

        // Check if the fields are empty
        if (itemName.isNotEmpty() && amount.isNotEmpty()) {
        }
    }

    companion object {

    }
}