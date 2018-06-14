package com.cuelogic.maheshauti.tinytorchassign_1;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cuelogic.maheshauti.tinytorchassign_1.Adapter.TinyTorchPostAdapter;
import com.cuelogic.maheshauti.tinytorchassign_1.JSONParsing.Parser;
import com.cuelogic.maheshauti.tinytorchassign_1.Listener.OnLoadMoreListener;
import com.cuelogic.maheshauti.tinytorchassign_1.model.PostDetails;
import com.cuelogic.maheshauti.tinytorchassign_1.Connectivity.HttpClient;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<PostDetails> list;
    String Tag = "Main activity---------------------------";

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Boolean isScrolling;
    LinearLayoutManager mLinerLayoutManagerVertical;
    TinyTorchPostAdapter adapter;
    ProgressBar progressBar;
    public int page_no = 10;
    public static int statuscode;
    int flag = 1;
    LinearLayout view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //  initPaginationScrollingEvents();

        new GetDataTask().execute();


    }

    public void setuploadmoreitems() {
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //  System.out.println("load more called");
                Log.e("haint", "Load More");
                list.add(null);
                adapter.notifyItemInserted(list.size() - 1);

                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("haint", "Load More 2");

                        //Remove loading item
                        list.remove(list.size() - 1);
                        adapter.notifyItemRemoved(list.size());
                        page_no++;
                        //Load data
                        System.out.println("previous list" + list);
                        new GetDataTask().execute();


                    }
                }, 5000);
            }
        });


    }


    private void init() {
        Log.d(Tag, "in init method");
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLinerLayoutManagerVertical = new LinearLayoutManager(this);
        isScrolling = false;
        list = new ArrayList<>();


    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {


        String jsonObjectString;

        @Override
        protected void onPreExecute() {
            Log.d(Tag, "in pre execute ");
            super.onPreExecute();


        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            jsonObjectString = HttpClient.getDataFromWeb(page_no);
            Log.d(Tag, jsonObjectString);
            Parser parser = new Parser(list, jsonObjectString);
            Log.d(Tag, list.toString());
            parser.parseJsonString();
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("msg is :" + list.get(i).getMsg() + " tags = " + list.get(i).getTags() + " url is = " + list.get(i).getImgUrl());
            }
            if (flag == 1) {
                setUpRecyclerView();
                setuploadmoreitems();
                flag = 0;
            }
            adapter.notifyDataSetChanged();
            adapter.setLoaded();
            System.out.println("updated list" + list);


        }
    }


    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(mLinerLayoutManagerVertical);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new TinyTorchPostAdapter(this, list, recyclerView);
        ;
        recyclerView.setAdapter(adapter);
        mLinerLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        //swipe refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new GetDataTask().execute();

                    }
                }, 5000);

            }
        });
        swipeRefreshLayout.setRefreshing(false);


    }

}


