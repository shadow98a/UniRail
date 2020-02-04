package com.unirail;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.unirail.Navigator.Navigator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;

//import kr.go.seoul.trafficsubway.Common.BaseActivity;

public class SearchRouteResult extends AppCompatActivity {
    public DBmanager dbmanager;
    public String openAPIKey = "70575677706d696333327152507752";
    public String subwayLocationAPIKey = "70575677706d696333327152507752";
    public String startStation = "";
    public String finalStation = "";
    public TextView start_station;
    public TextView final_station;
    public TextView middle_station;
    public TextView textTime;
    public TextView textmain;
    public TextView start_direction;
    public TextView middle_direction;
    public TextView start_linenum; //몇호선인지
    public TextView middle_linenum; //환승하는 것이 몇호선으로 가야하는지
    public TextView final_linenum; //환승하는 것이 몇호선으로 가야하는지
    public TextView start_raillinklist_num;
    public TextView middle_raillinklist_num;
    public String startX = "";
    public String startY = "";
    public String finalX = "";
    public String finalY = "";
    public String startCode[] = {"","",""}; //최종 경로의 역코드는 startCode[0]
    public String finalCode[] = {"","",""};
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
    private Button navigate;

    private final ArrayList<Navigator.argument_for_naviation> arguments=new ArrayList<Navigator.argument_for_naviation>();  //+beta 1.2 build 0127
    private Navigator.argument_for_naviation argument;                                        //+beta 1.2 build 0127

    private Navigator Navigator;                                                                //+beta 1.2 build 0127
    private final ServiceConnection connection = new ServiceConnection()                            //+beta 1.2 build 0127
    {                                                                                               //+beta 1.2 build 0127
        @Override                                                                                   //+beta 1.2 build 0127
        public void onServiceConnected(ComponentName name, IBinder binder)                          //+beta 1.2 build 0127
        {                                                                                           //+beta 1.2 build 0127
            Navigator = ((Navigator.binder) binder).get_Navigator();                              //+beta 1.2 build 0127
        }                                                                                           //+beta 1.2 build 0127

        @Override                                                                                   //+beta 1.2 build 0127
        public void onServiceDisconnected(ComponentName name)                                       //+beta 1.2 build 0127
        {                                                                                           //+beta 1.2 build 0127
        }                                                                                           //+beta 1.2 build 0127
    };                                                                                              //+beta 1.2 build 0127

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        boolean is_theme_white;
        is_theme_white = extras.getBoolean("is_theme_white");
        if(extras.getBoolean("is_theme_white")==false)
        {
            setContentView(R.layout.search_route_result);
            is_theme_white = false;
        }
        else
        {
            setContentView(R.layout.search_route_result_black);
            is_theme_white = true;
        }

        start_station = (TextView)findViewById(R.id.startStation);
        //middle_station = (TextView)findViewById(R.id.middleStation);
        final_station = (TextView)findViewById(R.id.finalStation);
        textTime = (TextView)findViewById(R.id.time);
        start_linenum = (TextView)findViewById(R.id.startLineNum);
        //middle_linenum = (TextView)findViewById(R.id.middleLineNum);
        //final_linenum = (TextView)findViewById(R.id.finalLineNum);
        start_raillinklist_num = (TextView)findViewById(R.id.startRailLinkListNum);
        //middle_raillinklist_num = (TextView)findViewById(R.id.middleRailLinkListNum);
        navigate=findViewById(R.id.navigate);

        if(this.getIntent() != null && this.getIntent().getStringExtra("OpenAPIKey") != null) {
            this.openAPIKey = this.getIntent().getStringExtra("OpenAPIKey");
        }

        if(this.getIntent() != null && this.getIntent().getStringExtra("SubwayLocationAPIKey") != null) {
            this.subwayLocationAPIKey = this.getIntent().getStringExtra("SubwayLocationAPIKey");
        }

        if(this.getIntent() != null && this.getIntent().getStringExtra("startStation") != null) {
            this.startStation = this.getIntent().getStringExtra("startStation");
        }

        if(this.getIntent() != null && this.getIntent().getStringExtra("finalStation") != null) {
            this.finalStation = this.getIntent().getStringExtra("finalStation");
        }

        System.out.println("--------------------------");
        System.out.println(this.startStation);
        System.out.println(this.finalStation);

        openAddressAPI addressAPI = new openAddressAPI(); //역에 따른 주소를 넘겨주는 api
        try {
            String str = addressAPI.execute(startStation,finalStation).get();
            System.out.println("--------------역코드------------------");
            String[] array = str.split("/");
            startCode[0] = array[0];
            finalCode[0] = array[1];
            System.out.println(startCode[0]);
            System.out.println(finalCode[0]);
        }catch(Exception e){
            e.printStackTrace();
        }

        wgsAddressAPI wgsApi = new wgsAddressAPI();
        try {
            String str = wgsApi.execute(startCode[0],finalCode[0]).get();
            System.out.println("-----------------좌표--------------------");
            String[] array = str.split("/");
            startX = array[0];
            startY = array[1];
            finalX = array[2];
            finalY = array[3];
            System.out.println("startX = " + startX);
            System.out.println("startY = " + startY);
            System.out.println("finalX = " + finalX);
            System.out.println("finalY = " + finalY);
        }catch(Exception e){
            e.printStackTrace();
        }

        openAPI subwayApi = new openAPI(); //받은 주소로 경로를 탐색하는 api
        try {
            String str = subwayApi.execute(startX,startY,finalX,finalY).get();
            System.out.println(str);
        }catch(Exception e){
            e.printStackTrace();
        }

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
        System.out.println(finalStation);

        textTime.setText(time);
        if(! startStation.contains("역")) {
            startStation = startStation + "역";
        }
        start_station.setText(startStation);
        //middle_station.setText(middleStation[0]);
        final_station.setText(finalStation);
        start_linenum.setText(startLineNum[0]);
        //middle_linenum.setText(middleLineNum[0]);
        startRailLinkListNum = startRailLinkListNum + "개역 이동";
        start_raillinklist_num.setText(startRailLinkListNum);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        dbmanager = DBmanager.getInstance(getApplicationContext());
        SQLiteDatabase db = dbmanager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("STARTSTATION",startStation);
        values.put("TIME",time);
        values.put("FINALSTATION",finalStation);
        values.put("STARTLINENUM",startLineNum[0]);
        values.put("STARTRAILLINKLISTNUM",startRailLinkListNum);
        values.put("MIDDLESTATION0",middleStation[0]);
        values.put("MIDDLESTATION1",middleStation[1]);
        values.put("MIDDLESTATION2",middleStation[2]);
        values.put("MIDDLELINENUM0",middleLineNum[0]);
        values.put("MIDDLELINENUM1",middleLineNum[1]);
        values.put("MIDDLELINENUM2",middleLineNum[2]);
        values.put("MIDDLERAILLINKLISTNUM0",middleRailLinkListNum[0]);
        values.put("MIDDLERAILLINKLISTNUM1",middleRailLinkListNum[1]);
        values.put("MIDDLERAILLINKLISTNUM2",middleRailLinkListNum[2]);
        db.insert("ROUTESTATION",null,values);
        db.close();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        middleStationAdapter middleAdapter = new middleStationAdapter(is_theme_white);
        ListView list = (ListView)findViewById(R.id.middleList);
        list.setAdapter(middleAdapter);
        for(int i=0;i<3;i++) {
            if(middleStation[i] != "") {
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

        bindService(new Intent(this, Navigator.class), connection, Context.BIND_AUTO_CREATE);

        navigate.setOnClickListener
                (
                        new View.OnClickListener()                                                      //+beta 1.2 build 0127
                        {                                                                               //+beta 1.2 build 0127
                            @Override                                                                   //+beta 1.2 build 0127
                            public void onClick(View view)                                              //+beta 1.2 build 0127
                            {
                                Navigator.navigate((Navigator.argument_for_naviation[])arguments.toArray(new Navigator.argument_for_naviation[arguments.size()]));
                            }
                        }
                );

    }

    String findDirection(String stationNM){
        findDirectionApi findApi = new findDirectionApi();
        String str = "";
        try {
            str = findApi.execute(startStation).get();
            System.out.println("-----------------direction--------------------");
            System.out.println(str);
        }catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }

    String[] findId(String stationNM){
        findIdApi idApi = new findIdApi();
        String id[] = {"","",""};
        try {
            String str = idApi.execute(stationNM).get();
            System.out.println("-----------------id--------------------");
            String[] array = str.split("/");
            int i=2;
            int same = 0;
            int x = 1;
            id[0] = array[1];
            while(i < 3 ){
                same = 0;
                for(int j=0;j<i;j++){
                    if(id[j] == array[i]){
                        same = 1;
                    }
                }
                if(same == 0) {
                    id[x] = array[i];
                    x++;
                }
                i++;
            }
            for(int j=0;j<3;j++){
                if(id[j] != "") {
                    System.out.println(id[j]);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }

    String findSubwayId(String subwayNM){
        //몇호선인지를 넣으면 1001이런 subwayid를 알려주는 함수
        if((subwayNM.equals("1호선") == true)){
            return "1001";
        }else if((subwayNM.equals("2호선") == true)){
            return "1002";
        }else if((subwayNM.equals("3호선") == true)){
            return "1003";
        }else if((subwayNM.equals("4호선") == true)){
            return "1004";
        }else if((subwayNM.equals("5호선") == true)){
            return "1005";
        }else if((subwayNM.equals("6호선") == true)){
            return "1006";
        }else if((subwayNM.equals("7호선") == true)){
            return "1007";
        }else if((subwayNM.equals("8호선") == true)){
            return "1008";
        }else if((subwayNM.equals("9호선") == true)){
            return "1009";
        }
        return "";
    }


    public class wgsAddressAPI extends AsyncTask<String, Void, String> {
        URL startUrl = null;
        protected String doInBackground(String... strings) {
            String s = this.executeClient(strings);
            System.out.println(s);
            return s;
        }

        String executeClient(String[] str) {

            StringBuffer buffer = new StringBuffer();
            try {
                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/70575677706d696333327152507752/xml/SearchLocationOfSTNByIDService/1/5/");
                urlBuilder.append(URLEncoder.encode(str[0], "UTF-8") + "/"); //출발지
                startUrl = new URL(urlBuilder.toString());
                InputStream is= startUrl.openStream(); //url위치로 입력스트림 연결
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                XmlPullParser xpp= factory.newPullParser();
                xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
                String tag;
                xpp.next();
                int eventType= xpp.getEventType();
                int find = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(find == 1){break;}
                    switch( eventType ){
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("row")) ;// 첫번째 검색결과
                            else if (tag.equals("XPOINT_WGS")) {
                                xpp.next();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }else if (tag.equals("YPOINT_WGS")) {
                                xpp.next();
                                buffer.append("/");
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                find = 1;
                                break;
                            }
                            break;
                    }
                    eventType = xpp.next();
                }

                urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/70575677706d696333327152507752/xml/SearchLocationOfSTNByIDService/1/5/");
                urlBuilder.append(URLEncoder.encode(str[1], "UTF-8") + "/"); //출발지
                startUrl = new URL(urlBuilder.toString());
                is= startUrl.openStream(); //url위치로 입력스트림 연결
                factory= XmlPullParserFactory.newInstance();
                xpp= factory.newPullParser();
                xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
                xpp.next();
                eventType= xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch( eventType ){
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("row")) ;// 첫번째 검색결과
                            else if (tag.equals("XPOINT_WGS")) {
                                xpp.next();
                                buffer.append("/");
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }else if (tag.equals("YPOINT_WGS")) {
                                xpp.next();
                                buffer.append("/");
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
                return buffer.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
    }

    public class openAddressAPI extends AsyncTask<String, String, String> { //역코드와 라인번호
        URL startUrl = null;
        URL finalUrl = null;
        protected String doInBackground(String... strings) {
            String s = this.executeClient(strings);
            System.out.println(s);
            return s;
        }

        String executeClient(String[] str) {

            StringBuffer buffer = new StringBuffer();

            try {
                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/70575677706d696333327152507752/xml/SearchSTNBySubwayLineInfo/1/5/ /");
                urlBuilder.append(URLEncoder.encode(str[0], "UTF-8")); //출발지
                startUrl = new URL(urlBuilder.toString());
                InputStream is= startUrl.openStream(); //url위치로 입력스트림 연결
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                XmlPullParser xpp= factory.newPullParser();
                xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
                String tag;
                xpp.next();
                int eventType= xpp.getEventType();
                int i=0;
                while (eventType != XmlPullParser.END_DOCUMENT) { ;
                    switch( eventType ){
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("row")) ;// 첫번째 검색결과
                            else if (tag.equals("STATION_CD")) {
                                buffer = new StringBuffer();
                                xpp.next();
                                startCode[i] = xpp.getText();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }
                            else if (tag.equals("LINE_NUM")) {
                                xpp.next();
                                String s = xpp.getText().substring(1);
                                startLineNum[i] = s;
                                //buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }
                            break;
                    }
                    eventType = xpp.next();
                }

                StringBuffer finalbuffer = new StringBuffer();
                urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/70575677706d696333327152507752/xml/SearchSTNBySubwayLineInfo/1/5/ /");
                urlBuilder.append(URLEncoder.encode(str[1], "UTF-8")); //출발지
                startUrl = new URL(urlBuilder.toString());
                is= startUrl.openStream(); //url위치로 입력스트림 연결
                factory= XmlPullParserFactory.newInstance();
                xpp= factory.newPullParser();
                xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
                xpp.next();
                eventType= xpp.getEventType();
                i=0;
                while (eventType != XmlPullParser.END_DOCUMENT) { ;
                    switch( eventType ){
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("row")) ;// 첫번째 검색결과
                            else if (tag.equals("STATION_CD")) {
                                finalbuffer = new StringBuffer();
                                xpp.next();
                                finalCode[i] = xpp.getText();
                                finalbuffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }
                            else if (tag.equals("LINE_NUM")) {
                                xpp.next();
                                finalLineNum[i] = xpp.getText();
                                break;
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
                buffer.append("/");
                buffer.append(finalbuffer.toString());
                return buffer.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
    }

    public class openAPI extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();
            URL url;
            try {
                //url = new URL("http://ws.bus.go.kr/api/rest/pathinfo/getPathInfoBySubway?ServiceKey=FgYWtyQY6EGgb5Yl4%2B1jT5cmRUYrK1Ht%2BetulrZ0YCKnSCoh%2FzgAXkh8r3ukrvo6Qpheo7ruYP5TMNJE5XA8PA%3D%3D&startX=126.83948388112836&startY=37.558210971753226&endX=127.01460762172958&endY=37.57250");
                ////
                StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/pathinfo/getPathInfoBySubway");
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=FgYWtyQY6EGgb5Yl4%2B1jT5cmRUYrK1Ht%2BetulrZ0YCKnSCoh%2FzgAXkh8r3ukrvo6Qpheo7ruYP5TMNJE5XA8PA%3D%3D"); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("startX","UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8")); /*출발지X좌표*/
                urlBuilder.append("&" + URLEncoder.encode("startY","UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8")); /*출발지Y좌표*/
                urlBuilder.append("&" + URLEncoder.encode("endX","UTF-8") + "=" + URLEncoder.encode(strings[3], "UTF-8")); /*목적지X좌표*/
                urlBuilder.append("&" + URLEncoder.encode("endY","UTF-8") + "=" + URLEncoder.encode(strings[2], "UTF-8")); /*목적지Y좌표*/
                url = new URL(urlBuilder.toString());
                ////
                InputStream is = url.openStream(); //url위치로 입력스트림 연결
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
                String tag;
                xpp.next();
                int eventType = xpp.getEventType();
                int find = 0;
                int start = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (find == 1) {
                        break;
                    }
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            buffer.append("파싱 시작...\n\n");
                            break;
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("pathList")) ;// 첫번째 검색결과
                            else if (start == 0 && tag.equals("fid")) {
                                buffer.append("역코드 : ");
                                xpp.next();
                                String str = xpp.getText();
                                str = str.substring(0, str.length()-1);
                                startCode[0] = str;
                                buffer.append(str);//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                argument=new Navigator.argument_for_naviation();              //+beta 1.2 build 0127
                                argument.set_fid(str);
                                break;
                            }
                            else if (start == 0 && tag.equals("fname")) {
                                buffer.append("출발역 : ");
                                xpp.next();
                                String s = xpp.getText();
                                if(! xpp.getText().contains("역")) {
                                    s = xpp.getText() + "역";
                                }
                                startStation = s;
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                break;
                            } else if (start == 0 && tag.equals("railLinkId")) {
                                xpp.next();
                                startRailLinkListNum = xpp.getText();
                                argument.set_from_departure_station_to_arrival_station(Integer.parseInt(xpp.getText()));
                                break;
                            } else if (start == 0 && tag.equals("routeNm")) {
                                buffer.append("몇호선 : ");
                                xpp.next();
                                startLineNum[0] = xpp.getText();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                start = 1;
                                argument.set_subwayNm(xpp.getText());
                                break;
                            } else if (start == 1 && tag.equals("fname")) {
                                buffer.append("환승역 : ");
                                xpp.next();
                                middleStation[middleNum] = xpp.getText();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                break;
                            }else if (start == 1 && tag.equals("railLinkId")) {
                                xpp.next();
                                middleRailLinkListNum[middleNum] = xpp.getText();
                                argument.set_from_departure_station_to_arrival_station(Integer.parseInt(xpp.getText()));
                                break;
                            } else if (start == 1 && tag.equals("routeNm")) {
                                buffer.append("몇호선으로 갈아탈까 : ");
                                xpp.next();
                                middleLineNum[middleNum] = xpp.getText();
                                middleNum++;
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                argument.set_subwayNm(xpp.getText());
                                break;
                            } else if (start == 1 && tag.equals("tname")) {
                                buffer.append("도착역 : ");
                                xpp.next();
                                finalStation = xpp.getText();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                break;
                            } else if (tag.equals("time")) {
                                buffer.append("time:");
                                xpp.next();
                                time = xpp.getText();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                buffer.append("\n"); //줄바꿈 문자 추가
                                find = 1;
                                break;
                            }else if (tag.equals("tid")) {                                         //+beta 1.2 build 0127
                                xpp.next();                                                         //+beta 1.2 build 0127
                                String tid = xpp.getText();                                         //+beta 1.2 build 0127
                                tid = tid.substring(0, tid.length()-1);                             //+beta 1.2 build 0127
                                argument.set_tid(tid);                                              //+beta 1.2 build 0127
                                arguments.add(argument);                                            //+beta 1.2 build 0127
                                Log.d("fid",argument.get_fid());                               //+beta 1.2 build 0127
                                Log.d("subwayNm",argument.get_subwayNm());                     //+beta 1.2 build 0127
                                Log.d("to_arrival_station",Integer.toString(argument.get_from_departure_station_to_arrival_station()));//+beta 1.2 build 0127
                                Log.d("tid",argument.get_tid());                               //+beta 1.2 build 0127
                                argument=new Navigator.argument_for_naviation();              //+beta 1.2 build 0127
                                argument.set_fid(tid);                                              //+beta 1.2 build 0127
                                break;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tag = xpp.getName(); //테그 이름 얻어오기
                            if (tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                            break;
                    }
                    eventType = xpp.next();
                }
                return buffer.toString();
            } catch (Exception e) {
                System.out.println("failed");
            }
            return "";
        }
    }

    public class findDirectionApi extends AsyncTask<String, Void, String> {
        URL url = null;
        protected String doInBackground(String... strings) {
            String s = this.executeClient(strings);
            System.out.println(s);
            return s;
        }

        String executeClient(String[] str) {
            StringBuffer buffer = new StringBuffer();
            try {
                StringBuilder urlBuilder = new StringBuilder("http://swopenapi.seoul.go.kr/api/subway/70575677706d696333327152507752/xml/fastTransfer/0/5/");
                urlBuilder.append(URLEncoder.encode(str[0], "UTF-8")); //출발지
                url = new URL(urlBuilder.toString());
                InputStream is= url.openStream(); //url위치로 입력스트림 연결
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                XmlPullParser xpp= factory.newPullParser();
                xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
                String tag;
                xpp.next();
                String code = str[0];
                int eventType= xpp.getEventType();
                String ssubwayId = findSubwayId(startLineNum[0]);
                String esubwayId = findSubwayId(middleLineNum[0]);
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch( eventType ){
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("row")) ;// 첫번째 검색결과
                            else if (tag.equals("subwayId")) {
                                xpp.next();
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }else if (tag.equals("sdirection")) {
                                xpp.next();
                                buffer.append("/");
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }
                    }
                    eventType = xpp.next();
                }
                return buffer.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
    }

    public class findIdApi extends AsyncTask<String, Void, String> {

        URL url = null;
        protected String doInBackground(String... strings) {
            String s = this.executeClient(strings);
            System.out.println(s);
            return s;
        }

        String executeClient(String[] str) {
            StringBuffer buffer = new StringBuffer();
            try {
                StringBuilder urlBuilder = new StringBuilder("http://swopenAPI.seoul.go.kr/api/subway/575475534d7368613739674b557967/xml/realtimeStationArrival/0/5/");
                urlBuilder.append(URLEncoder.encode(str[0], "UTF-8")); //출발지
                url = new URL(urlBuilder.toString());
                InputStream is= url.openStream(); //url위치로 입력스트림 연결
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                XmlPullParser xpp= factory.newPullParser();
                xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
                String tag;
                xpp.next();
                String code = str[0];
                int eventType= xpp.getEventType();;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch( eventType ){
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();//테그 이름 얻어오기
                            if (tag.equals("row")) ;// 첫번째 검색결과
                            else if (tag.equals("subwayId")) {
                                xpp.next();
                                buffer.append("/");
                                buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                break;
                            }
                    }
                    eventType = xpp.next();
                }
                return buffer.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
    }

}
