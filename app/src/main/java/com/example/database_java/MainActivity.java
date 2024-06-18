package com.example.database_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eId,eName,eAge,eCity;
    Button save,view,update,delete,search;
    DataBaseHandler db;

    String id;
    String name;
    String age;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eId = findViewById(R.id.id);
        eName = findViewById(R.id.name);
        eAge = findViewById(R.id.age);
        eCity = findViewById(R.id.city);

        save = findViewById(R.id.save);
        view = findViewById(R.id.view);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        search = findViewById(R.id.search);

        save.setOnClickListener(this);
        view.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        search.setOnClickListener(this);

        db = new DataBaseHandler(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            //save

            case R.id.save:
                name = eName.getText().toString();
                age = eAge.getText().toString();
                city = eCity.getText().toString();

                if (name.equals("") | age.equals("") | city.equals(""))
                {
                    Toast.makeText(this, "Please Fill The Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addEmployee(name,age,city);
                    eId.setText(" ");
                    eName.setText(" ");
                    eAge.setText(" ");
                    eCity.setText(" ");
                    Toast.makeText(this, "Data Saved Successfully..", Toast.LENGTH_SHORT).show();
                }
                break;

                //view

            case R.id.view:
                String data = db.getEmployee();

                if (data.equals(""))
                {
                    Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i1 = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i1);
                }
                break;

                //delete

//            case R.id.update:
//                name = eName.getText().toString();
//                age = eAge.getText().toString();
//                city = eCity.getText().toString();
//
//                if (name.equals("") | age.equals("") | city.equals(""))
//                {
//                    Toast.makeText(this, "Please Fill The Fields", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    db.updateEmployee(name,age,city);
//                    eId.setText(" ");
//                    eName.setText(" ");
//                    eAge.setText(" ");
//                    eCity.setText(" ");
//                    Toast.makeText(this, "Update Successfully..", Toast.LENGTH_SHORT).show();
//                }
//                break;

            case R.id.delete:
                id = eId.getText().toString();

                if (id.equals(""))
                {
                    Toast.makeText(this, "Please Fill The Id..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    long l = Long.parseLong(id);
                    db.deleteEmployee(l);
                    eId.setText("");
                    eName.setText("");
                    eAge.setText("");
                    eCity.setText("");
                    Toast.makeText(this, "Data Deleted Successfully..", Toast.LENGTH_SHORT).show();
                }
                break;

                //search

            case R.id.search:
                id = eId.getText().toString().trim();

                if(id.equals(""))
                {
                    Toast.makeText(this, "Please Fill The ID", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        long l1 = Long.parseLong(id);
                        name = db.getName(l1);
                        age = db.getAge(l1);
                        city = db.getCity(l1);

                        eName.setText(name);
                        eAge.setText(age);
                        eCity.setText(city);

                        Toast.makeText(this, "Data Found Successfully.", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, "Id Is Not Valid..", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }
}