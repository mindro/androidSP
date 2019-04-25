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
        SharedPreferences list = getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME, 0);
        String mySecretData = list.getString(Constants.DATA_KEY, "");
        List<String> mySecretDataList = Arrays.asList(mySecretData.split("\\s*,\\s*"));
        Spinner spinner = findViewById(R.id.deleteDataList);
        spinner.setAdapter(new ArrayAdapter<>(DeleteItemActivity.this,android.R.layout.simple_spinner_item, mySecretDataList));
    }

    public void mainActivity(View v) {
        Intent intent = new Intent(DeleteItemActivity.this, MainActivity.class);
        Spinner spinner = findViewById(R.id.deleteDataList);
        String selectedItem = spinner.getSelectedItem().toString();
        intent.putExtra(Constants.DELETE_ITEM_KEY, selectedItem);
        startActivity(intent);
    }

}
