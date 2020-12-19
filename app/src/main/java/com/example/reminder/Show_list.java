package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import android.os.Bundle;

public class Show_list extends AppCompatActivity {
    databaseHandler db= new databaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ListView listView= findViewById(R.id.lv);
        ArrayList<String> ar= new ArrayList<>();
        Button button= findViewById(R.id.dlt);
        Cursor res= db.getAll();
        if(res.getCount()!=0)
        {

            while ((res.moveToNext()))
            {
                String d3,d2,d1= res.getString(1).toUpperCase();
                d2= res.getString(2);
                d3= res.getString(3);
                ar.add("ITEM=  "+d1+"\n\nQUANTITY= "+d2+"\nPRICE= Rs"+d3+"/-\n");

            }
            ArrayAdapter ad= new ArrayAdapter(Show_list.this,android.R.layout.simple_list_item_1,ar);

            listView.setAdapter(ad);

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.onDlt();
            }
        });
    }
}