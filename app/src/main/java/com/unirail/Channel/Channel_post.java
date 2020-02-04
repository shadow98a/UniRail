package com.unirail.Channel;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.unirail.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Channel_post extends AppCompatActivity {

//    private static String IP_ADDRESS = "IP주소";
    private static String IP_ADDRESS = "175.193.19.34";
    private static String TAG = "phptest";

//    private EditText mEditTextName;
    private TextView object;
//    private EditText mEditTextCountry;
    private EditText problem;
//    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_channel_post);

//        mEditTextName = (EditText)findViewById(R.id.editText_main_name);
        object = (TextView)findViewById(R.id.object);
//        mEditTextCountry = (EditText)findViewById(R.id.editText_main_country);
        problem = (EditText) findViewById(R.id.problem);
//        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

//        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        object.setText(getIntent().getStringExtra("object"));


        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String name = mEditTextName.getText().toString();
                String object = Channel_post.this.object.getText().toString();
//                String country = mEditTextCountry.getText().toString();
                String problem = Channel_post.this.problem.getText().toString();

                InsertData task = new InsertData();
//                task.execute("http://" + IP_ADDRESS + "/insert.php", name,country);
                task.execute("http://" + IP_ADDRESS + "/post_problem.php", object,problem);


//                mEditTextName.setText("");
//                mEditTextCountry.setText("");

                setResult(RESULT_OK);
                finish();
            }
        });

    }



    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            progressDialog = ProgressDialog.show(MainActivity.this,
            progressDialog = ProgressDialog.show(Channel_post.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
//            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

//            String name = (String)params[1];
//            String country = (String)params[2];
            String object = (String)params[1];
            String problem = (String)params[2];

            String serverURL = (String)params[0];
//            String postParameters = "name=" + name + "&country=" + country;
            String postParameters = "object=" + object + "&problem=" + problem;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

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
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


}