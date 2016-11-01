package com.palle.lekha.mywallet.Model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.palle.lekha.mywallet.Data.MyWalletDatabase;
import com.palle.lekha.mywallet.R;

public class CategoryAddActivity extends AppCompatActivity {

    Button btn_add;
    EditText edit_addcategory;
    TextView tv_type;

    ArrayAdapter<String> typeArrayAdapter;
    MyWalletDatabase database;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        btn_add= (Button) findViewById(R.id.button_addcategory);
        edit_addcategory= (EditText) findViewById(R.id.editText_addcategory);
        tv_type= (TextView) findViewById(R.id.textView_type);

        database=new MyWalletDatabase(CategoryAddActivity.this);

        in=getIntent();
        tv_type.setText(in.getStringExtra("TYPE").toString());


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edit_addcategory.getText().toString().isEmpty())) {
                    Toast.makeText(CategoryAddActivity.this, "Enter category", Toast.LENGTH_SHORT).show();
                }
                else {
                        String type = tv_type.getText().toString();
                        String category = edit_addcategory.getText().toString();

                        long result = database.createCategoryDetails(category,type);

                        if (result > -1) {
                            Toast.makeText(CategoryAddActivity.this, "Category Added", Toast.LENGTH_SHORT).show();
                            edit_addcategory.setText("");
                        }

                }



            }
        });
    }
}