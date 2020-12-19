package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class databaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listKeeper.db";
    private static final String TABLE_DATA = "data";//table name
    private static final String KEY_PRICE = "price";//price attribute
    private static final String KEY_ITEM = "item";//item attribute
    private static final String KEY_ID = "id";//  pk
    private static final String KEY_QTY = "quantity";// quantity

    public databaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM + " TEXT,"
                + KEY_QTY + " TEXT," + KEY_PRICE + " TEXT)";
        db.execSQL(CREATE_LIST_TABLE);
    }
    public void onDlt()
    {
        SQLiteDatabase db = databaseHandler.this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_DATA);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // Create tables again
        onCreate(db);
    }

    // code to add the new list
    public boolean addList(list lest) {
        SQLiteDatabase db = databaseHandler.this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM, lest.getItem()); // Item
        values.put(KEY_QTY, lest.getQuantity()); // Quantity
        values.put(KEY_PRICE, lest.getPrice() ); // Price

        // Inserting Row
        long result=  db.insert(TABLE_DATA, null, values);
        if (result==-1)
            return false;
        else
            return true;

    }



    // code to get all contacts in a list view
    public List<list> getAllList() {
        List<list> itemList = new ArrayList<list>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list contact = new list();
                //contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setItem(cursor.getString(1));
                contact.setQuantity(cursor.getString(2));
                contact.setPrice(cursor.getString(3));
                // Adding contact to list
                itemList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return itemList;
    }

    public Cursor getAll()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("SELECT  * FROM " + TABLE_DATA,null);
        return res;
    }

    //Deleting single contact
    public void deleteContact(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_ITEM + " = ?",
                new String[] { st });
        //db.close();
    }

}