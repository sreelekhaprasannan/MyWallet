package com.palle.lekha.mywallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.palle.lekha.mywallet.Model.ExpenseMasterActivity;
import com.palle.lekha.mywallet.Model.IncomeMasterActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv_income,tv_expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_income= (TextView) findViewById(R.id.textView_income);
        tv_expense= (TextView) findViewById(R.id.textView_expense);

        tv_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(MainActivity.this, "Income", Toast.LENGTH_SHORT).show();

               Intent i=new Intent(MainActivity.this, IncomeMasterActivity.class);
                startActivity(i);

            }
        });
        tv_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, ExpenseMasterActivity.class);
                startActivity(i);
            }
        });
    }
}
