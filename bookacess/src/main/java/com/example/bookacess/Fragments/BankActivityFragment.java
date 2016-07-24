package com.example.bookacess.Fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bookacess.Activities.AddBankActivity;
import com.example.bookacess.Activities.CallLogActivity;
import com.example.bookacess.DBHelper;
import com.example.bookacess.Activities.MainActivity;
import com.example.bookacess.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class BankActivityFragment extends Fragment {

    private Button previousClick;
    private Button nextClick;
    private Spinner list_of_Banks;
    private DBHelper mdatabase;
    private TextView bank_Name;
    private Button add_bank;

    public BankActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_bank, container, false);

        previousClick = (Button) v.findViewById(R.id.previousPage);
        nextClick = (Button) v.findViewById(R.id.nextPage);
        bank_Name = (TextView) v.findViewById(R.id.bank);
        previousClick.setOnClickListener(previous);
        nextClick.setOnClickListener(next);
        list_of_Banks = (Spinner) v.findViewById(R.id.spinner);
        mdatabase = new DBHelper(getContext());

        // Spinner Drop down elements
        ArrayList lables = mdatabase.getAllCotacts();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list_of_Banks.setAdapter(dataAdapter);
        Log.i("the ", "banks" + lables);


        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_bank, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                startActivity(new Intent(getContext(), AddBankActivity.class));

                break;
        }

        return super.onOptionsItemSelected(item);

    }


    View.OnClickListener previous = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            startActivity(new Intent(getContext(), MainActivity.class));

        }
    };


    View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            startActivity(new Intent(getContext(), CallLogActivity.class));


        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}


//class MyCustomAdapter extends ArrayAdapter<String> {
//
//    public MyCustomAdapter(Context context, int textViewResourceId,
//                           String[] objects) {
//        super(context, textViewResourceId, objects);
//        // TODO Auto-generated constructor stub
//    }
//
//    @Override
//    public View getDropDownView(int position, View convertView,
//                                ViewGroup parent) {
//        // TODO Auto-generated method stub
//        return getCustomView(position, convertView, parent);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        return getCustomView(position, convertView, parent);
//    }
//
//    public View getCustomView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        //return super.getView(position, convertView, parent);
//
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = inflater.inflate(R.layout.spinner_data, parent, false);
//        TextView label = (TextView) row.findViewById(R.id.data_bank);
//
////        label.setText(li[position]);
//
//
//        return row;
//    }



