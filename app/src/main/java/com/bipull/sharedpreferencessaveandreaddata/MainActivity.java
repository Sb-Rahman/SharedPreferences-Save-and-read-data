package com.bipull.sharedpreferencessaveandreaddata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declaration of variables
    EditText text, number;
    Button save, read;

    private AlertDialog.Builder alertDialogBuilder;

    int intValue, intDefaultValue = 0;
    String stringValue, defaultstring = "No Data";
    boolean booleanValue, defaultBooleanValue = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Shared Preferences");

        // variables initialization
        text = findViewById(R.id.someText);
        number = findViewById(R.id.someNumber);
        save = findViewById(R.id.buttonsave);
        read = findViewById(R.id.buttonread);


        //set action to button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringValue = text.getText().toString();
                String tempValue = number.getText().toString().trim();


                if (!tempValue.equals("")) {
                    //convert string to int
                    intValue = Integer.parseInt(tempValue);

                    //getting boolean value
                    booleanValue = intValue >= 10;


                    //save data
                    saveData();


                } else {
                    number.setError("Field cannot be empty!");
                }

            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read  data

                readData();
            }
        });

    }

    private void readData() {
//getting message
        SharedPreferences sharedPreferences = getSharedPreferences("Example", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("Text", stringValue);
        int number = sharedPreferences.getInt("Number", intValue);
        boolean value = sharedPreferences.getBoolean("State", booleanValue);
     //show saved data with alertDialog
        alertDialogBuilder=new AlertDialog.Builder(MainActivity.this);
alertDialogBuilder.setTitle("Saved Data");
        alertDialogBuilder.setMessage("Your text: "+text+"\n Your number: "+number+"\n Greater than or equal Five: "+value);


        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();



//show saved data with toast

    //    Toast.makeText(this, "Name: " + text + " \n Number: " + number + " \n Boolean: " + value, Toast.LENGTH_LONG).show();

    }

    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("Example", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //data save
        editor.putString("Text", stringValue);
        editor.putInt("Number", intValue);
        editor.putBoolean("State", booleanValue);
        editor.apply();

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_LONG).show();


    }
}