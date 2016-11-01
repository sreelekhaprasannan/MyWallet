package com.palle.lekha.mywallet.Model;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.palle.lekha.mywallet.Data.MyWalletDatabase;
import com.palle.lekha.mywallet.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewIncomeFragment extends Fragment {

    MyWalletDatabase database;

    ListView listView_Incomeview;
    TextView textViewId,textViewDate,textViewCategory,textViewDescription,textViewAmount,textViewtotalIncome;
    EditText editTextTotalIncome;
    private ArrayList<ViewIncome> IncomeArrayList=new ArrayList<>();
    private  IncomeArrayAdapter incomeArrayAdapter;
    String TAG="ViewIncomeFragment";


    public ViewIncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_view_income, container, false);
        database = new MyWalletDatabase(getActivity());
        Log.e(TAG,"In the onCreateView method");
        listView_Incomeview = (ListView) v.findViewById(R.id.listview_IncomeView);

        textViewId=(TextView)v.findViewById(R.id.textview_idViewIncome);
        textViewCategory=(TextView)v.findViewById(R.id.textview_CategoryViewIncome);
        textViewAmount=(TextView)v.findViewById(R.id.textview_AmountViewIncome);
        textViewDescription=(TextView)v.findViewById(R.id.textview_DescriptionViewIncome);
        textViewDate=(TextView)v.findViewById(R.id.textview_DateViewIncome);
        textViewtotalIncome=(TextView)v.findViewById(R.id.textview_TotalIncome);

        editTextTotalIncome=(EditText)v.findViewById(R.id.editText_TotalIncome);
        incomeArrayAdapter = new IncomeArrayAdapter();

        readFromDatabaseAndUpdateListView();

        listView_Incomeview.setAdapter(incomeArrayAdapter);



        return v;
    }


    private void readFromDatabaseAndUpdateListView(){

        IncomeArrayList.clear();
        IncomeArrayList.addAll(database.getAllIncomeDetails());
        incomeArrayAdapter.notifyDataSetChanged();
    }



    class IncomeArrayAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return IncomeArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return IncomeArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            ViewIncome viewIncome =IncomeArrayList.get(position);

            //View v=getLayoutInflater().inflate(R.layout.income_row,null);

            View v=getActivity().getLayoutInflater().inflate(R.layout.income_row,null);

            TextView textViewCategory=(TextView)v.findViewById(R.id.textview_Category_RowIncome);
            TextView textViewId=(TextView)v.findViewById(R.id.textview_id_RowIncome);
            TextView textViewDate=(TextView)v.findViewById(R.id.textview_Date_RowIncome);
            TextView textViewDescription=(TextView)v.findViewById(R.id.textview_Description_RowIncome);

            TextView textViewAmount=(TextView)v.findViewById(R.id.textview_Amount_RowIncome);

            textViewId.setText(""+viewIncome.getId());
            textViewDate.setText(""+viewIncome.getDate());
            textViewCategory.setText(""+viewIncome.getCategory());
            textViewDescription.setText(""+viewIncome.getDescription());
            textViewAmount.setText(""+viewIncome.getAmount());

            return v;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e(TAG,"In the onStart method");

        incomeArrayAdapter = new IncomeArrayAdapter();
        readFromDatabaseAndUpdateListView();
        listView_Incomeview.setAdapter(incomeArrayAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e(TAG,"In the onResume method");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e(TAG,"In the onPause method");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"In the onAttach method");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG,"In the onCreate method");
    }
}
