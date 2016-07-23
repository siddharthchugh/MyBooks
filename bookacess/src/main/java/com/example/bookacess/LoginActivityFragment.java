package com.example.bookacess;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {


    private EditText textUser;
    private EditText textPassword;
    private Button click_Login;
    private String user = null;
    private String password = null;





    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        textUser = (EditText) v.findViewById(R.id.bankUser);
        textPassword = (EditText) v.findViewById(R.id.bankPassword);
        click_Login = (Button) v.findViewById(R.id.loginPost);
        click_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = "Siddharth";
                password = "123";

                String users = textUser.getText().toString();
                String pwd = textPassword.getText().toString();
                if (users.equals(user) && pwd.equals(password)) {

               startActivity(new Intent(getContext(),MainActivity.class));
                    Toast.makeText(getContext(), "Verified", Toast.LENGTH_SHORT).show();

                } else if (users == null || pwd.equals(password)) {
                    Toast.makeText(getContext(), "Username not Verified or invalid", Toast.LENGTH_SHORT).show();

                } else if (users.equals(user) || pwd == null) {
                    Toast.makeText(getContext(), "Kindly insert valid password", Toast.LENGTH_SHORT).show();

                }
                else if(users==null || pwd==null){
                    Toast.makeText(getContext(),  "Kindly insert valid password", Toast.LENGTH_SHORT).show();

                }


            }
        });


        return v;
    }

}
