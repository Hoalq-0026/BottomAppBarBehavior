package com.quanghoa.apps.bottomappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.navigation_action_share -> Toast.makeText(this, "action sharing is selected", Toast.LENGTH_LONG).show()
//                R.id.navigation_action_shopping -> Toast.makeText(this, "action shopping is selected", Toast.LENGTH_LONG).show()
//            }
//            true
//
//        }
    }
}
