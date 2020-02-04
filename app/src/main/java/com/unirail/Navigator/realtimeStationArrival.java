package com.unirail.Navigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class realtimeStationArrival extends open_API
{
//    private String subwayId=null;//지하철호선ID
    private String updnLine=null;//상하행선구분(2호선 : (내선:0,외선:1),상행,하행)
//    private String trainLineNm=null;//도착지방면 (성수행 - 구로디지털단지방면)
    private String subwayHeading=null;//내리는문방향 (오른쪽,왼쪽)
//    private String statnFid=null;//이전지하철역ID
//    private String statnTid=null;//다음지하철역ID
//    private String statnId=null;//지하철역ID
//    private String statnNm=null;//지하철역명
//    private String trnsitCo=null;//환승노선수
//    private String ordkey=null;//도착예정열차순번
//    private String subwayList=null;//연계호선ID (1002, 1007 등 연계대상 호상ID)
//    private String statnList=null;//연계지하철역ID (1002000233,1007000744)
//    private String btrainSttus=null;//열차종류 (급행,ITX)
//    private String barvlDt=null;//열차도착예정시간 (단위:초)
    private String btrainNo=null;//열차번호 (현재운행하고 있는 호선별 열차번호)
//    private String bstatnId=null;//종착지하철역ID
    private String bstatnNm=null;//종착지하철역명
//    private String recptnDt=null;//열차도착정보를 생성한 시각
    private String arvlMsg2=null;//첫번째도착메세지 (전역 진입, 전역 도착 등)
//    private String arvlMsg3=null;//두번째도착메세지 (종합운동장 도착, 12분 후 (광명사거리) 등)
    private String arvlCd=null;//도착코드 (0:진입, 1:도착, 2:출발, 3:전역출발, 4:전역진입, 5:전역도착, 99:운행중)

    static public final String is_approaching="0";
    static public final String is_stopped="1";
    static public final String is_leaving="2";

    public realtimeStationArrival(final String statnNm)
    {
        String encoded_statnNm=null;
        try
        {
            encoded_statnNm= URLEncoder.encode(statnNm,"UTF-8");
        }
        catch(final UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }

        URL="http://swopenapi.seoul.go.kr/api/subway/"+realtime_station_key+"/json/realtimeStationArrival/0/99/"+encoded_statnNm;
    }

    public final void request(final String subwayId, final String updnLine)
    {
        try
        {
            JSONArray list = new JSONObject(JSON(URL)).getJSONArray("realtimeArrivalList");
            JSONObject object;
            for (int index=0;index<list.length();index++)
            {
                object = list.getJSONObject(index);
                if (object.getString("subwayId").equals(subwayId)&&object.getString("updnLine").equals(updnLine))
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

    public final void request(final String btrainNo)
    {
        try
        {
            JSONArray list = new JSONObject(JSON(URL)).getJSONArray("realtimeArrivalList");
            JSONObject object;
            for (int index=0;index<list.length();index++)
            {
                object = list.getJSONObject(index);
                if (object.getString("btrainNo").equals(btrainNo))
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

    public final String get_updnLine()
    {
        return updnLine;
    }

    public final String get_subwayHeading()
    {
        return subwayHeading;
    }


    public final String get_btrainNo()
    {
        return btrainNo;
    }

    public final String get_bstatnNm()
    {
        return bstatnNm;
    }

    public final String get_arvlMsg2()
    {
        return arvlMsg2;
    }

    public final String get_arvlCd()
    {
        return arvlCd;
    }

    @Override
    protected final void parse(final JSONObject object)
    {
        try
        {
//            subwayId=object.getString("subwayId");
//            updnLine=object.getString("updnLine");
//            trainLineNm=object.getString("trainLineNm");
            subwayHeading=object.getString("subwayHeading");
//            statnFid=object.getString("statnFid");
//            statnTid=object.getString("statnTid");
//            statnId=object.getString("statnId");
//            statnNm=object.getString("statnNm");
//            trnsitCo=object.getString("trnsitCo");
//            ordkey=object.getString("ordkey");
//            subwayList=object.getString("subwayList");
//            statnList=object.getString("statnList");
//            btrainSttus=object.getString("btrainSttus");
//            barvlDt=object.getString("barvlDt");
            btrainNo=object.getString("btrainNo");
//            bstatnId=object.getString("bstatnId");
            bstatnNm=object.getString("bstatnNm");
//            recptnDt=object.getString("recptnDt");
            arvlMsg2=object.getString("arvlMsg2");
//            arvlMsg3=object.getString("arvlMsg3");
            arvlCd=object.getString("arvlCd");
        }
        catch (final JSONException exception)
        {
            exception.printStackTrace();
        }
    }
}