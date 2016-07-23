package com.example.bookacess;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class BankActivityFragment extends Fragment {

    private Button previousClick;
    private Button nextClick;


    public BankActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_bank, container, false);

        previousClick = (Button) v.findViewById(R.id.previousPage);
        nextClick = (Button) v.findViewById(R.id.nextPage);

        previousClick.setOnClickListener(previous);
        nextClick.setOnClickListener(next);
        return v;
    }



    View.OnClickListener previous = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            startActivity(new Intent(getContext(),MainActivity.class));

        }
    };



    View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            startActivity(new Intent(getContext(),EmailActivity.class));


        }
    };

}
