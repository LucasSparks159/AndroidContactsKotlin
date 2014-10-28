package com.example.egorb.contactsviewsample

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.Toast


public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridview = findViewById(R.id.gridView)
        if (gridview is GridView)
        {
            var adapter = ContactsGridAdapter(this)
            adapter.FillContacts()
            //smart-cast - nice, hope to see it in C#
            gridview.setAdapter(adapter)
        }
    }
}
