package com.example.fragmentsdemo

import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val dataFormFragment = DataFormFragment()
    val itemListFragment = ItemListFragment()

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

        supportFragmentManager.beginTransaction().add(R.id.mainFragmentView,dataFormFragment).commit()
        findViewById<Button>(R.id.switchToDataFormFragmentButton).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mainFragmentView,dataFormFragment).commit()
        }
        findViewById<Button>(R.id.switchToItemListFragmentButton).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mainFragmentView,itemListFragment).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }
}