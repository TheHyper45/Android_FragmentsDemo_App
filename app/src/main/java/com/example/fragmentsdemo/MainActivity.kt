package com.example.fragmentsdemo

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom)
            insets
        }
        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.commit {
            replace<ItemListFragment>(R.id.mainFragmentView)
            setReorderingAllowed(true)
            addToBackStack("Item_List")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.toolbarAddNew -> {
            Log.d("DEBUG","Click")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }*/
}