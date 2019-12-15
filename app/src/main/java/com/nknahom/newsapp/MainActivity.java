package com.nknahom.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String NEWSURL = "https://content.guardianapis.com/search?api-key=test";

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = findViewById(R.id.list);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());

        newsListView.setAdapter(newsAdapter);

        NewsAsyncTask newsAsyncTask = new NewsAsyncTask();
        newsAsyncTask.execute(NEWSURL);
    }

    class NewsAsyncTask extends AsyncTask<String, Void, List<News>>{

        @Override
        protected List<News> doInBackground(String... url) {
            return QueryUtils.fetchEarthquakeData(url[0]);
        }

        @Override
        protected void onPostExecute(List<News> newsLists) {

            newsAdapter.clear();

            if (newsLists != null && !newsLists.isEmpty()){
                newsAdapter.addAll(newsLists);
            }

        }
    }
}
