package com.example.listapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        SharedPreferences myData = getSharedPreferences(Constants.PREFERENCES_NAME,0);
        String existingDataList = myData.getString(Constants.DATA_KEY, "");
        existingDataList = existingDataList.concat(", ").concat(editText.getText().toString());
        SharedPreferences.Editor editor = myData.edit();
        editor.putString(Constants.DATA_KEY, existingDataList);
        editor.apply();
    }

    public void mainActivity(View v) {
        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
