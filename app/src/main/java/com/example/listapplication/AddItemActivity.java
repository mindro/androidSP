package com.example.listapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText editText = findViewById(R.id.editText);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEditor = preferences.edit();
        Set<String> updatedSet = preferences.getStringSet(Constants.DATA_KEY, new HashSet<String>());
        updatedSet.add(editText.getText().toString());
        spEditor.putStringSet(Constants.DATA_KEY, updatedSet);
        spEditor.apply();
    }

    public void mainActivity(View v) {
        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
