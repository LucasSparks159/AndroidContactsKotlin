package com.example.egorb.contactsviewsample

import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.provider.Contacts
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList
import java.util.Random

public class ContactsGridAdapter(private val context: Context) : BaseAdapter() {

    private var contacts: ArrayList<Contact> = arrayListOf()

    override fun getCount(): Int {
        return contacts.size()
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return (contacts.get(position) as Contact).id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row: View
        val contact = (contacts.get(position) as Contact)

        if (convertView == null) {
            val inflater = (context as Activity).getLayoutInflater()
            row = inflater.inflate(R.layout.contactsview_tile, parent, false)
        } else {
            row = convertView!!
        }

        val photoIv = (row.findViewById(R.id.contactTile_photo) as ImageView)
        val gradientIv = (row.findViewById(R.id.contactTile_gradient) as ImageView)
        val fullNameTv = (row.findViewById(R.id.contactTile_fullName) as TextView)
        val numberTv = (row.findViewById(R.id.contactTile_number) as TextView)
        val shortNameTv = (row.findViewById(R.id.contactTile_shortName) as TextView)

        val hasAvatar = contact.avatarId != null && contact.avatarId != ""

        if (hasAvatar) {
            photoIv.setVisibility(View.VISIBLE)
            val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact.id)
            val contactPhotoUri = Uri.withAppendedPath(contactUri, Contacts.Photos.CONTENT_DIRECTORY)
            photoIv.setImageURI(contactPhotoUri)
        } else {
            photoIv.setVisibility(View.GONE)
            val idx = Random().nextInt(COLORS.size)
            val randomColor = (COLORS[idx])
            row.setBackgroundColor(Color.parseColor(randomColor))
        }

        fullNameTv.setText(contact.name)
        numberTv.setText("Mobile")
        gradientIv.setVisibility(if (hasAvatar) View.VISIBLE else View.GONE)
        shortNameTv.setText(contact.name?.substring(0, 1))
        shortNameTv.setVisibility(if (!hasAvatar) View.VISIBLE else View.GONE)

        return row
    }

    public fun FillContacts() {
        contacts = ArrayList<Contact>()
        //val projection = array<String>(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_ID)
        val projection = array<String>("_id", "display_name", "photo_id")

        val cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(projection[0]))
                val name = cursor.getString(cursor.getColumnIndex(projection[1]))
                val avatarId = cursor.getString(cursor.getColumnIndex(projection[2]))
                val contact = Contact(id, name, avatarId)
                contacts.add(contact)
            } while (cursor.moveToNext())
        }

    }

    class object {
        public val COLORS: Array<String> = array<String>("#4B879C", "#A17099", "#688F68", "#A68458", "#507CA3")
    }
}