package com.palle.lekha.mywallet.Model;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class ViewExpenseFragment extends Fragment {


    MyWalletDatabase database;

    public ListView listView_Expenseview;
    TextView textViewId, textViewDate, textViewCategory, textViewDescription, textViewAmount, textViewtotalExpense;
    EditText editTextTotalExpense;
    public ArrayList<ViewExpense> expenseArrayList ;
    public ExpenseArrayAdapter expenseArrayAdapter;


    public ViewExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_expense, container, false);
        database = new MyWalletDatabase(getActivity());

        expenseArrayList = new ArrayList<>();

        listView_Expenseview = (ListView) v.findViewById(R.id.listview_ExpenseView);

        textViewId = (TextView) v.findViewById(R.id.textview_idViewExpense);
        textViewCategory = (TextView) v.findViewById(R.id.textview_CategoryViewExpense);
        textViewAmount = (TextView) v.findViewById(R.id.textview_AmountViewExpense);
        textViewDescription = (TextView) v.findViewById(R.id.textview_DescriptionViewExpense);
        textViewDate = (TextView) v.findViewById(R.id.textview_DateViewExpense);
        textViewtotalExpense = (TextView) v.findViewById(R.id.textview_TotalExpense);

        editTextTotalExpense = (EditText) v.findViewById(R.id.editText_TotalExpense);
        expenseArrayAdapter = new ViewExpenseFragment.ExpenseArrayAdapter();


        listView_Expenseview.setAdapter(expenseArrayAdapter);

        readFromDatabaseAndUpdateListView();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        readFromDatabaseAndUpdateListView();
    }

    public void readFromDatabaseAndUpdateListView() {

        expenseArrayList.clear();
        expenseArrayList.addAll(database.getAllExpenseDetails());
        expenseArrayAdapter.notifyDataSetChanged();
    }

    class ExpenseArrayAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return expenseArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return expenseArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            ViewExpense viewExpense = expenseArrayList.get(position);

            //View v=getLayoutInflater().inflate(R.layout.expense_row,null);

            View v = getActivity().getLayoutInflater().inflate(R.layout.expense_row, null);

            TextView textViewId = (TextView) v.findViewById(R.id.textview_id_RowExpense);
            TextView textViewDate = (TextView) v.findViewById(R.id.textview_Date_RowExpense);
            TextView textViewCategory = (TextView) v.findViewById(R.id.textview_Category_RowExpense);
            TextView textViewDescription = (TextView) v.findViewById(R.id.textview_Description_RowExpense);
            TextView textViewAmount = (TextView) v.findViewById(R.id.textview_Amount_RowExpense);

            textViewId.setText("" + viewExpense.getId());
            textViewDate.setText("" + viewExpense.getDate());
            textViewCategory.setText("" + viewExpense.getCategory());
            textViewDescription.setText("" + viewExpense.getDescription());
            textViewAmount.setText("" + viewExpense.getAmount());

            return v;
        }

    }
}
