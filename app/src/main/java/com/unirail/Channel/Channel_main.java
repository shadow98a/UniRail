package com.unirail.Channel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.unirail.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class Channel_main extends AppCompatActivity {

    private static String TAG = "phpquerytest";

//    private static final String TAG_JSON="webnautes";
    private static final String TAG_JSON="problems";
//    private static final String TAG_ID = "id";
//    private static final String TAG_NAME = "name";
//    private static final String TAG_ADDRESS ="country";
    private static final String TAG_OBJECT = "object";
    private static final String TAG_TIME = "time";
    private static final String TAG_PROBLEM = "problem";

    static private final int get_request_code = 0;
    static private final int post_request_code = 1;

//    private TextView mTextViewResult;
//    ArrayList<HashMap<String, String>> mArrayList;
    ArrayList<HashMap<String, String>> problems;
//    ListView mListViewList;
    ListView list_view;
//    EditText mEditTextSearchKeyword1, mEditTextSearchKeyword2;
    EditText search_keyword;
//    String mJsonString;
    String JSON;

    Button post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_main);

//        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
//        mListViewList = (ListView) findViewById(R.id.listView_main_list);
        list_view = (ListView) findViewById(R.id.problems);
//        mEditTextSearchKeyword1 = (EditText) findViewById(R.id.editText_main_searchKeyword1);
//        mEditTextSearchKeyword2 = (EditText) findViewById(R.id.editText_main_searchKeyword2);
        search_keyword = (EditText) findViewById(R.id.search_keyword);
        post=findViewById(R.id.post);


//        Button button_search = (Button) findViewById(R.id.button_main_search);
//        button_search.setOnClickListener(new View.OnClickListener() {
        search_keyword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                mArrayList.clear();
//
//
//                GetData task = new GetData();
//                task.execute( mEditTextSearchKeyword1.getText().toString(), mEditTextSearchKeyword2.getText().toString());
                final Intent intent=new Intent(Channel_main.this, Channel_get.class);

                startActivityForResult(intent,get_request_code);
            }
        });

        post.setVisibility(View.INVISIBLE);
        post.setActivated(false);
        post.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                final Intent intent=new Intent(Channel_main.this, Channel_post.class);
                                intent.putExtra("object",search_keyword.getText().toString());
                                startActivityForResult(intent,post_request_code);
                            }
                        }
                );


//        mArrayList = new ArrayList<>();
        problems = new ArrayList<>();

        request("");
    }


    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Channel_main.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
//            mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

//            if (result == null){
//
//                mTextViewResult.setText(errorString);
//            }
//            else {

//                mJsonString = result;
                JSON = result;
                showResult();
//            }
        }


        @Override
        protected String doInBackground(String... params) {

//            String searchKeyword1 = params[0];
//            String searchKeyword2 = params[1];
            String search_keyword = params[0];

//            String serverURL = "http://서버IP/query.php";
            String serverURL = "http://175.193.19.34/get_problems.php";
//            String postParameters = "country=" + searchKeyword1 + "&name=" + searchKeyword2;
            String postParameters = "object=" + search_keyword;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
//            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONObject jsonObject = new JSONObject(JSON);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

//                String id = item.getString(TAG_ID);
//                String name = item.getString(TAG_NAME);
//                String address = item.getString(TAG_ADDRESS);
                String object = item.getString(TAG_OBJECT);
                String time = item.getString(TAG_TIME);
                String problem = item.getString(TAG_PROBLEM);


                HashMap<String, String> hashMap = new HashMap<>();

//                hashMap.put(TAG_ID, id);
//                hashMap.put(TAG_NAME, name);
//                hashMap.put(TAG_ADDRESS, address);
                hashMap.put(TAG_OBJECT, object);
                hashMap.put(TAG_TIME, time);
                hashMap.put(TAG_PROBLEM, problem);

//                mArrayList.add(hashMap);
                problems.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
//                    Channel_main.this, mArrayList, R.layout.item_list,
//                    new String[]{TAG_ID,TAG_NAME, TAG_ADDRESS},
//                    new int[]{R.id.textView_list_id, R.id.textView_list_name, R.id.textView_list_address}
                    Channel_main.this, problems, R.layout.problem,
                    new String[]{TAG_OBJECT,TAG_TIME, TAG_PROBLEM},
                    new int[]{R.id.object, R.id.time, R.id.problem}
            );

//            mListViewList.setAdapter(adapter);
            list_view.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    private void request(final String search_keyword)
    {
        problems.clear();

        GetData task = new GetData();
        task.execute(search_keyword);
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, @Nullable Intent intent)
    {
        super.onActivityResult(request_code, result_code, intent);

        if(result_code==RESULT_OK)
        {
            if(request_code==get_request_code)
            {
                final String object=intent.getStringExtra("search_keyword");
                search_keyword.setText(object);
                if(object.equals("")==false)
                {
                    post.setVisibility(View.VISIBLE);
                    post.setActivated(true);
                }
                else
                {
                    post.setVisibility(View.INVISIBLE);
                    post.setActivated(false);
                }
            }
        }
        request(search_keyword.getText().toString());
    }
}