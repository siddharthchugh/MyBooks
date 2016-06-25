package com.example.richie.mybooks.Activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
