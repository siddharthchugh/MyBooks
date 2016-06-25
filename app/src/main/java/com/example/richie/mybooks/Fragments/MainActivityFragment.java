package com.example.richie.mybooks.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.richie.mybooks.Activities.AddBooksActivity;
import com.example.richie.mybooks.Activities.BookDetailActivity;
import com.example.richie.mybooks.AdapterManager.Book_DetailAdapter;
import com.example.richie.mybooks.Http.HttpManger;
import com.example.richie.mybooks.JSON.Book_Info;
import com.example.richie.mybooks.Pojo.Book_detail;
import com.example.richie.mybooks.R;
import com.example.richie.mybooks.Url.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View inflater_Layout;
    private ListView book_List;
    private List<Book_detail> book_detailList;
    private ProgressBar progressBar;
    NetworkInfo netInfo;
    private List<GetData> getBookdata;
    Book_detail bd;
    private Book_DetailAdapter bookAdapter;
    private TextView td_Data;
    String book_title;
    String book_author;
    String book_publishers;
    String lastCheckedBy;
    String book_url;
    String bookid;
    String timechecked;
    CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeView;


    public MainActivityFragment() {

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflater_Layout= inflater.inflate(R.layout.fragment_main,container,false);
        book_List = (ListView) inflater_Layout.findViewById(R.id.listView);
        swipeView= (SwipeRefreshLayout)inflater_Layout.findViewById(R.id.swipe_refresh_layout);


        return inflater_Layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeView.setEnabled(true);

        getBookdata = new ArrayList<>();
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setEnabled(true);

                        swipeView.setRefreshing(false);
                        Display();

                    }
                }, 1000);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void Display() {

        if (isConnection()) {

            requestData(Constants.GETBOOKS);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isConnection()) {

            requestData(Constants.GETBOOKS);

        } else {
            Toast.makeText(getContext(), "Sorry no data availsble.Please Add data", Toast.LENGTH_SHORT).show();
        }

    }


    public boolean isConnection() {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;
        } else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("Your are not connected to the internet, try again later !");

            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    getActivity().finish();
                }
            });

            alertDialogBuilder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    onResume();
                }
            });


            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return false;
        }
    }
    private void requestData(String url) {

        GetData mg = new GetData();
        mg.execute(url);

    }



    public void updated() {


        bookAdapter = new Book_DetailAdapter(getActivity(), R.layout.book_items, book_detailList);
        book_List.setAdapter(bookAdapter);
        book_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bd = (Book_detail) adapterView.getItemAtPosition(i);
                Log.v("the ", "value" + bd.toString());

                if (isConnection()) {

                    if (bd != null) {
                        book_title = bd.getTitle();
                        book_author = bd.getAuthor_name();
                        book_publishers = bd.getPublishers();
                        book_url = bd.getUrl();
                        lastCheckedBy = bd.getLastCheckedBy();
                        timechecked = bd.getLastCheckedOut();
                        Intent d_Intent = new Intent(getActivity(), BookDetailActivity.class);
                        d_Intent.putExtra("booktitle", book_title);
                        d_Intent.putExtra("bookauthor", book_author);
                        d_Intent.putExtra("bookpublishers", book_publishers);
                        d_Intent.putExtra("bookurl", book_url);
                        d_Intent.putExtra("userchecked", lastCheckedBy);
                        d_Intent.putExtra("timechecked", timechecked);

                        startActivity(d_Intent);
                    }
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.setting_menu, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.addBooks:

                startActivity(new Intent(getContext(), AddBooksActivity.class));

                break;

            case R.id.referesh:
                Display();
                break;


        }
        return super.onOptionsItemSelected(item);


    }
    class GetData extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (getBookdata.size() == 0) {
                //  progressBar.setVisibility(View.VISIBLE);
            }
            getBookdata.add(this);


        }

        @Override
        protected String doInBackground(String... params) {


            String content = HttpManger.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {


            getBookdata.remove(this);
            if (getBookdata.size() == 0) {
                //       progressBar.setVisibility(View.INVISIBLE);
                swipeView.setRefreshing(false);

            }

            if (s != null) {
                book_detailList = Book_Info.parseFeed(s);
                updated();

            } else {
                Toast.makeText(getContext(), "Please connect to Intenet ", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
