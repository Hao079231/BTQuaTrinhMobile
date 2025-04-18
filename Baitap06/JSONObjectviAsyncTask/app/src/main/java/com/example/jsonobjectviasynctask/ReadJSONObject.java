package com.example.jsonobjectviasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadJSONObject extends AsyncTask<String, Void, String> {

    private TextView resultTextView;

    public ReadJSONObject(TextView resultTextView) {
        this.resultTextView = resultTextView;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("ReadJSONObject", "Dữ liệu JSON: " + s);  // Kiểm tra log

        try {
            JSONArray array = new JSONArray(s);
            StringBuilder result = new StringBuilder();

            // Lấy số phần tử tối đa là 5, nếu mảng có ít hơn 5 thì chỉ lấy phần tử của mảng
            int maxItems = Math.min(array.length(), 5);
            for (int i = 0; i < maxItems; i++) {
                JSONObject todo = array.getJSONObject(i);
                int userId = todo.getInt("userId");
                int id = todo.getInt("id");
                String title = todo.getString("title");
                boolean completed = todo.getBoolean("completed");

                result.append("User ID: ").append(userId)
                        .append("\nID: ").append(id)
                        .append("\nTitle: ").append(title)
                        .append("\nCompleted: ").append(completed)
                        .append("\n\n");
            }

            resultTextView.setText(result.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
