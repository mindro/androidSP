package com.example.listapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    List<String> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setItemsListFromSharedPreferences();

        String itemToDelete = getIntent().getStringExtra(Constants.DELETE_ITEM_KEY);
        if (itemToDelete != null) {
            for (Iterator<String> iter = this.itemsList.listIterator(); iter.hasNext(); ) {
                String a = iter.next();
                if (a.equalsIgnoreCase(itemToDelete)) {
                    iter.remove();
                }
            }
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
            editor.putStringSet(Constants.DATA_KEY, new HashSet<>(this.itemsList));
            editor.apply();
        }

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1, this.itemsList));

    }

    public void addItemActivity(View v) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
    }

    public void deleteItemActivity(View v) {
        Intent intent = new Intent(MainActivity.this, DeleteItemActivity.class);
        intent.putStringArrayListExtra(Constants.LIST_EXTRA_KEY, new ArrayList<>(this.itemsList));
        startActivity(intent);
    }

    public void setItemsListFromSharedPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> itemsSet = preferences.getStringSet(Constants.DATA_KEY, new HashSet<String>());
        this.itemsList = new ArrayList<>(itemsSet);

    }
}
