package com.clintonmedbery.database;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    SimpleCursorAdapter contactsAdapter;
    ContactsHandler handler;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new ContactsHandler(this);
        handler.reset();


        Contact basic1Contact = new Contact("Bob", "4055039994", "Likes The Hobbit Movies better than TLOTR");
        handler.addContact(basic1Contact);
        Contact basic2Contact = new Contact("Randy", "4055038884", "Hates cheeseburgers");
        handler.addContact(basic2Contact);
        Contact basic3Contact = new Contact("Larry", "4055034444", "Never votes");
        handler.addContact(basic3Contact);
        Contact basic4Contact = new Contact("Frank", "4055034444", "Doesn't put back shopping cart");
        handler.addContact(basic4Contact);
        Contact basic5Contact = new Contact("John", "4055035444", "Doesn't read term and conditions");
        handler.addContact(basic5Contact);
        Contact basic6Contact = new Contact("Maria", "5555555555", "Has a fake number");
        handler.addContact(basic6Contact);
        Contact basic7Contact = new Contact("Danielle", "5555566666", "Doesn't prefer Thin Mints");
        handler.addContact(basic7Contact);
        Contact basic8Contact = new Contact("Fabio", "5555566666", "Can't believe it's not butter");
        handler.addContact(basic8Contact);
        Contact basic9Contact = new Contact("Josh", "5555566666", "Liked the second Star Wars trilogy better");
        handler.addContact(basic9Contact);


        displayListView();

        getListView().setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view,int position, long id) {

                Cursor cursor = handler.getContactList();
                Context context = getApplicationContext();
                Contact contact = new Contact();
                if(cursor.moveToPosition(position)) {
                    contact._id = cursor.getInt(cursor.getColumnIndex("_id"));
                    contact.name = cursor.getString(cursor.getColumnIndex("name"));
                    contact.dirtySecret = cursor.getString(cursor.getColumnIndex("dirty_secret"));
                    contact.phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));


                }
                CharSequence text = contact.name + "\n" + contact.dirtySecret
                        + "\nID: " + contact._id + "\nPHONE: " + contact.phoneNumber;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });


    }

    private void displayListView(){

        Cursor cursor = handler.getContactList();
        String[] columns = new String[]{
               handler.KEY_NAME,
               handler.KEY_PHONE_NUMBER,
               handler.KEY_DIRTY_SECRET
        };

        int[] bind = new int[] {
            R.id.name,
            R.id.number,
            R.id.secret,
        };

        contactsAdapter = new SimpleCursorAdapter(
                this,
                R.layout.data_layout,
                cursor,
                columns,
                bind,
        0);

        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(contactsAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
