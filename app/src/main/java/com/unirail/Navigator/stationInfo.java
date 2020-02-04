package com.unirail.Navigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class stationInfo extends open_API
{
//    private String statnId=null;//지하철역ID (1001080144, 1001080145 등 지하철역별 고유ID)
//    private String statnNm=null;//지하철역명 (금천구청, 석수 등 지하철역별 고유 한글명)
//    private String subwayId=null;//지하철호선ID (1001,1002 등 지하철호선별 고유ID)
//    private String subwayNm=null;//지하철호선명 (1호선, 2호선 등 지하철호선별 고유 한글명)
//    private String statnNmEng=null;//지하철역영문명 (Geumcheon-gu Office, Soeksu 등 지하철역별 고유 영문명)
//    private String statnFid=null;//이전지하철역ID
//    private String statnFnm=null;//이전지하철역명
//    private String statnTid=null;//다음지하철역ID
//    private String statnTnm=null;//다음지하철역명
//    private String operPblinstt=null;//운영기관 (서울메트로, 철도청구간 등)
//    private String zipNo=null;//우편번호
//    private String adresBass=null;//기본주소
//    private String adresDetail=null;//상세주소
//    private String telno=null;//전화번호
//    private String fxnum=null;//팩스번호
    private String directAt=null;//급행정차여부 (0:미정차, 1:상행정차, 2:하행정차, 3:양방향정차)
//    private String subwayXcnts=null;//지하철X좌표 (204134.76818, 452282.70778 등)
//    private String subwayYcnts=null;//지하철Y좌표 (204134.76818, 452282.70778 등)
//    private String trnsitCo=null;//환승노선수
//    private String subwayList=null;//연계지하철역ID

    static public final String express_train_stops_at_no_line="0";
    static public final String express_train_stops_at_up_line="1";
    static public final String express_train_stops_at_down_line="2";
    static public final String express_train_stops_at_all_line="3";

    public stationInfo(final String statnNm)
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

        URL="http://swopenAPI.seoul.go.kr/api/subway/"+default_key+"/json/stationInfo/0/99/"+encoded_statnNm;
    }

    public final void request(final String subwayNm)
    {
        try
        {
            JSONArray list = new JSONObject(JSON(URL)).getJSONArray("stationList");
            JSONObject object;
            for (int index=0;index<list.length();index++)
            {
                object = list.getJSONObject(index);
                if (object.getString("subwayNm").equals(subwayNm))
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

    public final String get_directAt()
    {
        return directAt;
    }

    @Override
    protected final void parse(final JSONObject object)
    {
        try
        {
//            statnId=object.getString("statnId");
//            statnNm=object.getString("statnNm");
//            subwayId=object.getString("subwayId");
//            subwayNm=object.getString("subwayNm");
//            statnNmEng=object.getString("statnNmEng");
//            statnFid=object.getString("statnFid");
//            statnFnm=object.getString("statnFnm");
//            statnTid=object.getString("statnTid");
//            statnTnm=object.getString("statnTnm");
//            operPblinstt=object.getString("operPblinstt");
//            zipNo=object.getString("zipNo");
//            adresBass=object.getString("adresBass");
//            adresDetail=object.getString("adresDetail");
//            telno=object.getString("telno");
//            fxnum=object.getString("fxnum");
            directAt=object.getString("directAt");
//            subwayXcnts=object.getString("subwayXcnts");
//            subwayYcnts=object.getString("subwayYcnts");
//            trnsitCo=object.getString("trnsitCo");
//            subwayList=object.getString("subwayList");
        }
        catch (final JSONException exception)
        {
            exception.printStackTrace();
        }
    }
}
