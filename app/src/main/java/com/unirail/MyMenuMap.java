package com.unirail;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMenuMap {
    private ArrayList<String> subwayName;
    private HashMap<String, ArrayList<String>> mMenu;
    private String stationNM[] = {
            "가락시장",
            "강남",
            "강변",
            "건대입구",
            "경복궁",
            "경찰병원",
            "고속터미널",
            "교대",
            "구로디지털단지",
            "구의",
            "구파발",
            "금호",
            "길음",
            "낙성대",
            "남부터미널",
            "남태령",
            "노원",
            "녹번",
            "당고개",
            "당산",
            "대림",
            "대청",
            "대치",
            "도곡",
            "도림천",
            "독립문",
            "동대문",
            "동대문역사문화공원",
            "동대입구",
            "동묘앞",
            "동작",
            "뚝섬",
            "매봉",
            "명동",
            "무악재",
            "문래",
            "미아",
            "미아사거리",
            "방배",
            "봉천",
            "불광",
            "사당",
            "삼각지",
            "삼성",
            "상계",
            "상왕십리",
            "서울대입구",
            "서울역",
            "서초",
            "선릉",
            "성수",
            "성신여대입구",
            "수서",
            "수유",
            "숙대입구",
            "시청",
            "신답",
            "신당",
            "신대방",
            "신도림",
            "신림",
            "신사",
            "신설동",
            "신용산",
            "신정네거리",
            "신촌",
            "쌍문",
            "아현",
            "안국",
            "압구정",
            "약수",
            "양재",
            "양천구청",
            "역삼",
            "연신내",
            "영등포구청",
            "오금",
            "옥수",
            "왕십리",
            "용답",
            "용두",
            "을지로3가",
            "을지로4가",
            "을지로입구",
            "이대",
            "이촌",
            "일원",
            "잠실",
            "잠실나루",
            "잠실새내",
            "잠원",
            "제기동",
            "종각",
            "종로3가",
            "종로5가",
            "종합운동장",
            "지축",
            "창동",
            "청량리",
            "총신대입구",
            "충무로",
            "충정로",
            "학여울",
            "한성대입구",
            "한양대",
            "합정",
            "혜화",
            "홍대입구",
            "홍제",
            "회현"
    };

    public MyMenuMap() {
        mMenu                   = new HashMap<>();
        subwayName = new ArrayList<>();


        setHashMap();
        setSubwayName();
    }

    private void setSubwayName(){

        int i=0;
        for(i=0; i<stationNM.length; i++){
            subwayName.add(stationNM[i]);
        }
    }


    private void setHashMap() {
        mMenu.put("subway", subwayName);
    }
    // 각 메뉴 반환
    public ArrayList<String> getMenu(String menuTitle) { return mMenu.get(menuTitle); }
}