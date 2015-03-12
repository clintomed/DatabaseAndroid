package com.clintonmedbery.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clintonmedbery on 3/11/15.
 */
public class ContactsHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "contactsManager";

    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_DIRTY_SECRET = "dirty_secret";


    public ContactsHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createSql =
                "CREATE TABLE " + this.TABLE_CONTACTS
                + "("
                + " _id INTEGER PRIMARY KEY,"
                + " name       text not null,"
                + " phone_number text not null,"
                + " dirty_secret text not null"
                + ")";
        db.execSQL(createSql);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void reset(){

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.name);
        values.put(KEY_PHONE_NUMBER, contact.phoneNumber);
        values.put(KEY_DIRTY_SECRET, contact.dirtySecret);
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public int getContactsCount(){
        String countItemsQuery = "SELECT * FROM " +TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countItemsQuery, null);
        cursor.close();

        return cursor.getCount();

    }

    public Cursor getContactList() {

        String query = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }





}
