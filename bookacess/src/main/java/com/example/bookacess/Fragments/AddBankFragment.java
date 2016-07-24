package com.example.bookacess.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookacess.DBHelper;
import com.example.bookacess.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBankFragment extends Fragment {


    private DBHelper databaseInsert;
    private Button added;
    private EditText bankinfo;
    private EditText user;
    private EditText password;
    private String name = null;


    public AddBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_bank, container, false);

        bankinfo = (EditText) v.findViewById(R.id.nbank);
        user = (EditText) v.findViewById(R.id.bankUser);
        bankinfo = (EditText) v.findViewById(R.id.bankPassword);

        added = (Button) v.findViewById(R.id.added);


        databaseInsert = new DBHelper(getContext());
        added.setOnClickListener(send);

        return v;
    }

    View.OnClickListener send = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            name = bankinfo.getText().toString();

            if (databaseInsert.insertBanks(name)) {
                Toast.makeText(getContext(), "Bank name added", Toast.LENGTH_SHORT).show();
            } else
                 {
                Toast.makeText(getContext(), "Kindly fill in the data", Toast.LENGTH_SHORT).show();
            }
            {

            }


        }
    };



}


