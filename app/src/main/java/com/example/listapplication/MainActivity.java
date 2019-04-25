package com.example.listapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences list = getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME, 0);
        //Get data as string
        String mySecretData = list.getString(Constants.DATA_KEY, "One, Two, Three");
        //Convert comma separated string to list
        List<String> mySecretDataList = new ArrayList<>(Arrays.asList(mySecretData.split("\\s*,\\s*")));
        String itemToDelete = getIntent().getStringExtra(Constants.DELETE_ITEM_KEY);

        if (itemToDelete != null) {
            for (Iterator<String> iter = mySecretDataList.listIterator(); iter.hasNext(); ) {
                String a = iter.next();
                if (a.equalsIgnoreCase(itemToDelete)) {
                    iter.remove();
                }
            }
            //Convert list to comma separated string

            StringBuilder sb = new StringBuilder();
            if (mySecretDataList.size() > 0) {
                for (String s : mySecretDataList) {
                    sb.append(s);
                    sb.append(", ");
                }
            }
            SharedPreferences.Editor editor = list.edit();
            editor.putString(Constants.DATA_KEY, sb.toString());
            editor.apply();
        }

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1, mySecretDataList));

    }

    public void addItemActivity(View v) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
    }

    public void deleteItemActivity(View v) {
        Intent intent = new Intent(MainActivity.this, DeleteItemActivity.class);
        startActivity(intent);
    }
}
