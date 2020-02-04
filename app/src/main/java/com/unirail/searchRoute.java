package com.unirail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
//import kr.go.seoul.trafficsubway.Common.BaseActivity;

public class searchRoute extends AppCompatActivity {

    boolean is_theme_white = true;

    private Button final_search;
    private Button start_search;
    private Button search;
    private TextView start_station;
    private TextView final_station;
    static  final int GET_START = 0;
    static final int GET_DESTINATION=1;
    private TextView textmain;
    public static Context mContext;
    RouteStation r;

    public searchRoute()
    {
        openAPIKey = "70575677706d696333327152507752";
        subwayLocationAPIKey = "70575677706d696333327152507752";

    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();

        is_theme_white = extras.getBoolean("is_theme_white");
        if(extras.getBoolean("is_theme_white")==false)
        {
            setContentView(R.layout.search_route);
            is_theme_white = false;
        }
        else
        {
            setContentView(R.layout.search_route_black);
            is_theme_white = true;
        }

        start_search = findViewById(R.id.start_search);
        final_search = findViewById(R.id.start_search);
        start_station = findViewById(R.id.start_station);
        final_station = findViewById(R.id.final_station);
        search = findViewById(R.id.search);
        r = new RouteStation();
        start_station.setText(r.getStartStation());
        final_station.setText(r.getFinalStation());

        String stationNM = "";
        if(getIntent() != null && getIntent().getStringExtra("OpenAPIKey") != null)
            openAPIKey = getIntent().getStringExtra("OpenAPIKey");
        if(getIntent() != null && getIntent().getStringExtra("SubwayLocationAPIKey") != null)
            subwayLocationAPIKey = getIntent().getStringExtra("SubwayLocationAPIKey");

        if(getIntent() != null && getIntent().getStringExtra("start") != null) {
            r.setStartStation(getIntent().getStringExtra("start"));
            start_station.setText(r.getStartStation());
        }
        if(getIntent() != null && getIntent().getStringExtra("final") != null) {
            r.setFinalStation(getIntent().getStringExtra("final"));
            final_station.setText(r.getFinalStation());
        }

        if(getIntent().getBooleanExtra("is_start",false) == true ) {
            stationNM = getIntent().getStringExtra("StationNM");
            r.setStartStation(stationNM);
            start_station.setText(r.getStartStation());
        }else {
            stationNM = getIntent().getStringExtra("StationNM");
            r.setFinalStation(stationNM);
            final_station.setText(r.getFinalStation());
        }
//        initView();

        start_search.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(searchRoute.this,StartSearchActivity.class);
                        intent.putExtra("is_theme_white", is_theme_white);
                        intent.putExtra("start",start_station.getText());
                        intent.putExtra("final",final_station.getText());
                        startActivity(intent);
                    }
            }
        );

        final_search.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(searchRoute.this,FinalSearchActivity.class);
                        intent.putExtra("is_theme_white", is_theme_white);
                        intent.putExtra("start",start_station.getText());
                        intent.putExtra("final",final_station.getText());
                        startActivity(intent);
                    }
                }
        );

        search.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(searchRoute.this,SearchRouteResult.class);
                        intent.putExtra("is_theme_white", is_theme_white);
                        intent.putExtra("startStation",start_station.getText().toString());
                        intent.putExtra("finalStation",final_station.getText().toString());
                        startActivity(intent);
                    }
                }
        );

        mContext = this;
        textmain = findViewById(R.id.textmain);
        Button start_button = (Button)findViewById(R.id.start_search);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(searchRoute.this, StartSearchActivity.class);
                intent.putExtra("is_theme_white", is_theme_white);
                startActivityForResult(intent, GET_START);
            }
        });

        Button final_button = (Button)findViewById(R.id.final_search);
        final_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(searchRoute.this, FinalSearchActivity.class);
                intent.putExtra("is_theme_white", is_theme_white);
                startActivityForResult(intent, GET_DESTINATION);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == GET_START){
            if(resultCode == RESULT_OK){
                start_station.setText(data.getExtras().getString("INPUT_TEXT"));
            }
        }
        else
        {
            if(resultCode == RESULT_OK){
                final_station.setText(data.getExtras().getString("INPUT_TEXT"));
            }
        }
    }

//    private void initView()
//    {
//        btnBackSubway = (ImageView)findViewById(R.id.btn_back_subway);
//        btnBackSubway.setOnClickListener(new View.OnClickListener()
//                                         {
//
//                                             public void onClick(View view)
//                                             {
//                                                 finish();
//                                             }
//                                             final searchRoute this$0;
//                                             {
//                                                 this.this$0 = searchRoute.this;
//                                             }
//                                         }
//        );
//
//    }


    private String openAPIKey;
    private String subwayLocationAPIKey;
    private ImageView btnBackSubway;

}

