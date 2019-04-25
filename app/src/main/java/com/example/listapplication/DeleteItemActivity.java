package com.example.listapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class DeleteItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        Spinner spinner = findViewById(R.id.deleteDataList);
        List<String> itemsList = getIntent().getStringArrayListExtra(Constants.LIST_EXTRA_KEY);
        spinner.setAdapter(new ArrayAdapter<>(DeleteItemActivity.this,android.R.layout.simple_spinner_item, itemsList));
    }

    public void mainActivity(View v) {
        Intent intent = new Intent(DeleteItemActivity.this, MainActivity.class);
        Spinner spinner = findViewById(R.id.deleteDataList);
        String selectedItem = spinner.getSelectedItem().toString();
        intent.putExtra(Constants.DELETE_ITEM_KEY, selectedItem);
        startActivity(intent);
    }

}
