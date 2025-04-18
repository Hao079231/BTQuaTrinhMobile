package com.example.demoasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;

    public MyAsyncTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextParent, "Bắt đầu tiến trình...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(50); // giả lập công việc
                publishProgress(i); // gửi giá trị tiến độ lên UI
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar progressBar = contextParent.findViewById(R.id.prbDemo);
        TextView textView = contextParent.findViewById(R.id.txtStatus);
        int progress = values[0];
        progressBar.setProgress(progress);
        textView.setText(progress + "%");
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        Toast.makeText(contextParent, "Đã hoàn thành!", Toast.LENGTH_SHORT).show();
    }
}
