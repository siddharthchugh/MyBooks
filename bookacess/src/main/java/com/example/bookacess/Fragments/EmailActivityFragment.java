package com.example.bookacess.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookacess.Activities.CallLogActivity;
import com.example.bookacess.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class EmailActivityFragment extends Fragment {


    private EditText username;
    private EditText emailAddress;
    private EditText userComments;
    private Button click_Send;
    private Button previousClick;

    String user = null;
    String mail_Subject = null;
    String mail = null;
    String comments = null;

    public EmailActivityFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_email, container, false);

        username = (EditText) v.findViewById(R.id.user);
        emailAddress = (EditText) v.findViewById(R.id.userEmail);
        userComments = (EditText) v.findViewById(R.id.userComment);
        previousClick = (Button) v.findViewById(R.id.previousPage);

        click_Send = (Button) v.findViewById(R.id.loginPost);
        click_Send.setOnClickListener(emailSend);
        previousClick.setOnClickListener(prevPage);

        return v;
    }


    View.OnClickListener prevPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            startActivity(new Intent(getContext(), CallLogActivity.class));
        }
    };


    View.OnClickListener emailSend = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            user = username.getText().toString();

            mail = emailAddress.getText().toString();
            comments = userComments.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{user});
            email.putExtra(Intent.EXTRA_EMAIL, mail);

            email.putExtra(Intent.EXTRA_TEXT, comments);

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));


        }
    };


}
