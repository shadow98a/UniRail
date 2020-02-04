package com.unirail.Channel;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.unirail.R;

import java.util.ArrayList;
import java.util.Iterator;

public class Channel_get extends Activity {
    public static MyMenu menu = new MyMenu();
    ArrayList<String> items = new ArrayList<>();
    ListViewAdapter     adapter = new ListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_get);

        items.addAll(menu.getMenu("subway"));

        final AutoCompleteTextView edit = (AutoCompleteTextView) findViewById(R.id.searchText);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        edit.setAdapter(arrayAdapter);

        // 메뉴 선택 시 해당 메뉴 Recipe Activity. start()
        edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Intent intent = new Intent(SearchActivity.this, TrafficSubwayInfo.class);
                Intent intent=getIntent();
//                intent.putExtra("OpenAPIKey", WebViewInterface.openAPIKey);
//                intent.putExtra("SubwayLocationAPIKey", WebViewInterface.subwayLocationAPIKey);
//                intent.putExtra("StationNM", edit.getText().toString());
                intent.putExtra("search_keyword", edit.getText().toString());
//                WebViewInterface.mContext.startActivity(intent);

                setResult(RESULT_OK,intent);
                finish();
            }
        });


        ListView list = (ListView)findViewById(R.id.listViewSearch);

        // 메뉴 선택 시 해당 메뉴 Recipe Activity. start()
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);
                String itemTitle = item.getTitle();

//                Intent intent = new Intent(SearchActivity.this, TrafficSubwayInfo.class);
                Intent intent = getIntent();
//                intent.putExtra("OpenAPIKey", WebViewInterface.openAPIKey);
//                intent.putExtra("SubwayLocationAPIKey", WebViewInterface.subwayLocationAPIKey);
//                intent.putExtra("StationNM", itemTitle);
                intent.putExtra("search_keyword",itemTitle);
//                WebViewInterface.mContext.startActivity(intent);

                setResult(RESULT_OK,intent);
                finish();
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


        Button all_problems = (Button)findViewById(R.id.all_problems);

        all_problems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("search_keyword","");
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        Button UniRail = (Button)findViewById(R.id.UniRail);

        UniRail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("search_keyword","유니레일");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}