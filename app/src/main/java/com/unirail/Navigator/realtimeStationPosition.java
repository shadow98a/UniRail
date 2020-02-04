package com.unirail.Navigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class realtimeStationPosition extends open_API
{
//    private String subwayId=null;//지하철호선ID
//    private String subwayNm=null;//지하철호선명
//    private String statnId=null;//지하철역ID
    private String statnNm=null;//지하철역명
//    private String trainNo=null;//열차번호
//    private String lastRecptnDt=null;//최종수신날짜
//    private String recptnDt=null;//최종수신시간
//    private String updnLine=null;//상하행선구분 (2호선(1:내선 0:외선), 0:상행 1:하행)
//    private String statnTid=null;//종착지하철역ID
//    private String statnTnm=null;//종착지하철역명
    private String trainSttus=null;//열차상태구분 (0:진입 1:도착, 0,1외 나머지는:출발)
    private String directAt=null;//급행여부 (1:급행, 0:아님)
//    private String lstcarAt=null;//막차여부 (1:막차, 0:아님)

    static public final String is_approaching="0";
    static public final String is_stopped="1";
    static public final String is_leaving="2";
    static public final String is_express_train="1";
    static public final String is_not_express_train="0";

    public realtimeStationPosition(final String subwayNm)
    {
        String encoded_subwayNm=null;
        try
        {
            encoded_subwayNm= URLEncoder.encode(subwayNm,"UTF-8");
        }
        catch(final UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }

        URL="http://swopenAPI.seoul.go.kr/api/subway/"+realtime_station_key+"/json/realtimePosition/0/99/"+encoded_subwayNm;
    }

    public final void request(final String trainNo)
    {
        try
        {
            JSONArray list = new JSONObject(JSON(URL)).getJSONArray("realtimePositionList");
            JSONObject object;
            for (int index = 0; index < list.length(); index++)
            {
                object = list.getJSONObject(index);
                if (object.getString("trainNo").equals(trainNo))
                {
                    parse(object);
                    break;
                }
            }
        }
        catch (final JSONException exception)
        {
            exception.printStackTrace();
        }
    }

    public final String get_statnNm()
    {
        return statnNm;
    }

    public final String get_trainSttus()
    {
        return trainSttus;
    }

    public final String get_directAt()
    {
        return directAt;
    }

    @Override
    protected void parse(final JSONObject object)
    {
        try
        {
//            subwayId=object.getString("subwayId");
//            subwayNm=object.getString("subwayNm");
//            statnId=object.getString("statnId");
            statnNm=object.getString("statnNm");
//            trainNo=object.getString("trainNo");
//            lastRecptnDt=object.getString("lastRecptnDt");
//            recptnDt=object.getString("recptnDt");
//            updnLine=object.getString("updnLine");
//            statnTid=object.getString("statnTid");
//            statnTnm=object.getString("statnTnm");
            trainSttus=object.getString("trainSttus");
            directAt=object.getString("directAt");
//            lstcarAt=object.getString("lstcarAt");
        }
        catch (final JSONException exception)
        {
            exception.printStackTrace();
        }
    }
}