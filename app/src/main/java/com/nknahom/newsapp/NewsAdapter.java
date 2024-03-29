package com.nknahom.newsapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextView in the news_item.xml layout with the ID sectionName
        TextView sectionName = listItemView.findViewById(R.id.sectionName);
        sectionName.setText(currentNews.getSectionName());

        // Find the TextView in the news_item.xml layout with the ID
        TextView authorName = listItemView.findViewById(R.id.author_name);

        // Checks if the Author's First and Last Names are available
        if (currentNews.getAuthor_first_name() == null && currentNews.getAuthor_last_name() == null){
            authorName.setVisibility(View.GONE);
        } else if (currentNews.getAuthor_first_name() == null){
            authorName.setText("Author: " + currentNews.getAuthor_last_name());
        } else if (currentNews.getAuthor_last_name() == null){
            authorName.setText("Author: " + currentNews.getAuthor_first_name());
        } else {
            authorName.setText("By: " + currentNews.getAuthor_first_name() + " " + currentNews.getAuthor_last_name());
        }

        // Find the TextView in the news_item.xml layout with the ID webPublicationDate
        TextView webPublicationDate = listItemView.findViewById(R.id.webPublicationDate);

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat myResult = new SimpleDateFormat("E dd MMM yyyy HH.mm zzz");
        try {
            Date myDate = myFormat.parse(currentNews.getDate());
            webPublicationDate.setText(myResult.format(myDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Find the TextView in the news_item.xml layout with the ID webTitle
        TextView webTitle = listItemView.findViewById(R.id.webTitle);
        webTitle.setText(currentNews.getTitle());

        return listItemView;
    }
}
