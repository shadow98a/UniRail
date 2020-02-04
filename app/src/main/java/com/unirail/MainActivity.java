package com.unirail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unirail.Channel.Channel_main;

//import kr.go.seoul.trafficsubway.Common.BaseActivity;

public class MainActivity extends AppCompatActivity {

    boolean is_theme_white = false;
    private DBmanager db;
    private Button black_theme;
    private Button white_theme;
    private Button search_route;
    private Button start_activity;
    private RelativeLayout Rlayout;
    private Button station_info;
    private Button recent_search;
    public TextView 설명;
    static  final int GET_STRING = 1;

    private TextView textmain;
    public static Context mContext;

    public MainActivity()
    {
        openAPIKey = "71536a5044736861373274514e706b";
        subwayLocationAPIKey = "71536a5044736861373274514e706b";

    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = new DBmanager(this.getApplicationContext(),"routestation.db",null,1);
        setContentView(R.layout.activity_main);
        Rlayout = findViewById(R.id.Rlayout);
        black_theme = findViewById(R.id.black_theme);
        white_theme = findViewById(R.id.white_theme);
        search_route = findViewById(R.id.search_route);
        textmain = findViewById(R.id.textmain);
        설명 = findViewById(R.id.설명);
        start_activity=findViewById(R.id.start_activity);
        station_info = findViewById(R.id.station_info);
        ///////////////
        recent_search = findViewById(R.id.recent_search);
        recent_search.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(MainActivity.this,RecentRoute.class);
                        intent.putExtra("is_theme_white", is_theme_white);
                        startActivity(intent);
                    }
                }
        );
        /////////////////

        search_route.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(MainActivity.this,searchRoute.class);
                        intent.putExtra("is_theme_white", is_theme_white);
                        startActivity(intent);
                    }
                }
        );

        station_info.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(MainActivity.this, SearchMap.class);
                        intent.putExtra("is_theme_white", is_theme_white);
                        startActivity(intent);
                    }
                }
        );

        black_theme.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                is_theme_white = true;
                                black_theme.setBackgroundColor(Color.BLACK);
                                white_theme.setBackgroundColor(Color.BLACK);
                                textmain.setTextColor(Color.BLACK);
                                textmain.setBackgroundColor(Color.rgb(187,226,251));
                                search_route.setBackgroundColor(Color.BLACK);
                                search_route.setTextColor(Color.WHITE);
                                Rlayout.setBackgroundColor(Color.rgb(187,226,251));
                                black_theme.setTextColor(Color.WHITE);
                                white_theme.setTextColor(Color.WHITE);
//                                start_activity.setTextColor(Color.WHITE);
                                //                               start_activity.setBackgroundColor(Color.BLACK);
                                station_info.setBackgroundColor(Color.BLACK);
                                station_info.setTextColor(Color.WHITE);
                                recent_search.setBackgroundColor(Color.BLACK);
                                recent_search.setTextColor(Color.WHITE);
                                설명.setTextColor(Color.WHITE);
                                설명.setBackgroundColor(Color.BLACK);
//                                mWebViewInterface.changeTheme(is_theme_white);
                            }
                        }
                );
        white_theme.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                is_theme_white =false;
                                black_theme.setBackgroundColor(Color.WHITE);
                                white_theme.setBackgroundColor(Color.WHITE);
                                textmain.setTextColor(Color.BLACK);
                                textmain.setBackgroundColor(Color.rgb(187,226,251));
                                search_route.setBackgroundColor(Color.WHITE);
                                search_route.setTextColor(Color.BLACK);
                                Rlayout.setBackgroundColor(Color.rgb(187,226,251));
                                black_theme.setTextColor(Color.BLACK);
                                white_theme.setTextColor(Color.BLACK);
//                                start_activity.setTextColor(Color.BLACK);
//                                start_activity.setBackgroundColor(Color.WHITE);
                                station_info.setBackgroundColor(Color.WHITE);
                                station_info.setTextColor(Color.BLACK);
                                recent_search.setBackgroundColor(Color.WHITE);
                                recent_search.setTextColor(Color.BLACK);
                                설명.setTextColor(Color.BLACK);
                                설명.setBackgroundColor(Color.WHITE);
//                                mWebViewInterface.changeTheme(is_theme_white);
                            }
                        }
                );
        mContext = this;
        textmain = findViewById(R.id.textmain);
        Button button = (Button)findViewById(R.id.search_route);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, searchRoute.class);
                intent.putExtra("is_theme_white", is_theme_white);
                startActivityForResult(intent, GET_STRING);

            }
        });

        if(getIntent() != null && getIntent().getStringExtra("OpenAPIKey") != null)
            openAPIKey = getIntent().getStringExtra("OpenAPIKey");
        if(getIntent() != null && getIntent().getStringExtra("SubwayLocationAPIKey") != null)
            subwayLocationAPIKey = getIntent().getStringExtra("SubwayLocationAPIKey");
        //initView();

        start_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra("boolean-keyword", true);

                startActivity(intent);
            }
        });

        findViewById(R.id.channel).setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                startActivity(new Intent(MainActivity.this, Channel_main.class));
                            }
                        }
                );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == GET_STRING){
            if(resultCode == RESULT_OK){
                textmain.setText(data.getStringExtra("INPUT_TEXT"));
            }
        }
    }

    private void initView()
    {
        if(is_theme_white==false)
        {
            System.out.println("false=하양1");
        }
        else {
            System.out.println("true=검정1");
        }
//        btnBackSubway = (ImageView)findViewById(R.id.btn_back_subway);
//        btnBackSubway.setOnClickListener(new View.OnClickListener()
//                                         {
//
//                                             public void onClick(View view)
//                                             {
//                                                 System.out.println(is_theme_white);
//                                                 finish();
//                                             }
//
//                                             final MainActivity this$0;
//
//                                             {
//                                                 this.this$0 = MainActivity.this;
//                                                 // super();
//                                             }
//                                         }
//        );
//        lineMapWebview = (WebView)findViewById(R.id.line_map_webview);
//        lineMapWebview.setWebViewClient(new WebViewClient());
//        lineMapWebview.getSettings().setJavaScriptEnabled(true);
//        lineMapWebview.getSettings().setBuiltInZoomControls(true);
//        lineMapWebview.getSettings().setSupportZoom(true);
//        lineMapWebview.getSettings().setDisplayZoomControls(false);
//        lineMapWebview.getSettings().setDefaultTextEncodingName("UTF-8");
//        mWebViewInterface = new WebViewInterface(this, lineMapWebview, openAPIKey, subwayLocationAPIKey);
//        lineMapWebview.addJavascriptInterface(mWebViewInterface, "Android");
//        lineMapWebview.loadUrl("file:///android_asset/mSeoul_Subway.html");
//        // Intent intent = new Intent(MainActivity.this, WebViewInterface.class);
//        // intent.putExtra("is_theme_white", is_theme_white);
    }


    private String openAPIKey;
    private String subwayLocationAPIKey;
    private ImageView btnBackSubway;
//    private WebView lineMapWebview;
//    private WebViewInterface mWebViewInterface;
}

