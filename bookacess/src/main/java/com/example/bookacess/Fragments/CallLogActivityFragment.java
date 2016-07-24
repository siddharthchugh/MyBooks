package com.example.bookacess.Fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.bookacess.Activities.BankActivity;
import com.example.bookacess.Activities.EmailActivity;
import com.example.bookacess.R;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class CallLogActivityFragment extends Fragment {

    private Cursor managedCursor;
    private TextView tData = null;
    String phNumber = null;
    String callType = null;
    String callDate = null;
    Date callDayTime = null;
    private Button previousClick;
    private Button nextClick;

    String names=null;
    ListView ls;

    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            CallLog.Calls.CACHED_NAME,
       //     CallLog.Calls.NUMBER,
//            CallLog.Calls.DATE,
  //          CallLog.Calls.TYPE,
    //        CallLog.Calls.DURATION
    };

        private static final int[] LAYOUT_ITEMS_TO_FILL = new int[] {
            android.R.id.text1,
            android.R.id.text2
    };
    SimpleCursorAdapter adapter = null;
    Cursor cursor;

    public CallLogActivityFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_call_log, container, false);
        tData = (TextView) v.findViewById(R.id.callLog);
        tData.setMovementMethod(new ScrollingMovementMethod());
        previousClick = (Button) v.findViewById(R.id.previousPage);
        nextClick = (Button) v.findViewById(R.id.nextPage);
        previousClick.setOnClickListener(prevPage);
        nextClick.setOnClickListener(nextPage);

            return v;

    }


    View.OnClickListener prevPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            startActivity(new Intent(getContext(),BankActivity.class));
        }
    };


    View.OnClickListener nextPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getContext(),EmailActivity.class));

        }
    };


    public void getCallDetails() {
        ContentResolver resolver = getActivity().getContentResolver();
        String strOrder = android.provider.CallLog.Calls.DATE + " ASC";
        managedCursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, strOrder);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        while (managedCursor.moveToNext()) {
            names = managedCursor.getString(name);
            phNumber = managedCursor.getString(number);
            callType = managedCursor.getString(type);
            callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:

                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            tData.append("name:"+" "+names +"\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration+"\n"+"\n"+"\n");


        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_call_log,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case R.id.action_logs:

                getCallDetails();

        }

        return super.onOptionsItemSelected(item);
    }
}
