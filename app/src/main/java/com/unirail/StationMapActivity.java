package com.unirail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.HashMap;

public class StationMapActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_map);
        String InputStation;
        PhotoView photoView = findViewById(R.id.photoView);
        //Bundle extras = getIntent().getExtras();
        InputStation = getIntent().getStringExtra("InputStation");
        System.out.println(InputStation);

        HashMap<String, Integer> InputStation_to_ImageResource = new HashMap<>();
        InputStation_to_ImageResource.put("서울역", R.drawable.seoul);
        InputStation_to_ImageResource.put("시청", R.drawable.cityhall_one);
        InputStation_to_ImageResource.put("종각역", R.drawable.jonggak);
        InputStation_to_ImageResource.put("종로3가", R.drawable.jongnosamga);
        InputStation_to_ImageResource.put("종로5가", R.drawable.jongnooga);
        InputStation_to_ImageResource.put("동대문", R.drawable.dongdaemun);
        InputStation_to_ImageResource.put("동묘앞", R.drawable.dongmyo);
        InputStation_to_ImageResource.put("신설동", R.drawable.sinseoldong); //1호선
        InputStation_to_ImageResource.put("제기동", R.drawable.jegidong);
        InputStation_to_ImageResource.put("청량리", R.drawable.cheongnyangni);
        //--------------------------------------------------------------------------
        InputStation_to_ImageResource.put("을지로입구", R.drawable.euljiroilga);
        InputStation_to_ImageResource.put("을지로3가", R.drawable.euljirosamga); //2호선
        InputStation_to_ImageResource.put("을지로4가", R.drawable.euljirosaga); //2호선
        InputStation_to_ImageResource.put("동대문역사문화공원", R.drawable.dongdaemun_history);//2호선
        InputStation_to_ImageResource.put("신당", R.drawable.sindang);
        InputStation_to_ImageResource.put("상왕십리", R.drawable.sangwangsimni);
        InputStation_to_ImageResource.put("왕십리", R.drawable.wangsimni);
        InputStation_to_ImageResource.put("한양대", R.drawable.hanyang_univ);
        InputStation_to_ImageResource.put("뚝섬", R.drawable.ttukseom);
        InputStation_to_ImageResource.put("성수", R.drawable.seongsu);
        InputStation_to_ImageResource.put("건대입구", R.drawable.konkuk_univ);
        InputStation_to_ImageResource.put("구의", R.drawable.guui);
        InputStation_to_ImageResource.put("강변", R.drawable.gangbyeon);
        InputStation_to_ImageResource.put("잠실나루", R.drawable.jamsillaru);
        InputStation_to_ImageResource.put("잠실", R.drawable.jamsil);
        InputStation_to_ImageResource.put("잠실새내", R.drawable.jamsilsaenae);
        InputStation_to_ImageResource.put("종합운동장", R.drawable.sports_complex);
        InputStation_to_ImageResource.put("삼성", R.drawable.samseong);
        InputStation_to_ImageResource.put("선릉", R.drawable.seolleung);
        InputStation_to_ImageResource.put("역삼", R.drawable.yeoksam);
        InputStation_to_ImageResource.put("강남", R.drawable.gangnam);
        InputStation_to_ImageResource.put("교대", R.drawable.seoulnatl_univ);
        InputStation_to_ImageResource.put("서초", R.drawable.seocho);
        InputStation_to_ImageResource.put("방배", R.drawable.bangbae);
        InputStation_to_ImageResource.put("사당", R.drawable.sadang);
        InputStation_to_ImageResource.put("낙성대", R.drawable.nakseongdae);
        InputStation_to_ImageResource.put("서울대입구", R.drawable.seoul_univ);
        InputStation_to_ImageResource.put("봉천", R.drawable.bongcheon);
        InputStation_to_ImageResource.put("신림", R.drawable.sillim);
        InputStation_to_ImageResource.put("신대방", R.drawable.sindaebang);
        InputStation_to_ImageResource.put("구로디지털단지", R.drawable.guro_digital_complex);
        InputStation_to_ImageResource.put("대림", R.drawable.daerim);
        InputStation_to_ImageResource.put("신도림", R.drawable.sindorim);
        InputStation_to_ImageResource.put("문래", R.drawable.mullae);
        InputStation_to_ImageResource.put("영등포구청", R.drawable.yeongdeungpogu_office);
        InputStation_to_ImageResource.put("당산", R.drawable.dangsan);
        InputStation_to_ImageResource.put("합정", R.drawable.hapjeong);
        InputStation_to_ImageResource.put("홍대입구", R.drawable.hongik_univ);
        InputStation_to_ImageResource.put("신촌", R.drawable.sinchon);
        InputStation_to_ImageResource.put("이대입구", R.drawable.ewha_womans_univ);
        InputStation_to_ImageResource.put("아현", R.drawable.ahyeon);
        InputStation_to_ImageResource.put("충정로", R.drawable.chungjeongno);
        InputStation_to_ImageResource.put("용답", R.drawable.yongdap);
        InputStation_to_ImageResource.put("신답", R.drawable.sindap);
        InputStation_to_ImageResource.put("용두", R.drawable.yongdu);
        //InputStation_to_ImageResource.put("신설동",R.drawable.sinseoldong); 2호선
        InputStation_to_ImageResource.put("도림천", R.drawable.dorimcheon);
        InputStation_to_ImageResource.put("양천구청", R.drawable.yangcheongu_office);
        InputStation_to_ImageResource.put("신정네거리", R.drawable.sinjeongnegeori);
        //---------------------------------------------------------------------------
        InputStation_to_ImageResource.put("지축", R.drawable.jichuk);
        InputStation_to_ImageResource.put("구파발", R.drawable.gupabal);
        InputStation_to_ImageResource.put("연신내", R.drawable.yeonsinnae);
        InputStation_to_ImageResource.put("불광", R.drawable.bulgwang);
        InputStation_to_ImageResource.put("녹번", R.drawable.nokbeon);
        InputStation_to_ImageResource.put("홍제", R.drawable.hongje);
        InputStation_to_ImageResource.put("무악재", R.drawable.muakjae);
        InputStation_to_ImageResource.put("독립문", R.drawable.dongnimmun);
        InputStation_to_ImageResource.put("경복궁", R.drawable.gyeongbokgung);
        InputStation_to_ImageResource.put("안국", R.drawable.anguk);
        InputStation_to_ImageResource.put("충무로", R.drawable.chungmuro);//3호선
        InputStation_to_ImageResource.put("동대입구", R.drawable.dongguk_univ);
        InputStation_to_ImageResource.put("약수", R.drawable.yaksu);
        InputStation_to_ImageResource.put("금호", R.drawable.geumho);
        InputStation_to_ImageResource.put("옥수", R.drawable.oksu);
        InputStation_to_ImageResource.put("압구정", R.drawable.apgujeong);
        InputStation_to_ImageResource.put("신사", R.drawable.sinsa);
        InputStation_to_ImageResource.put("잠원", R.drawable.jamwon);
        InputStation_to_ImageResource.put("고속터미널", R.drawable.express_bus_terminal);
        //InputStation_to_ImageResource.put("교대",R.drawable.seoul_natl_nuiv); 3호선
        InputStation_to_ImageResource.put("남부터미널", R.drawable.nambu_bus_terminal);
        InputStation_to_ImageResource.put("양재", R.drawable.yangjae);
        InputStation_to_ImageResource.put("매봉", R.drawable.maebong);
        InputStation_to_ImageResource.put("도곡", R.drawable.dogok);
        InputStation_to_ImageResource.put("대치", R.drawable.daechi);
        InputStation_to_ImageResource.put("학여울", R.drawable.hangnyeoul);
        InputStation_to_ImageResource.put("대청", R.drawable.daecheong);
        InputStation_to_ImageResource.put("일원", R.drawable.irwon);
        InputStation_to_ImageResource.put("수서", R.drawable.suseo);
        InputStation_to_ImageResource.put("가락시장", R.drawable.garak_market);
        InputStation_to_ImageResource.put("경찰병원", R.drawable.national_police_hospital);
        InputStation_to_ImageResource.put("오금", R.drawable.ogeum);
        //----------------------------------------------------------------------
        InputStation_to_ImageResource.put("당고개", R.drawable.danggogae);
        InputStation_to_ImageResource.put("상계", R.drawable.sanggye);
        InputStation_to_ImageResource.put("노원", R.drawable.nowon);
        InputStation_to_ImageResource.put("창동", R.drawable.changdong);
        InputStation_to_ImageResource.put("쌍문", R.drawable.ssangmun);
        InputStation_to_ImageResource.put("수유", R.drawable.suyu);
        InputStation_to_ImageResource.put("미아", R.drawable.mia);
        InputStation_to_ImageResource.put("미아사거리", R.drawable.miasageori);
        InputStation_to_ImageResource.put("길음", R.drawable.gireum);
        InputStation_to_ImageResource.put("성신여대입구", R.drawable.sungshin_womans_univ);
        InputStation_to_ImageResource.put("한성대", R.drawable.hansung_univ);
        InputStation_to_ImageResource.put("혜화", R.drawable.hyehwa);
        //InputStation_to_ImageResource.put("동대문",R.drawable.dongdaemun);
        //InputStation_to_ImageResource.put("동대문역사문화공원",R.drawable.dongdaemun_history_four);
        //InputStation_to_ImageResource.put("충무로",R.drawable.chungmuro);
        InputStation_to_ImageResource.put("명동", R.drawable.myeongdong);
        InputStation_to_ImageResource.put("회현", R.drawable.hoehyeon);
        //InputStation_to_ImageResource.put("서울역",R.drawable.seoul);
        InputStation_to_ImageResource.put("숙대입구", R.drawable.sookmyung_womens_univ);
        InputStation_to_ImageResource.put("삼각지", R.drawable.samgakji);
        InputStation_to_ImageResource.put("신용산", R.drawable.sinyongsan);
        InputStation_to_ImageResource.put("이촌", R.drawable.ichon);
        InputStation_to_ImageResource.put("동작", R.drawable.dongjak);
        InputStation_to_ImageResource.put("총신대입구", R.drawable.chongshin_univ);
        //InputStation_to_ImageResource.put("사당",R.drawable.sadang);
        InputStation_to_ImageResource.put("남태령", R.drawable.namtaeryeong);

        try{
            int ImageResouce = InputStation_to_ImageResource.get(InputStation);
            photoView.setImageResource(ImageResouce);
        }catch(Exception e){
            Toast.makeText(StationMapActivity.this,"해당역정보가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
