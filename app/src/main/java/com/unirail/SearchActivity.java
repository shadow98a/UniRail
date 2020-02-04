package com.unirail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchActivity extends Activity {
    public static MyMenu menu = new MyMenu();
    ArrayList<String> items = new ArrayList<>();
    ListViewAdapter     adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        Bundle extras=getIntent().getExtras();
        boolean is_theme_white;
        String is_start = getIntent().getStringExtra("is_start");

        System.out.println("-----------------------------");
        System.out.println(is_start);

        is_theme_white = extras.getBoolean("is_theme_white");
        adapter = new ListViewAdapter(is_theme_white);
        if(extras.getBoolean("is_theme_white")==false)
        {
            setContentView(R.layout.activity_search);
            is_theme_white = false;
        }
        else
        {
            setContentView(R.layout.activity_search_black);
            is_theme_white = true;
        }

        items.addAll(menu.getMenu("subway"));

//        final AutoCompleteTextView edit = (AutoCompleteTextView) findViewById(R.id.searchText);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
//        edit.setAdapter(arrayAdapter);
//
//        // 메뉴 선택 시 해당 메뉴 Recipe Activity. start()
//        edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(SearchActivity.this, TrafficSubwayInfo.class);
//                intent.putExtra("OpenAPIKey", WebViewInterface.openAPIKey);
//                intent.putExtra("SubwayLocationAPIKey", WebViewInterface.subwayLocationAPIKey);
//                intent.putExtra("StationNM", edit.getText().toString());
//                WebViewInterface.mContext.startActivity(intent);
//                startActivity(intent);
//            }
//        });


        ListView list = (ListView)findViewById(R.id.listViewSearch);

//        // 메뉴 선택 시 해당 메뉴 Recipe Activity. start()
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Bundle extras=getIntent().getExtras();
//                boolean is_theme_white;
//                is_theme_white = extras.getBoolean("is_theme_white");
//
//                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);
//                String itemTitle = item.getTitle();
//                Intent intent = new Intent(SearchActivity.this, TrafficSubwayInfo.class);
//               // intent.putExtra("is_theme_white", is_theme_white);
//                intent.putExtra("boolean-keyword", true);
//                intent.putExtra("OpenAPIKey", WebViewInterface.openAPIKey);
//                intent.putExtra("SubwayLocationAPIKey", WebViewInterface.subwayLocationAPIKey);
//                intent.putExtra("StationNM", itemTitle);
//                intent.putExtra("is_theme_white", is_theme_white);
//                WebViewInterface.mContext.startActivity(intent);
//                startActivity(intent);
//            }
//        });

        // 메뉴 선택 시 해당 메뉴 Recipe Activity. start()
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras=getIntent().getExtras();
                boolean is_theme_white;

                is_theme_white = extras.getBoolean("is_theme_white");
                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);
                String itemTitle = item.getTitle();
                Intent intent = new Intent(SearchActivity.this, searchRoute.class);
                // intent.putExtra("is_theme_white", is_theme_white);
                intent.putExtra("boolean-keyword", true);
//                intent.putExtra("OpenAPIKey", WebViewInterface.openAPIKey);
//                intent.putExtra("SubwayLocationAPIKey", WebViewInterface.subwayLocationAPIKey);
                intent.putExtra("StationNM", itemTitle);
                intent.putExtra("is_theme_white", is_theme_white);

//                WebViewInterface.mContext.startActivity(intent);
                startActivity(intent);
            }
        });

        list.setAdapter(adapter);

        Iterator<String> iterator = items.iterator();

        // 모든 메뉴를 리스트에 동적 추가
        while(iterator.hasNext()) {

            String menuTitle = iterator.next();
            Resources res = getBaseContext().getResources();

            int drawableId = res.getIdentifier(menuTitle.toLowerCase(), "drawable"
                    , getBaseContext().getPackageName());

            adapter.addItem(menuTitle);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}