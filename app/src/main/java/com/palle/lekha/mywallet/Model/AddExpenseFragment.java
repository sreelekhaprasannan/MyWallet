package com.palle.lekha.mywallet.Model;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class AddExpenseFragment extends Fragment {


    TextView tv_date;
    ImageButton ib_date;
    EditText editText_description,editText_amount;
    Spinner spnrExpensecategory;
    Button btn_AddExpense;
    ArrayAdapter categoryArrayAdapter;
    FloatingActionButton fab_expcategoryAdd;
    ArrayList <String> categoryList = new ArrayList<>();
    MyWalletDatabase database;

    String TAG="AddExpenseFragment";


//    String[] categoryary={"Salary","Rent","Deposits","Shares","Others"};




    public AddExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        database=new MyWalletDatabase(getActivity());

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_expense, container, false);

        editText_description= (EditText) v.findViewById(R.id.editxt_expenseDescription);
        editText_amount= (EditText) v.findViewById(R.id.editxt_expenseAmount);
        btn_AddExpense= (Button) v.findViewById(R.id.btn_expenseAdd);
        ib_date= (ImageButton) v.findViewById(R.id.imgbtn_date);
        tv_date= (TextView) v.findViewById(R.id.tv_expenseDate);
        spnrExpensecategory= (Spinner) v.findViewById(R.id.spnr_expensecategory);
        fab_expcategoryAdd= (FloatingActionButton) v.findViewById(R.id.fab_expCategoryAdd);
        categoryList =database.getAllExpenseCategory();
        categoryArrayAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,categoryList);



        int mYear, mMonth, mDay;
        java.util.Calendar c= java.util.Calendar.getInstance();
        mYear = c.get(java.util.Calendar.YEAR);
        mMonth = c.get(java.util.Calendar.MONTH)+1;
        mDay = c.get(java.util.Calendar.DAY_OF_MONTH);

        tv_date.setText(""+mDay+"/"+mMonth+"/"+mYear);

        spnrExpensecategory.setAdapter(categoryArrayAdapter);
        categoryArrayAdapter.notifyDataSetChanged();



        btn_AddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=tv_date.getText().toString();
                String description=editText_description.getText().toString();
                String category=categoryList.get(spnrExpensecategory.getSelectedItemPosition());
                int amount=Integer.parseInt(editText_amount.getText().toString());

                if (editText_amount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter Amount", Toast.LENGTH_SHORT).show();
                    ViewExpenseFragment viewExpenseFragment=new ViewExpenseFragment();
                    viewExpenseFragment.expenseArrayAdapter.notifyDataSetChanged();
                    viewExpenseFragment.listView_Expenseview.setAdapter(viewExpenseFragment.expenseArrayAdapter);
                }

                else {
                    long result = database.createExpenseDetails(date, category, description, amount);

                    if (result > -1) {
                        Toast.makeText(getActivity(), "Expense Added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        ib_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdate();
            }
        });


        fab_expcategoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),CategoryAddActivity.class);
                i.putExtra("TYPE","Expense");
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

                tv_date.setText(""+strDay+"/"+strMonth+"/"+strYear);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        categoryList=database.getAllExpenseCategory();
        categoryArrayAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,categoryList);
        spnrExpensecategory.setAdapter(categoryArrayAdapter);
        categoryArrayAdapter.notifyDataSetChanged();

    }
}
