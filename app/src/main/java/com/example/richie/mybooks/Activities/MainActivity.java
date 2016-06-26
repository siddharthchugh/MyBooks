package com.example.richie.mybooks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.richie.mybooks.Fragments.MainActivityFragment;
import com.example.richie.mybooks.R;

public class MainActivity extends AppCompatActivity {

    private boolean doubletappressedone = false;
    private com.getbase.floatingactionbutton.FloatingActionButton addAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addAction = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.action_add);
        addAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, AddBooksActivity.class));

            }
        });

    }



    @Override
    public void onBackPressed() {

        if (doubletappressedone) {
            super.onBackPressed();
            return;
        }
        this.doubletappressedone = true;
        Toast.makeText(getApplication(), "Press twice to finish the app", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletappressedone = true;
            }
        }, 2000);
    }

    ;


}
