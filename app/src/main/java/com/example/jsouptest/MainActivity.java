package com.example.jsouptest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "https://www.powerball.net/statistics";
    private ProgressDialog progressDialog;
    private ArrayList<String> numberList = new ArrayList<>();
    private ArrayList<String> timesDrawnList = new ArrayList<>();
    private ArrayList<String> lastDrawnList = new ArrayList<>();
    private List<String> period;
    private List<String> tDrawnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Boby is trying jsoup for the first time.");
            progressDialog.setMessage("Loading");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Connect to the website
            try {
                Document document = Jsoup.connect(URL).get();
               // Element x = document.select("li[class=tab is-active]").select("a");
                // Using Element to get the meta data
                Elements elements = document.select("div[class=freq-result js-stats-item]");
                int elementsSize = elements.size();

                for (int i=0; i<=elementsSize; i++)
                {
                    Elements number = document.select("div[class=ball inline]").eq(i);
                    String mNumber = number.text();
                    numberList.add(mNumber);

                    Elements ptimesDrawnList = document.select("strong").eq(i);
                    String mtimesDrawnList = ptimesDrawnList.text();
                    timesDrawnList.add(mtimesDrawnList);

                    //Elements plastDrawnList = document.select()

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            period = numberList.subList(369,438);
            tDrawnList = timesDrawnList.subList(369,438);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            DataAdapter dataAdapter = new DataAdapter(period,tDrawnList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(dataAdapter);
            progressDialog.dismiss();
        }
    }
}
