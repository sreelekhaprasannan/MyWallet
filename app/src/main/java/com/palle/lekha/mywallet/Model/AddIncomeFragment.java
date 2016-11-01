package com.palle.lekha.mywallet.Model;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.palle.lekha.mywallet.Data.MyWalletDatabase;
import com.palle.lekha.mywallet.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncomeFragment extends Fragment {
    EditText editxt_amount,editxt_description;
    TextView txtView_date;
    Spinner spinnercategory;
    Button btnAdd;
    ImageButton imgbtn_date;
    ArrayList<String> incomeCategoryList=new ArrayList<>();
    ArrayAdapter <String> categoryArrayAdapter;
    MyWalletDatabase database;
    FloatingActionButton btn_categoryAdd;





    //String[] categoryary={"Salary","Rent","Deposits","Shares","Others"};

    public AddIncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database=new MyWalletDatabase(getActivity());

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_income, container, false);

        editxt_amount= (EditText) v.findViewById(R.id.editxt_incomeAmount);
        txtView_date= (TextView) v.findViewById(R.id.txtView_incomeDate);
        editxt_description= (EditText) v.findViewById(R.id.editxt_incomeDescription);
        btnAdd= (Button) v.findViewById(R.id.btn_incomeAdd);
        imgbtn_date=(ImageButton)v.findViewById(R.id.btn_date);
        btn_categoryAdd= (FloatingActionButton) v.findViewById(R.id.fab_addcategory);
        spinnercategory= (Spinner) v.findViewById(R.id.spnr_incomecategory);
        incomeCategoryList=database.getAllIncomeCategory();
        categoryArrayAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,incomeCategoryList);



        int mYear, mMonth, mDay;
        java.util.Calendar c= java.util.Calendar.getInstance();
        mYear =c.get(java.util.Calendar.YEAR);
        mMonth =c.get(java.util.Calendar.MONTH)+1;
        mDay = c.get(java.util.Calendar.DAY_OF_MONTH);
        String month,day;
        month=""+mMonth;
        day=""+mDay;
        if(mMonth<10){
            month = "0"+mMonth;
        }

        if(mDay<10){
            day = "0"+mDay;
        }



        txtView_date.setText(""+mDay+"/"+mMonth+"/"+mYear);


        spinnercategory.setAdapter(categoryArrayAdapter);
        categoryArrayAdapter.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=txtView_date.getText().toString();
                String category=incomeCategoryList.get(spinnercategory.getSelectedItemPosition());
                String description=editxt_description.getText().toString();
                int amount=Integer.parseInt(editxt_amount.getText().toString());
                if (editxt_amount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter Amount", Toast.LENGTH_SHORT).show();

                }
                else {
                    long result = database.createIncomeDetails(date, category, description, amount);
                    if (result > -1) {
                        Toast.makeText(getActivity(), "Income Added", Toast.LENGTH_SHORT).show();
                        editxt_description.setText("");
                        editxt_amount.setText("");
                    }
                }
            }
        });

        imgbtn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 showdate();

            }
        });



        btn_categoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),CategoryAddActivity.class);
                i.putExtra("TYPE","Income");
                startActivity(i);


            }
        });




        return v;
    }

    public void  showdate(){
        int mYear, mMonth, mDay;
        java.util.Calendar c= java.util.Calendar.getInstance();
        mYear = c.get(java.util.Calendar.YEAR);
        mMonth = c.get(java.util.Calendar.MONTH)+1;
        mDay = c.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String strMonth = String.valueOf(month), strDay = String.valueOf(day), strYear = String.valueOf(year);

                if(month<10){
                    strMonth = "0"+strMonth;
                }

                if(day<10){
                    strDay = "0"+strDay;
                }

                txtView_date.setText(""+strDay+"/"+strMonth+"/"+strYear);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        incomeCategoryList=database.getAllExpenseCategory();
        categoryArrayAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,incomeCategoryList);
        spinnercategory.setAdapter(categoryArrayAdapter);
        categoryArrayAdapter.notifyDataSetChanged();
    }
}