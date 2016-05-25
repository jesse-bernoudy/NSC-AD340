package com.example.jesse.threading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    CountingTask mCountingTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.myTextView);
        Random r = new Random();

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < 10000; ++i) {
           list.add(r.nextInt(100000)+1);
        }

        mCountingTask = new CountingTask();
        mCountingTask.execute(list.toArray(new Integer[list.size()]));
    }

    private class CountingTask extends AsyncTask<Integer, Void, Integer[]> {

        @Override
        protected Integer[] doInBackground(Integer... params) {
            java.util.Arrays.sort(params, Collections.<Integer>reverseOrder());
            return params;
        }

        protected void onProgressUpdate(Void... voids) {
        }

        protected void onPostExecute(Integer[] results) {
            mTextView.setText(java.util.Arrays.toString(results));
        }

    }
}
