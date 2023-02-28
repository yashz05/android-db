package com.twp.employee_table;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.name);
        EditText code = findViewById(R.id.code);
        EditText desg = findViewById(R.id.desg);
        EditText salary = findViewById(R.id.salary);
        Button insert = findViewById(R.id.insert);
        Button update = findViewById(R.id.update);
        Button show = findViewById(R.id.show);
        Button delete = findViewById(R.id.delete);
        db_helper db = new db_helper(this);
        RecyclerView emplist = findViewById(R.id.listemp);
        employee[] n = new employee[3];

        db.initdb();
        insert.setOnClickListener((e -> {
            db.insertEmployee(name.getText().toString(), Integer.parseInt(code.getText().toString()), desg.getText().toString(), salary.getText().toString(), this);
        }));
        update.setOnClickListener((e -> {
            db.updateEmployee(name.getText().toString(), "new yash", Integer.parseInt(code.getText().toString()), desg.getText().toString(), salary.getText().toString(), this);
        }));
        delete.setOnClickListener((e -> {
            db.delete(name.getText().toString(), this);
        }));
        show.setOnClickListener((e -> {
            int i = 0;
            Cursor d = db.getData();
            if (d.moveToFirst()) {
                while (!d.isAfterLast()) {
                    employee em = new employee();
                    em.name = d.getString(1);
                    em.code = d.getInt(2);
                    em.desgination = d.getString(3);
                    em.salary = d.getString(4);
                    n[i] = em;
                    d.moveToNext();
                    i++;
                }
            }
            employee_adapter a = new employee_adapter(n);
            emplist.setAdapter(a);
            emplist.setLayoutManager(new LinearLayoutManager(this));
        }));


    }
}