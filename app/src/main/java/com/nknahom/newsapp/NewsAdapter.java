package com.nknahom.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        // Find the TextView in the news_item.xml layout with the ID webPublicationDate
        TextView webPublicationDate = listItemView.findViewById(R.id.webPublicationDate);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(currentNews.getDate(), inputFormatter);
        String formattedDate = outputFormatter.format(date);

        LocalDate todayDate = LocalDate.now();

        String check = Integer.toString(date.compareTo(todayDate));
        Log.v("Today Date", check);

        if (date.compareTo(todayDate) > 0){

            DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("h", Locale.ENGLISH);

            LocalTime hours = LocalTime.parse(currentNews.getDate(),inputFormatter);
            int checkHour = Integer.parseInt(todayFormatter.format(hours));
            if (checkHour < 1){
                webPublicationDate.setText("Now");
            } else {
                webPublicationDate.setText(hours + " hours ago");
            }
        } else {
            webPublicationDate.setText(formattedDate);
        }


        // Find the TextView in the news_item.xml layout with the ID webTitle
        TextView webTitle = listItemView.findViewById(R.id.webTitle);
        webTitle.setText(currentNews.getTitle());

        return listItemView;
    }
}
