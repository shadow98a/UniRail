package com.unirail;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
//import kr.go.seoul.trafficsubway.Common.BaseActivity;

public class RecentRoute extends AppCompatActivity  {
    public DBmanager dbmanager;
    public String startStation = "";
    public String finalStation = "";
    public TextView start_station;
    public TextView final_station;
    public TextView middle_station;
    public TextView textTime;
    public TextView start_direction;
    public TextView middle_direction;
    public TextView start_linenum; //몇호선인지
    public TextView middle_linenum; //환승하는 것이 몇호선으로 가야하는지
    public TextView final_linenum; //환승하는 것이 몇호선으로 가야하는지
    public TextView start_raillinklist_num;
    public TextView middle_raillinklist_num;
    public String startLineNum[] = {"","",""};
    public String middleLineNum[] = {"","",""};
    public String finalLineNum[] = {"","",""};
    public String startID[] = {"","",""};
    public String startDirection = "";
    public String startRailLinkListNum = "";
    public String middleRailLinkListNum[] = {"","",""};
    public String time = "";
    public String middleStation[] = {"","","","","",""};
    public int middleNum = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        boolean is_theme_white;
        is_theme_white = extras.getBoolean("is_theme_white");
        if(extras.getBoolean("is_theme_white")==false)
        {
            setContentView(R.layout.recent_route_result);
            is_theme_white = false;
        }
        else
        {
            setContentView(R.layout.recent_route_result_black);
            is_theme_white = true;
        }

        start_station = (TextView)findViewById(R.id.startStation);
        final_station = (TextView)findViewById(R.id.finalStation);
        textTime = (TextView)findViewById(R.id.time);
        start_linenum = (TextView)findViewById(R.id.startLineNum);
        start_raillinklist_num = (TextView)findViewById(R.id.startRailLinkListNum);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        dbmanager = DBmanager.getInstance(getApplicationContext());
        SQLiteDatabase db = dbmanager.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT TIME,STARTSTATION,FINALSTATION,STARTLINENUM,STARTRAILLINKLISTNUM,MIDDLESTATION0,MIDDLESTATION1,MIDDLESTATION2,MIDDLELINENUM0,MIDDLELINENUM1,MIDDLELINENUM2,MIDDLERAILLINKLISTNUM0,MIDDLERAILLINKLISTNUM1,MIDDLERAILLINKLISTNUM2 FROM ROUTESTATION", null);
        //cursor.moveToFirst();
        if (cursor.getCount() == 0){
            System.out.println("없어!!!");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("최근 검색 기록이 없습니다.");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }else{
            System.out.println("있어!!!");
            //System.out.println(cursor.getCount());
        }

        while(!cursor.isAfterLast()){
            time = cursor.getString(0);
            startStation = cursor.getString(1);
            finalStation = cursor.getString(2);
            startLineNum[0] = cursor.getString(3);
            startRailLinkListNum = cursor.getString(4);
            middleStation[0] = cursor.getString(5);
            middleStation[1] = cursor.getString(6);
            middleStation[2] = cursor.getString(7);
            middleLineNum[0] = cursor.getString(8);
            middleLineNum[1] = cursor.getString(9);
            middleLineNum[2] = cursor.getString(10);
            middleRailLinkListNum[0]= cursor.getString(11);
            middleRailLinkListNum[1]= cursor.getString(12);
            middleRailLinkListNum[2]= cursor.getString(13);
            cursor.moveToNext();
        }
        cursor.close();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("final!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(time);
        System.out.println(startStation);
        System.out.println(startLineNum[0]);
        System.out.println(startRailLinkListNum + "개역 이동");
        System.out.println("처음");
        System.out.println(middleStation[0]);
        System.out.println(middleLineNum[0]);
        System.out.println(middleRailLinkListNum[0]);
        System.out.println("두번째");
        System.out.println(middleStation[1]);
        System.out.println(middleLineNum[1]);
        System.out.println(middleRailLinkListNum[1]);
        System.out.println("세번째");
        System.out.println(middleStation[2]);
        System.out.println(middleLineNum[2]);
        System.out.println(middleRailLinkListNum[2]);
        System.out.println(finalStation);

        textTime.setText(time);
        start_station.setText(startStation);
        final_station.setText(finalStation);
        start_linenum.setText(startLineNum[0]);
        startRailLinkListNum = startRailLinkListNum + "개역 이동";
        start_raillinklist_num.setText(startRailLinkListNum);

        middleStationAdapter middleAdapter = new middleStationAdapter(is_theme_white);
        ListView list = (ListView)findViewById(R.id.middleList);
        list.setAdapter(middleAdapter);
        for(int i=0;i<1;i++) {
            if(middleStation[i] != "" || middleStation[i]!= null ) {
                middleAdapter.addItem(middleStation[i]);
                middleAdapter.addItem(middleLineNum[i]);
                middleRailLinkListNum[i] = middleRailLinkListNum[i] + "개역 이동";
                middleAdapter.addItem(middleRailLinkListNum[i]);
            }
        }
        /////////////////////////////////////
        // ListView 크기 조절
        int totalHeight = 0;
        for (int i = 0; i < middleAdapter.getCount(); i++) {
            View listItem = middleAdapter.getView(i, null, list);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = list.getLayoutParams();
        params.height = totalHeight + (list.getDividerHeight() * (middleAdapter.getCount() - 1));
        list.setLayoutParams(params);
        /////////////////////////////////////
    }
}
